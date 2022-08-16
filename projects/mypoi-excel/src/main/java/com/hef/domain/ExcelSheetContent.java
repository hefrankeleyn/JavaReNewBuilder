package com.hef.domain;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 一个sheet页的内容
 * @Date 2022/1/8
 * @Author lifei
 */
public class ExcelSheetContent {

    private String sheetName;

    private int[] columnWidthsBase256;
    // 一个sheet页里面有多个table
    private List<TableContent> tableContentList = new ArrayList<>();

    public int[] getColumnWidthsBase256() {
        if (columnWidthsBase256!=null) {
            return columnWidthsBase256.clone();
        }
        if (CollectionUtils.isNotEmpty(tableContentList)) {
            List<int[]> widthList = tableContentList.stream().map(TableContent::getColumnWidthsBase256)
                    .filter(Objects::nonNull).collect(Collectors.toList());
            int len = widthList.stream().map(item -> item.length).mapToInt(Integer::intValue).max().getAsInt();
            columnWidthsBase256 = new int[len];
            for (int k = 0; k < len; k++) {
                int mw = 0;
                for (int[] a : widthList) {
                    if (k<a.length) {
                        mw = Math.max(mw, a[k]);
                    }
                }
                columnWidthsBase256[k] = mw;
            }
        }
        if (columnWidthsBase256==null) {
            return null;
        }else {
            return columnWidthsBase256.clone();
        }
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<TableContent> getTableContentList() {
        return tableContentList;
    }

    public void setTableContentList(List<TableContent> tableContentList) {
        this.tableContentList = tableContentList;
    }

    private void setColumnWidthsBase256(int[] columnWidthsBase256) {
        this.columnWidthsBase256 = columnWidthsBase256;
    }

}
