package com.hef.domain;

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


}
