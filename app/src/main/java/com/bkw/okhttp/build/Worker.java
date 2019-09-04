package com.bkw.okhttp.build;

/**
 * 建筑工人
 */
public class Worker {

    //拿到图纸
    private DisignMap disignMap;

    public void setDisignMap(DisignMap disignMap) {
        this.disignMap = disignMap;
    }


    /**
     * 盖房子
     *
     * @return 盖好的房子交付
     */
    public House buildHouse() {
        //开始按照图纸盖房子
        House house = new House();
        house.setHeight(disignMap.getHeight());
        house.setWidth(disignMap.getWidth());

        return house;
    }

}
