package com.hef.domain;

/**
 * 单元格的内容
 * @Date 2022/1/8
 * @Author lifei
 */
public class CellContent {

    private Object cellValue;
    private MyCellColor myCellBGColor;

    public CellContent(){}
    private CellContent(Builder builder){
        this.cellValue = builder.cellValue;
        this.myCellBGColor = builder.myCellBGColor;
    }

    public static class Builder {
        private Object cellValue;
        private MyCellColor myCellBGColor;

        public Builder cellValue(Object cellValue){this.cellValue = cellValue; return this;}
        public Builder myCellBGColor(MyCellColor myCellBGColor){this.myCellBGColor = myCellBGColor; return this;}

        public CellContent builder() {
            return new CellContent(this);
        }
    }

    public Object getCellValue() {
        return cellValue;
    }

    public void setCellValue(Object cellValue) {
        this.cellValue = cellValue;
    }

    public MyCellColor getMyCellBGColor() {
        return myCellBGColor;
    }

    public void setMyCellBGColor(MyCellColor myCellBGColor) {
        this.myCellBGColor = myCellBGColor;
    }
}
