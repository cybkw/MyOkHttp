package com.bkw.mylibrary.okhttp;

import com.bkw.mylibrary.okhttp.chain.ChainManager;
import com.bkw.mylibrary.okhttp.chain.ConnectionInterceptor;
import com.bkw.mylibrary.okhttp.chain.HeaderInterceptor;
import com.bkw.mylibrary.okhttp.chain.Interceptor2;
import com.bkw.mylibrary.okhttp.chain.RetryRequestInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 真正执行请求的类
 *
 * @author bkw
 */
public class RealCall2 implements Call2 {
    /**
     * Guarded by this.
     */
    private boolean executed;

    /**
     * okHttp
     */
    private OkHttpClient2 client2;

    /**
     * 请求信息
     */
    private Request2 request2;

    public RealCall2(OkHttpClient2 client2, Request2 request2) {
        this.client2 = client2;
        this.request2 = request2;
    }

    public OkHttpClient2 getClient2() {
        return client2;
    }

    @Override
    public void enqueue(Callback2 responseCallback) {
        //不能重复执行
        synchronized (this) {
            if (executed) {
                executed = true;
                throw new IllegalStateException("Already Executed");
            }
        }

        client2.dispatcher().enqueue(new AsyncCall2(responseCallback));
    }

    public final class AsyncCall2 implements Runnable {

        public Request2 getRequest2() {
            return RealCall2.this.request2;
        }

        private Callback2 callback2;

        public AsyncCall2(Callback2 responseCallback) {
            this.callback2 = responseCallback;
        }

        @Override
        public void run() {
            //执行耗时操作
            boolean signalledCallback = false;
            try {
                Response2 response2 = getResponseWithInterceptorChain();
                //如果用户取消请求，告诉用户因取消请求而失败
                if (request2.isCancel()) {
                    signalledCallback = true;
                    callback2.onFailure(RealCall2.this, new IOException("用户取消了请求"));
                } else {
                    signalledCallback = true;
                    callback2.onResponse(RealCall2.this, response2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //责任划分，出现异常是用户还是OkHttp
                if (signalledCallback) {
                    System.out.println("用户在使用过程中，出错了。。");
                } else {
                    callback2.onFailure(RealCall2.this, new IOException("OkHttp getResponseWithInterceptorChain 错误，e:" + e.toString()));
                }
            } finally {

                client2.dispatcher().finished(this);
            }
        }

        public Response2 getResponseWithInterceptorChain() {

            List<Interceptor2> interceptor2s = new ArrayList<>();
            interceptor2s.add(new RetryRequestInterceptor());
            interceptor2s.add(new HeaderInterceptor());
            interceptor2s.add(new ConnectionInterceptor());
            ChainManager chainManager = new ChainManager(interceptor2s, 0, request2, RealCall2.this);

            return chainManager.getResponse(request2);
        }
    }
}
