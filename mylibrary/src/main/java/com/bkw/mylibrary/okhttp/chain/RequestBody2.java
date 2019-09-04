package com.bkw.mylibrary.okhttp.chain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求体对象
 *
 * @author bkw
 */
public class RequestBody2 {

    public static final String UTF8 = "UTF-8";
    /**
     * 表单提交
     */
    public static final String TYPE = "application/x-www-form-urlencoded";

    /**
     * 请求体集合
     */
    Map<String, String> bodys = new HashMap<>();

    public void addBody(String key, String value) {
        try {
            //需要URL编码
            bodys.put(URLEncoder.encode(key, UTF8), URLEncoder.encode(value, UTF8));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getBodys() {
        return bodys;
    }

    public String getBody2string() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> stringEntry : bodys.entrySet()) {
            stringBuffer.append(stringEntry.getKey())
                    .append("=")
                    .append(stringEntry.getValue())
                    .append("&");
        }

        if (stringBuffer.length() != 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }
}
