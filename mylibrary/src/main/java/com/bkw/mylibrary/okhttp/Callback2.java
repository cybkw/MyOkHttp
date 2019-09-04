package com.bkw.mylibrary.okhttp;

import java.io.IOException;

/**
 * 请求结果回调方法
 * @author bkw
 */
public interface Callback2 {
    void onFailure(Call2 call, IOException e);

    void onResponse(Call2 call, Response2 response) throws IOException;
}
