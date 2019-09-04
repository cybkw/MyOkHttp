package com.bkw.mylibrary.okhttp;

/**
 * 构建请求
 *
 * @author bkw
 */
public class OkHttpClient2 {

    private Dispatcher2 dispatcher2;
    /**
     * 重试次数
     */
    private int retryCount = 3;

    public OkHttpClient2() {
        this(new Builder());
    }

    public OkHttpClient2(Builder builder) {
        dispatcher2 = builder.dispatcher2;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public final static class Builder {
        Dispatcher2 dispatcher2;
        /**
         * 重试次数
         */
        int retryCount = 3;

        public Builder() {
            dispatcher2 = new Dispatcher2();
        }

        public OkHttpClient2 build() {
            return new OkHttpClient2(this);
        }

        public Builder dispatcher(Dispatcher2 dispatcher2) {
            this.dispatcher2 = dispatcher2;
            return this;
        }

        public Builder setRetrycount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

    }

    public Call2 newCall(Request2 request2) {
        return new RealCall2(this, request2);
    }


    public Dispatcher2 dispatcher() {
        return dispatcher2;
    }
}
