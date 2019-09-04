package com.bkw.okhttp.chain;

/**
 * 节点1
 * @author bkw
 */
public class Chain4 extends BaseChain {

    public Chain4(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {
        System.out.println("节点4 执行了任务。。");
    }
}
