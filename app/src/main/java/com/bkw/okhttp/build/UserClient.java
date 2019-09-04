package com.bkw.okhttp.build;

/**
 * 用户
 *
 * @author bkw
 */
public class UserClient {
//    public static void main(String[] args) {
//        //找建筑公司，建造房子-->设计师
//        DisignPerson disignPerson=new DisignPerson();
//
//        //画图纸的过程-高度
//        disignPerson.addHeight(4);
//        //面积
//        disignPerson.addWidth(120);
//
//        //构建者模式，灵活的使用配置方式，分解复杂的过程
//
//        //盖房子的过程
//        House house = disignPerson.build();
//
//        System.out.println(house.toString());
//    }

    /**
     *第二种方式： 链式调用
     * */
    public static void main(String[] args){
        //设计师的方法返回自己。
        House house=new DisignPerson().addHeight(12).addWidth(220).build();

        System.out.println(house.toString());
    }
}
