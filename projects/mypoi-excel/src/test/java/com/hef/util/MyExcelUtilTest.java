package com.hef.util;

import com.hef.domain.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/1/8
 * @Author lifei
 */
public class MyExcelUtilTest {

    /**
     * 将数据内容写入excel文件
     */
    @Test
    public void createNewExcelWriteContentTest() {
        String excelFilePath = "/Users/lifei/Documents/opt/doc/demo01.xlsx";
        ExcelContent excelContent = new ExcelContent();
        List<ExcelSheetContent> sheetContentList = new ArrayList<>();
        // 添加第一个sheet页的内容
        sheetContentList.add(createFirstSheet());
        // 添加第二个sheet页内容
        sheetContentList.add(createSecondSheet());
        // 添加多个sheet页的内容
        excelContent.setSheetContentList(sheetContentList);
        MyExcelUtil.createNewExcelWriteContent(excelContent, excelFilePath);
    }

    /**
     * 创建第一个sheet
     * @return
     */
    private ExcelSheetContent createFirstSheet() {
        // 第一个sheet页
        ExcelSheetContent firstSheetContent = new ExcelSheetContent();
        // 设置sheet页的名称
        firstSheetContent.setSheetName("第一个sheet页");
        List<TableContent> firstSheetTableList = new ArrayList<>();
        // 第一个sheet页里的第一个表格
        TableContent tableContent1 = createSheetFirstTable();
        // 第一个sheet页里的第二个表格
        TableContent tableContent2 = createSheetSecondTable();
        // 添加表格
        firstSheetTableList.add(tableContent1);
        firstSheetTableList.add(tableContent2);
        // 往第一个sheet页中，添加多个表格
        firstSheetContent.setTableContentList(firstSheetTableList);
        return firstSheetContent;
    }

    private ExcelSheetContent createSecondSheet() {
        // 第一个sheet页
        ExcelSheetContent firstSheetContent = new ExcelSheetContent();
        // 设置sheet页的名称
        firstSheetContent.setSheetName("第二个sheet页");
        List<TableContent> firstSheetTableList = new ArrayList<>();
        // 第一个sheet页里的第一个表格
        TableContent tableContent1 = createSheetFirstTable();
        // 第一个sheet页里的第二个表格
        TableContent tableContent2 = createSheetSecondTable();
        // 添加表格
        firstSheetTableList.add(tableContent1);
        firstSheetTableList.add(tableContent2);
        // 往第一个sheet页中，添加多个表格
        firstSheetContent.setTableContentList(firstSheetTableList);
        return firstSheetContent;
    }


    /**
     * 创建第一个表格
     * @return
     */
    private TableContent createSheetFirstTable() {
        TableContent tableContent = new TableContent();
        tableContent.setTitle("第一个sheet页的第一个表格");
        // 创建一个2行5列的表格
        int rowNum = 3, colNum = 5;
        CellContent[][] table = new CellContent[rowNum][colNum];
        table[0] = new CellContent[]{
                new CellContent.Builder().cellValue("用户").builder(),
                new CellContent.Builder().cellValue("性别").builder(),
                new CellContent.Builder().cellValue("得分").builder(),
                new CellContent.Builder().cellValue("评价").builder(),
                new CellContent.Builder().cellValue("状态").builder()
        };
        table[1] = new CellContent[]{
                new CellContent.Builder().cellValue("小a").builder(),
                new CellContent.Builder().cellValue("男").builder(),
                new CellContent.Builder().cellValue("21").builder(),
                new CellContent.Builder().cellValue("好").myCellBGColor(new MyCellColor(51, 204, 51)).builder(),
                new CellContent.Builder().cellValue("在职").builder()
        };
        table[2] = new CellContent[]{
                new CellContent.Builder().cellValue("小b").builder(),
                new CellContent.Builder().cellValue("男").builder(),
                new CellContent.Builder().cellValue("10").builder(),
                new CellContent.Builder().cellValue("差").myCellBGColor(new MyCellColor(255, 153, 102)).builder(),
                new CellContent.Builder().cellValue("在职").builder()
        };
        tableContent.setTable(table);
        // 添加标题颜色
        tableContent.setTitleBGColor(new MyCellColor(153, 255, 153));
        // 设置列宽
        tableContent.setColumnWidthsBase256(new int[]{20, 20, 20, 20, 20});
        // 设置标题居左
        tableContent.setTitleAlignment(HorizontalAlignment.LEFT);
        // 前两列的每一列，合并单元格
        tableContent.setMergeBeforeColNums(2);
        return tableContent;
    }


    /**
     * 创建第二个表格
     * @return
     */
    private TableContent createSheetSecondTable() {
        TableContent tableContent = new TableContent();
        tableContent.setTitle("第一个sheet页的第二个表格");
        // 创建一个2行5列的表格
        int rowNum = 3, colNum = 5;
        CellContent[][] table = new CellContent[rowNum][colNum];
        table[0] = new CellContent[]{
                new CellContent.Builder().cellValue("用户").builder(),
                new CellContent.Builder().cellValue("得分").builder(),
                new CellContent.Builder().cellValue("评价").builder(),
                new CellContent.Builder().cellValue("状态").builder()
        };
        table[1] = new CellContent[]{
                new CellContent.Builder().cellValue("小a").builder(),
                new CellContent.Builder().cellValue("21").builder(),
                new CellContent.Builder().cellValue("好").myCellBGColor(new MyCellColor(51, 204, 51)).builder(),
                new CellContent.Builder().cellValue("在职").builder()
        };
        table[2] = new CellContent[]{
                new CellContent.Builder().cellValue("小b").builder(),
                new CellContent.Builder().cellValue("10").builder(),
                new CellContent.Builder().cellValue("差").myCellBGColor(new MyCellColor(255, 153, 102)).builder(),
                new CellContent.Builder().cellValue("在职").builder()
        };
        tableContent.setTable(table);
        // 添加标题颜色
        tableContent.setTitleBGColor(new MyCellColor(153, 255, 153));
        // 设置列宽
        tableContent.setColumnWidthsBase256(new int[]{20, 20, 20, 20, 20});
        // 设置标题居左
        tableContent.setTitleAlignment(HorizontalAlignment.LEFT);
        return tableContent;
    }

}
