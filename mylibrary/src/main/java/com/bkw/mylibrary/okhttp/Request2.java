package com.bkw.mylibrary.okhttp;

import com.bkw.mylibrary.okhttp.chain.RequestBody2;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求数据体
 *
 * @author bkw
 */
public class Request2 {

    public static final String GET = "GET";
    public static final String POST = "POST";

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方式
     */
    private String method = GET;

    /**
     * 请求头，请求属性集
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * 请求体
     */
    private RequestBody2 requestBody2;

    /**
     * 用户取消请求标识，默认false
     */
    private boolean isCancel;

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestBody2 getRequestBody2() {
        return requestBody2;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public Request2() {
        this(new Builder());
    }

    public Request2(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.isCancel = builder.isCancel;
        this.requestBody2=builder.requestBody2;
    }

    public final static class Builder {
        String url;
        String method = GET;
        Map<String, String> headers = new HashMap<>();
        RequestBody2 requestBody2;
        boolean isCancel;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder cancel() {
            isCancel = false;
            return this;
        }

        public Builder get() {
            this.method = GET;
            return this;
        }

        public Builder post(RequestBody2 requestBody2) {
            this.method = POST;
            this.requestBody2 = requestBody2;
            return this;
        }


        public Builder addRequestHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public Request2 build() {
            return new Request2(this);
        }
    }
}
