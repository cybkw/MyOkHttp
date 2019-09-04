
package com.bkw.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Callback {

    private String TAG = "MainActivity";

    public static final String URL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void execute(View view) throws IOException {

        Dispatcher dispatcher=new Dispatcher();

        OkHttpClient client = new OkHttpClient.Builder().build();


        Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();
        //Call接收
        Call call = client.newCall(request);
        //同步方法--需要开发者自己开启子线程
        Response response = call.execute();
        response.body().toString();
        Log.e(TAG, "execute:" + response.body().toString());
    }

    public void enqueue(View view) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();
        //Call接收
        Call call = client.newCall(request);

        //异步方法执行，不需要开发者开启子线程
        call.enqueue(this);

        call.cancel();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        //失败
        Log.e(TAG, "请求失败" + e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //成功
        Log.e(TAG, "enqueue:" + response.body().string());
    }


}
