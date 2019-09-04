package com.bkw.mylibrary.okhttp.chain;

import android.util.Log;

import com.bkw.mylibrary.okhttp.Request2;
import com.bkw.mylibrary.okhttp.Response2;
import com.bkw.mylibrary.okhttp.SocketRequestServer;

import java.util.Map;

/**
 * 请求头拦截器
 *
 * @author bkw
 */
public class HeaderInterceptor implements Interceptor2 {

    @Override
    public Response2 doNext(Chain2 chain2) throws Exception {

        Log.e("TAG", "请求头拦截器执行了");
        //拼接请求头 or 请求属性集等。
        ChainManager manager = (ChainManager) chain2;
        Request2 request = manager.getRequest();

        Map<String, String> headers = request.getHeaders();

        //无论get post它一定有域名
        headers.put("Host", new SocketRequestServer().getHost(request));

        if (request.getMethod().equalsIgnoreCase(Request2.POST)) {
            //POST的请求属性集与GET请求不一样。
            headers.put("Content-Length", request.getRequestBody2().getBody2string().length()+"");
            headers.put("Content-Type", RequestBody2.TYPE);
        }

        //执行下一个拦截器
        return manager.getResponse(request);
    }
}
