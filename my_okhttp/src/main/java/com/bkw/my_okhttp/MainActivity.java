package com.bkw.my_okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bkw.mylibrary.okhttp.Call2;
import com.bkw.mylibrary.okhttp.Callback2;
import com.bkw.mylibrary.okhttp.OkHttpClient2;
import com.bkw.mylibrary.okhttp.Request2;
import com.bkw.mylibrary.okhttp.Response2;
import com.bkw.mylibrary.okhttp.chain.RequestBody2;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //    private static final String URL = "http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=13cb58f5884f9749287abbead9c658f2";
    private static final String URL = "http://restapi.amap.com/v3/weather/weatherInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void useOkHttp(View view) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url(URL)
                .get().build();


        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败。。。");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("请求成功。。。result=" + response.body().string());
            }
        });
    }

    /**
     * 自定义OkHttp
     *
     * @param view
     */
    public void useMyOkHttp(View view) {

        OkHttpClient2 okHttpClient = new OkHttpClient2.Builder().build();

        //GET
//        Request2 request2 = new Request2.Builder().url(URL).build();

        //POST
        RequestBody2 body2 = new RequestBody2();
        body2.addBody("city", "110101");
        body2.addBody("key", "13cb58f5884f9749287abbead9c658f2");
        Request2 request2 = new Request2.Builder().post(body2).url(URL).build();

        Call2 call2 = okHttpClient.newCall(request2);

        call2.enqueue(new Callback2() {
            @Override
            public void onFailure(Call2 call, IOException e) {
                System.out.println("请求失败。。。");
            }

            @Override
            public void onResponse(Call2 call, Response2 response) throws IOException {
                System.out.println("请求成功。。。result=" + response.getBody());
            }
        });
    }
}
