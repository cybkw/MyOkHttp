package com.bkw.okhttp.chain;

public class TestChain {

    public static void main(String[] args) {
        BaseChain chain1 = new Chain1(false);
        BaseChain chain2 = new Chain2(false);
        BaseChain chain3 = new Chain3(true);
        BaseChain chain4 = new Chain4(false);

        chain1.addNextTask(chain2);
        chain2.addNextTask(chain3);
        chain3.addNextTask(chain4);

        //执行第一个节点
        chain1.action();
    }
}
