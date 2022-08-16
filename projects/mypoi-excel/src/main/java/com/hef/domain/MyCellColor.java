package com.hef.domain;

/**
 * 单元格的背景颜色
 * @Date 2022/1/22
 * @Author lifei
 */
public class MyCellColor {

    private int red;
    private int green;
    private int blue;

    public MyCellColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
