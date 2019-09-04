package com.bkw.mylibrary.okhttp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * 解析请求的对象
 *
 * @author bkw
 */
public class SocketRequestServer {

    /**
     * 空格
     */
    private final String SPACE = " ";
    /**
     * 版本
     */
    private final String VIERSION = "HTTP/1.1";

    private final String GRGN = "\r\n";

    /**
     * 通过Request对象得到域名Host。
     *
     * @param request2
     * @return
     */
    public String getHost(Request2 request2) {

        try {
            URL url = new URL(request2.getUrl());
            return url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("getHost 域名失败");
    }

    public int getPort(Request2 request2) {
        try {
            URL url = new URL(request2.getUrl());

            int port = url.getPort();

            return port == -1 ? url.getDefaultPort() : port;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取请求头信息
     *
     * @param request2
     * @return
     */
    public String getRequestHeaderInfo(Request2 request2) {
        //得到请求方式
        URL url = null;
        try {
            url = new URL(request2.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String file = url.getFile();

        //GET / HTTP/1.1
        //Host: www.baidu.com
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(request2.getMethod())
                //空格
                .append(SPACE)
                .append(file)
                .append(SPACE)
                .append(VIERSION)
                .append(GRGN);

        //获取请求集，进行拼接
        if (!request2.getHeaders().isEmpty()) {
            Map<String, String> map = request2.getHeaders();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append(":")
                        .append(SPACE)
                        .append(entry.getValue())
                        .append(GRGN);
            }
            //拼接空行
            stringBuffer.append(GRGN);
        }

        //POST请求才有 请求体的拼接的需求
        if (Request2.POST.equalsIgnoreCase(request2.getMethod())) {
            stringBuffer.append(request2.getRequestBody2().getBody2string()).append(GRGN);
        }

        return stringBuffer.toString();
    }

    /**
     * 返回服务器地址属于什么协议http or https
     *
     * @param path
     * @return
     */
    public String queryProtocol(String path) {
        try {
            URL url = new URL(path);
            return url.getProtocol();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
