package com.bkw.okhttp.chain;

/**
 * 节点1
 * @author bkw
 */
public class Chain3 extends BaseChain {
    public Chain3(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("节点3 执行了任务。。");
    }
}
