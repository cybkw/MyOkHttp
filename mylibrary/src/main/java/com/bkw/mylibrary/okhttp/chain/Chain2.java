package com.bkw.mylibrary.okhttp.chain;

import com.bkw.mylibrary.okhttp.Request2;
import com.bkw.mylibrary.okhttp.Response2;

import java.io.IOException;

/**
 * 链条接口
 * @author bkw
 */
public interface Chain2 {
    Request2 getRequest();

    Response2 getResponse(Request2 request2) throws IOException;

}
