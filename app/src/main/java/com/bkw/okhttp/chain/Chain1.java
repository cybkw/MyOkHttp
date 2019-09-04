package com.bkw.okhttp.chain;

/**
 * 节点1
 * @author bkw
 */
public class Chain1 extends BaseChain {
    public Chain1(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("节点1执行了任务。。");
    }
}
