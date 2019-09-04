package com.bkw.okhttp.chain;

/**
 * @author bkw
 */
public abstract class BaseChain {

    /**
     * 判断当前任务节点，有没有能力执行
     */
    private boolean isTask;

    /**
     * 执行下一个节点
     */
    private BaseChain nextTask;


    public BaseChain(boolean isTask) {
        this.isTask = isTask;
    }

    /**
     * 添加下一个节点任务
     */
    public void addNextTask(BaseChain nextTask) {
        this.nextTask = nextTask;
    }


    /**
     * 定义行为，具体让子节点去执行任务
     */
    public abstract void doAction();

    /**
     * 执行任务
     */
    public void action() {
        if (isTask) {
            //子节点执行 链条断开，不再继续向后传递
            doAction();
        } else {
            //继续执行下一个任务节点
            if (nextTask != null) {
                nextTask.action();
            }
        }
    }
}
