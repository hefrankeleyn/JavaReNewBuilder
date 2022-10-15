package com.hef.domain;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Range;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * sheet页中的一个表格
 * @Date 2022/1/8
 * @Author lifei
 */
public class TableContent {

    // 标题
    private String title;
    // 表格内容
    private CellContent[][] table;

    // 当前表格宽度
    private int[] columnWidthsBase256;

    // 前几列合并单元格
    private int mergeBeforeColNums;

    /**
     * 这个表格的：从第几行到第几行。
     * 用于对复杂的单元格进行合并, 需要和 colRange 配合使用。
     * 范围从 1 开始
     */
    private Range<Integer> rowRange;

    /**
     * 这个表格的：从第几列到第几列
     * 用于对复杂的单元格进行合并， 需要和 rowRange 配合使用 ：
     */
    private Range<Integer> colRange;


    // 标题居中、居左的样式
    private HorizontalAlignment titleAlignment;
    private MyCellColor titleBGColor;

    public int[] getColumnWidthsBase256() {
        if (columnWidthsBase256==null) return null;
        return columnWidthsBase256.clone();
    }

    public void setColumnWidthsBase256(int[] columnWidthsBase256) {
        this.columnWidthsBase256=null;
        if (columnWidthsBase256!=null) {
            this.columnWidthsBase256 = columnWidthsBase256.clone();
        }
    }

    public int getMergeBeforeColNums() {
        return mergeBeforeColNums;
    }

    public void setMergeBeforeColNums(int mergeBeforeColNums) {
        this.mergeBeforeColNums = mergeBeforeColNums;
    }

    public HorizontalAlignment getTitleAlignment() {
        return titleAlignment;
    }

    public void setTitleAlignment(HorizontalAlignment titleAlignment) {
        this.titleAlignment = titleAlignment;
    }

    public MyCellColor getTitleBGColor() {
        return titleBGColor;
    }

    public void setTitleBGColor(MyCellColor titleBGColor) {
        this.titleBGColor = titleBGColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CellContent[][] getTable() {
        return table;
    }

    public void setTable(CellContent[][] table) {
        this.table = table;
    }

    public Range<Integer> getRowRange() {
        return rowRange;
    }

    public void setRowRange(Range<Integer> rowRange) {
        this.rowRange = rowRange;
    }

    public Range<Integer> getColRange() {
        return colRange;
    }

    public void setColRange(Range<Integer> colRange) {
        this.colRange = colRange;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(TableContent.class)
                .add("title", title)
                .add("table", table)
                .add("columnWidthsBase256", columnWidthsBase256)
                .add("mergeBeforeColNums", mergeBeforeColNums)
                .add("rowRange", rowRange)
                .add("colRange", colRange)
                .add("titleAlignment", titleAlignment)
                .add("titleBGColor", titleBGColor)
                .toString();
    }
}
