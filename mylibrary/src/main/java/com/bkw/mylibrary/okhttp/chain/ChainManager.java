package com.bkw.mylibrary.okhttp.chain;

import com.bkw.mylibrary.okhttp.RealCall2;
import com.bkw.mylibrary.okhttp.Request2;
import com.bkw.mylibrary.okhttp.Response2;

import java.util.List;

/**
 * 拦截器管理类
 *
 * @author bkw
 */
public class ChainManager implements Chain2 {

    private final List<Interceptor2> interceptors;
    private int index;
    /**
     * 请求头拦截器，更新Request
     */
    private final Request2 request;
    private final RealCall2 call;

    public ChainManager(List<Interceptor2> interceptors, int index, Request2 request, RealCall2 call) {
        this.interceptors = interceptors;
        this.request = request;
        this.index = index;
        this.call = call;
    }

    @Override
    public Response2 getResponse(Request2 request2) {
        //index++ 计数，不能大于size不能等于
        if (index >= interceptors.size()) {
            throw new AssertionError();
//            return null;
        }

        if (interceptors.isEmpty()) {
            throw new IllegalArgumentException("interceptors is null");
        }


        //取出第一个拦截器
        //index默认为0
        Interceptor2 interceptor2 = interceptors.get(index);

        //这里index+1表示执行下一个
        ChainManager chainManager = new ChainManager(interceptors, index+1, request2, call);

        //执行下一个
        Response2 response2 = null;
        try {
            response2 = interceptor2.doNext(chainManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response2;
    }

    @Override
    public Request2 getRequest() {
        return request;
    }

    public RealCall2 getCall() {
        return call;
    }
}


