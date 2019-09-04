package com.bkw.okhttp.build;

/**
 * 真实的房子
 */
public class House {
    private int width;
    private int height;

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

    @Override
    public String toString() {
        return "House{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
