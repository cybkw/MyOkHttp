package com.bkw.okhttp.build;

/**
 * 房子图纸
 *
 * @author bkw
 */
public class DisignMap {

    private int width;
    private int height;

    public DisignMap() {
    }

    public DisignMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
