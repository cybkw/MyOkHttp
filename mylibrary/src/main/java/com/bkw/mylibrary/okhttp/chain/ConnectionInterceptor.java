package com.bkw.mylibrary.okhttp.chain;

import android.util.Log;

import com.bkw.mylibrary.okhttp.Request2;
import com.bkw.mylibrary.okhttp.Response2;
import com.bkw.mylibrary.okhttp.SocketRequestServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

/**
 * 拦截服务器拦截器
 *
 * @author bkw
 */
public class ConnectionInterceptor implements Interceptor2 {
    @Override
    public Response2 doNext(Chain2 chain2) throws Exception {

        Log.e("TAG", "服务器连接拦截器执行了");
        SocketRequestServer server = new SocketRequestServer();
        ChainManager chainManager = (ChainManager) chain2;
        Request2 request = chainManager.getRequest();

        /*优化，区分https与http请求*/
        Socket socket = null;
        String protocol = server.queryProtocol(request.getUrl());
        if (protocol != null) {
            if ("HTTP".equalsIgnoreCase(protocol)) {
                socket = new Socket(server.getHost(request), server.getPort(request));
            } else {
                socket = SSLSocketFactory.getDefault().createSocket(server.getHost(request), server.getPort(request));
            }
        }

        //发起请求
        OutputStream os = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        String headerInfo = server.getRequestHeaderInfo(request);
        Log.e("TAG", "请求头信息：" + headerInfo);
        bufferedWriter.write(headerInfo);
        bufferedWriter.flush();

        //响应结果
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread() {
            @Override
            public void run() {
                super.run();
                String readLine = null;
                while (true) {
                    try {
                        if ((readLine = bufferedReader.readLine()) != null) {
                            Log.e("TAG", "服务器响应：" + readLine);
                        } else {
                            return;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Response2 response2 = new Response2();
        //取出响应码,读取第一行，响应头信息
        String readLine = bufferedReader.readLine();
        //服务器响应的： HTTP/1.1 200 OK
        String[] strings = readLine.split(" ");
        //响应码
        response2.setCode(Integer.parseInt(strings[1]));

        //取出响应体,响应头空行之下的，就是响应体
        String readerline = null;
        try {
            while ((readerline = bufferedReader.readLine()) != null) {
                if ("".equals(readerline)) {
                    //读到空行了，就代表下面就是响应体了
                    response2.setBody(bufferedReader.readLine());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //因为这是最后一个拦截器，所以返回结果Response
        return response2;
    }
}
