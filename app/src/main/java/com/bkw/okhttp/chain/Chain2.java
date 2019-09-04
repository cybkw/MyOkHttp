package com.bkw.okhttp.chain;

/**
 * 节点1
 * @author bkw
 */
public class Chain2 extends BaseChain {

    public Chain2(boolean isTask) {
        super(isTask);
    }

    @Override
    public void doAction() {

        System.out.println("节点2 执行了任务。。");
    }
}
