package com.bkw.mylibrary.okhttp.chain;

import com.bkw.mylibrary.okhttp.Response2;

/**
 * 链条上的每个节点需要做的动作接口定义
 * @author bkw
 */
public interface Interceptor2 {
    Response2 doNext(Chain2 chain2) throws Exception;
}
