package com.bkw.okhttp.chain2;

public interface IBaseTask {
    /**
     * @param isTask   任务节点是否有能力执行
     * @param nextTask 下一个任务节点
     */
    void doRunAction(String isTask, IBaseTask nextTask);
}
