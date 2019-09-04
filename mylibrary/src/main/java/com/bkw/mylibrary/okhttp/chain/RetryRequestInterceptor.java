package com.bkw.mylibrary.okhttp.chain;

import android.util.Log;

import com.bkw.mylibrary.okhttp.OkHttpClient2;
import com.bkw.mylibrary.okhttp.Response2;

import java.io.IOException;

/**
 * 重试请求拦截器
 *
 * @author bkw
 */
public class RetryRequestInterceptor implements Interceptor2 {

    @Override
    public Response2 doNext(Chain2 chain2) throws IOException {
        Log.e("TAG", "重试拦截器执行了");
        ChainManager chainManager = (ChainManager) chain2;

        OkHttpClient2 client2 = chainManager.getCall().getClient2();

        if (client2.getRetryCount() != 0) {
            for (int i = 0; i < client2.getRetryCount(); i++) {
                Log.e("TAG", "重试拦截器，Return 2");
                Response2 response2 = chainManager.getResponse(chainManager.getRequest());
                return response2;
            }
        }

//        return null;
        throw new IOException("chainManager is Null ");
    }

}
