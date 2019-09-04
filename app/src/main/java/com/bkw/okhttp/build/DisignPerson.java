package com.bkw.okhttp.build;

/**
 * 建筑设计师
 *
 * @author bkw
 */
public class DisignPerson {

    //图纸
    private DisignMap disignMap;

    //建筑工人
    private Worker worker;

    /**
     * 设计师拥有画图的能力
     */
    public DisignPerson() {
        disignMap = new DisignMap();
        worker = new Worker();
    }

    /**
     * 设计楼层高度-画图纸
     */
    public DisignPerson addHeight(int height) {
        disignMap.setHeight(height);
        return this;
    }

    /**
     * 设计楼层宽度-画图纸
     */
    public DisignPerson addWidth(int width) {
        disignMap.setWidth(width);
        return this;
    }

    /**
     * 设计师将图纸交给工人。
     * 工人将盖好的房子交付。
     *
     * @return
     */
    public House build() {
        //设计师将图纸交给工人。
        worker.setDisignMap(disignMap);
        //  * 工人将盖好的房子交付。
        return worker.buildHouse();
    }
}
