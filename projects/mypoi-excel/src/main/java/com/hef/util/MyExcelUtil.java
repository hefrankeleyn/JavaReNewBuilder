package com.hef.util;

import com.google.common.base.Joiner;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.hef.domain.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @Date 2022/1/8
 * @Author lifei
 */
public class MyExcelUtil {

    /**
     * 设置列宽的系数
     */
    private static int COL_WIDTH_BASE = 256;

    /**
     * 创建一个新的excel， 并向里面写入内容
     *
     * @param excelContent  excel 的内容
     * @param excelFilePath excel文件路径，excel文件所在的文件夹路径必须存在。如果文件已经存在会被删除后重建。
     */
    public static void createNewExcelWriteContent(ExcelContent excelContent, String excelFilePath) {
        try {
            MyExcelTypeEnum myExcelType = judgeExcelFileType(excelFilePath);
            Workbook wb;
            if (myExcelType == MyExcelTypeEnum.XLS) {
                wb = new HSSFWorkbook();
            } else {
                wb = new XSSFWorkbook();
            }

            if (excelContent != null && !CollectionUtils.isEmpty(excelContent.getSheetContentList())) {
                writeExcelContent(excelContent, wb);
                try (OutputStream outputStream = new FileOutputStream(excelFilePath)) {
                    wb.write(outputStream);
                }
            }
            try (OutputStream outputStream = new FileOutputStream(excelFilePath)) {
                wb.write(outputStream);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 填写excel的内容
     *
     * @param excelContent
     * @param wb
     */
    private static void writeExcelContent(ExcelContent excelContent, Workbook wb) {
        if (excelContent == null || CollectionUtils.isEmpty(excelContent.getSheetContentList())) {
            return;
        }
        CellStyle cellStyle = wb.createCellStyle();
        setBaseBorderStyle(cellStyle);
        for (ExcelSheetContent sheetContent : excelContent.getSheetContentList()) {
            Sheet sheet = wb.createSheet(sheetContent.getSheetName());
            List<TableContent> tableList = sheetContent.getTableContentList();
            int nextRowIndex = 0;
            for (TableContent tableContent : tableList) {
                nextRowIndex = writeTableContent(tableContent, sheet, nextRowIndex, cellStyle, wb);
            }
            // 设置sheet的宽度
            int[] columnWidthsBase256 = sheetContent.getColumnWidthsBase256();
            if (columnWidthsBase256 != null && columnWidthsBase256.length > 0) {
                for (int i = 0; i < columnWidthsBase256.length; i++) {
                    sheet.setColumnWidth(i, columnWidthsBase256[i] * COL_WIDTH_BASE);
                }
            }
        }
    }

    /**
     * 向一个sheet中写一个表格的内容
     *
     * @param tableContent
     * @param sheet
     * @param firstRowIndex
     * @param cellStyle
     * @param wb
     * @return
     */
    private static int writeTableContent(TableContent tableContent, Sheet sheet, int firstRowIndex, CellStyle cellStyle, Workbook wb) {
        CellContent[][] table = tableContent.getTable();
        if (table == null || table.length == 0 || table[0].length == 0) {
            return firstRowIndex;
        }
        int h = table.length, w = table[0].length;
        String title = tableContent.getTitle();
        // 创建标题
        if (StringUtils.isNoneBlank(title)) {
            Row row = sheet.createRow(firstRowIndex);
            // 创建合并单元格
            CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRowIndex, firstRowIndex, 0, w - 1);
            sheet.addMergedRegion(cellRangeAddress);
            // 设置合并单元格的基础样式
            setMergeBaseStyle(cellRangeAddress, sheet);
            // 设置合并单元格的值
            Cell cell = row.createCell(0);
            setCellValue(tableContent.getTitle(), cell);
            cell.setCellStyle(cellStyle);
            if (Objects.nonNull(tableContent.getTitleAlignment())) {
                CellStyle cellStyle1 = wb.createCellStyle();
                setBaseBorderStyle(cellStyle1);
                cellStyle1.setAlignment(tableContent.getTitleAlignment());
                cell.setCellStyle(cellStyle1);
            }
            if (Objects.nonNull(tableContent.getTitleBGColor())) {
                CellStyle cellStyle1 = setCellSelfColor(wb, tableContent.getTitleBGColor());
                if (cellStyle1 != null) {
                    setBaseBorderStyle(cellStyle1);
                    if (Objects.nonNull(tableContent.getTitleAlignment())) {
                        cellStyle1.setAlignment(tableContent.getTitleAlignment());
                    }
                    cell.setCellStyle(cellStyle1);
                }
            }
        }
        // 创建正文内容
        int index = firstRowIndex + 1;
        for (int i = 0; i < table.length; i++, index++) {
            Row row = sheet.createRow(index);
            for (int j = 0; j < table[i].length; j++) {
                Cell cell = row.createCell(j);
                setCellValue(table[i][j].getCellValue(), cell);
                cell.setCellStyle(cellStyle);
                if (Objects.nonNull(table[i][j].getMyCellBGColor())) {
                    CellStyle cellStyle1 = setCellSelfColor(wb, table[i][j].getMyCellBGColor());
                    setBaseBorderStyle(cellStyle1);
                    cell.setCellStyle(cellStyle1);
                }
            }
        }
        // 根据 rowRange 和 colRange 合并复杂的单元格, 2022-10-15 修改
        if (Objects.nonNull(tableContent.getRowRange()) && tableContent.getRowRange().lowerEndpoint() > 0
                && Objects.nonNull(tableContent.getColRange()) && tableContent.getColRange().lowerEndpoint() > 0) {
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            // 开始行 和结束 行
            Range<Integer> rowRange = tableContent.getRowRange();
            int rowFirstAdd = Objects.equals(rowRange.lowerBoundType(), BoundType.OPEN) ? rowRange.lowerEndpoint() + 1 : rowRange.lowerEndpoint();
            int rowSecondAdd = Objects.equals(rowRange.upperBoundType(), BoundType.OPEN) ? rowRange.upperEndpoint() - 1 : rowRange.upperEndpoint();
            int rowBegin = firstRowIndex + 1 + (rowFirstAdd - 1);
            int rowEnd = firstRowIndex + 1 + (rowSecondAdd - 1);

            // 开始列 和 结束列
            Range<Integer> colRange = tableContent.getColRange();
            int colFirstAdd = Objects.equals(colRange.lowerBoundType(), BoundType.OPEN) ? colRange.lowerEndpoint() + 1 : colRange.lowerEndpoint();
            int colSecondAdd = Objects.equals(colRange.upperBoundType(), BoundType.OPEN) ? colRange.upperEndpoint() - 1 : colRange.upperEndpoint();
            int colBegin = colFirstAdd - 1;
            int colEnd = colSecondAdd - 1;
            mergeRowsOrCols(rowBegin, rowEnd, colBegin, colEnd, sheet, formulaEvaluator);
        } else if (tableContent.getMergeBeforeColNums() > 0) {
            // 前几列对每一列进行合并单元格
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            mergeRowsColByCol(firstRowIndex + 1, index - 1, 0, tableContent.getMergeBeforeColNums() - 1, sheet, formulaEvaluator);
        }
        // 下一个表格和上一个表格，中间留一行空白
        return index + 1;
    }

    /**
     * 合并复杂的单元格
     * @param rowBegin 开始行 （包含边界）
     * @param rowEnd 结束行 （包含边界）
     * @param colBegin 开始列 （包含边界）
     * @param colEnd 结束列（包含边界）
     * @param sheet
     * @param formulaEvaluator
     */
    private static void mergeRowsOrCols(int rowBegin, int rowEnd, int colBegin, int colEnd, Sheet sheet, FormulaEvaluator formulaEvaluator) {
        // 已经在合并了的单元格
        Set<String> mergeCellSet = new HashSet<>();
        // 遍历要合并的范围
        for (int i = rowBegin; i<=rowEnd; i++) {
            for (int j=colBegin; j<=colEnd; j++) {
                // 如果单元格已经合并过了就跳过
                if (mergeCellSet.contains(cellPoint(i, j))) continue;
                int r1 = i, c1 = j, r2 = i, c2 = j;
                // 查找要最大合并的列
                while ((c2+1)<=colEnd && !mergeCellSet.contains(cellPoint(r2, c2+1)) && twoCellValueEquals(r1, c1, r2, (c2+1), sheet, formulaEvaluator)) c2 += 1;

                // 查找要合并的最大行
                while ((r2+1)<=rowEnd && onePointEqualOneRow(r1, c1, r2+1, c1, c2, mergeCellSet, sheet, formulaEvaluator)) r2 += 1;
                // 两个点不相等，的时候，进行合并
                if (!StringUtils.equals(cellPoint(r1, c1), cellPoint(r2, c2))) {
                    sheet.addMergedRegion(new CellRangeAddress(r1, r2, c1, c2));
                    // 将合并过的单元格放到集合中
                    for (int r=r1; r<=r2; r++) {
                        for (int c=c1; c<=c2; c++) {
                            mergeCellSet.add(cellPoint(r, c));
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断某一个值与整行数据相等
     * @param row
     * @param col
     * @param r2
     * @param c1
     * @param c2
     * @param mergeCellSet
     * @param sheet
     * @param formulaEvaluator
     * @return
     */
    private static boolean onePointEqualOneRow(int row, int col, int r2, int c1, int c2, Set<String> mergeCellSet, Sheet sheet, FormulaEvaluator formulaEvaluator) {
        for (int c = c1; c <= c2; c++) {
            if (mergeCellSet.contains(cellPoint(r2, c))) return false;
            if (!twoCellValueEquals(row, col, r2, c, sheet, formulaEvaluator)) return false;
        }
        return true;
    }

    /**
     *
     * @param r1
     * @param c1
     * @param r2
     * @param c2
     * @param sheet
     * @param formulaEvaluator
     * @return
     */
    private static boolean twoCellValueEquals(int r1, int c1, int r2, int c2, Sheet sheet, FormulaEvaluator formulaEvaluator) {
        // 判断两个单元格的值是否相等
        String v1 = getCellValue(sheet.getRow(r1).getCell(c1), formulaEvaluator) + "";
        String v2 = getCellValue(sheet.getRow(r2).getCell(c2), formulaEvaluator) + "";
        return StringUtils.equals(v1, v2);
    }

    /**
     * 将坐标坐标变成一个字符串
     * @param row
     * @param col
     * @return
     */
    private static String cellPoint(int row, int col) {
        return Joiner.on("-").join(Arrays.asList(row, col));
    }

    /**
     * 对每一列进行合并单元格
     *
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @param sheet
     * @param formulaEvaluator
     */
    private static void mergeRowsColByCol(int firstRow, int endRow, int firstCol, int endCol,
                                          Sheet sheet, FormulaEvaluator formulaEvaluator) {
        for (int col = firstCol; col <= endCol; col++) {
            for (int r1 = firstRow, r2 = firstRow; r1 < endRow && r2 <= endRow; ) {
                if (Objects.isNull(sheet.getRow(r1)) || Objects.isNull(sheet.getRow(r2 + 1))) {
                    if (r2 > r1) {
                        sheet.addMergedRegion(new CellRangeAddress(r1, r2, col, col));
                    }
                    r1 = r2 + 1;
                    r2 = r1;
                    continue;
                }
                String v1 = getCellValue(sheet.getRow(r1).getCell(col), formulaEvaluator) + "";
                String v2 = getCellValue(sheet.getRow(r2 + 1).getCell(col), formulaEvaluator) + "";
                if (StringUtils.equals(v1, v2)) {
                    r2++;
                } else {
                    if (r2 > r1) {
                        sheet.addMergedRegion(new CellRangeAddress(r1, r2, col, col));
                    }
                    r1 = r2 + 1;
                    r2 = r1;
                }
            }
        }
    }

    private static Object getCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null) return null;
        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                return getCellValue(formulaEvaluator.evaluate(cell));
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

    /**
     * 获取单元格的值
     *
     * @param cellValue
     * @return
     */
    private static Object getCellValue(CellValue cellValue) {
        if (cellValue == null) return null;
        switch (cellValue.getCellTypeEnum()) {
            case NUMERIC:
                return cellValue.getNumberValue();
            case STRING:
                return cellValue.getStringValue();
            case BOOLEAN:
                return cellValue.getBooleanValue();
            default:
                return null;
        }
    }

    /**
     * 设置单元格基础的样式
     *
     * @param cellStyle
     */
    private static void setBaseBorderStyle(CellStyle cellStyle) {
        // 设置单元格 上下左右为实线黑边
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        // 单元格内容居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 解决样式中打开后自动换行（不用鼠标双击一下才换行)
        cellStyle.setWrapText(true);
    }

    /**
     * 设置合并单元格的边框样式：实线、黑框
     *
     * @param cellRangeAddress
     * @param sheet
     */
    private static void setMergeBaseStyle(CellRangeAddress cellRangeAddress, Sheet sheet) {
        RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.index, cellRangeAddress, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setTopBorderColor(IndexedColors.BLACK.index, cellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.index, cellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.BLACK.index, cellRangeAddress, sheet);
    }

    /**
     * 设置单元格的值
     *
     * @param value
     * @param cell
     */
    private static void setCellValue(Object value, Cell cell) {
        if (value == null) {
            return;
        }
        if (value instanceof Double || value instanceof Integer) {
            cell.setCellValue(Double.valueOf(value + ""));
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue(value + "");
        }
    }

    /**
     * 自定义背景颜色
     *
     * @param wb
     * @param myCellColor
     * @return
     */
    private static CellStyle setCellSelfColor(Workbook wb, MyCellColor myCellColor) {
        if (wb instanceof HSSFWorkbook && myCellColor != null) {
            HSSFWorkbook hssfWorkbook = (HSSFWorkbook) wb;
            HSSFPalette customPalette = hssfWorkbook.getCustomPalette();
            customPalette.setColorAtIndex(HSSFColor.HSSFColorPredefined.LIME.getIndex(),
                    (byte) myCellColor.getRed(),
                    (byte) myCellColor.getGreen(),
                    (byte) myCellColor.getBlue());
            HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
            cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIME.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            return cellStyle;
        } else if (wb instanceof XSSFWorkbook && myCellColor != null) {
            XSSFWorkbook xssfWorkbook = (XSSFWorkbook) wb;
            XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
            cellStyle.setFillForegroundColor(new XSSFColor(new Color(myCellColor.getRed(),
                    myCellColor.getGreen(),
                    myCellColor.getBlue())));
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            return cellStyle;
        } else {
            return null;
        }
    }

    /**
     * 判断excel文件类型
     *
     * @param excelFilePath
     * @return
     */
    private static MyExcelTypeEnum judgeExcelFileType(String excelFilePath) {
        if (StringUtils.isBlank(excelFilePath)) {
            throw new IllegalArgumentException("excel路径不能为空");
        }
        int i = excelFilePath.lastIndexOf(".");
        if (i < 0) throw new IllegalArgumentException("文件名称没有后缀，后缀必须为: .xls 或 .xlsx");
        String suffix = excelFilePath.substring(i + 1);
        MyExcelTypeEnum myExcelType = MyExcelTypeEnum.findExcelSuffixEnum(suffix);
        if (myExcelType == null) throw new IllegalArgumentException("文件后缀必须为： .xls 或 .xlsx");
        File file = new File(excelFilePath);
        if (file.exists() && file.isFile()) {
            file.delete();
//            throw new IllegalArgumentException("文件已存在，需要把已存在的文件删除");
        }
        String parent = file.getParent();
        File parentFile = new File(parent);
        if (!parentFile.exists() || !parentFile.isDirectory()) {
            throw new IllegalArgumentException("文件路径不存在：" + parentFile.getAbsolutePath());
        }
        return myExcelType;
    }
}
