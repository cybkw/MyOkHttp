package com.bkw.connectionspool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String[] args) {
    /*    ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("正在运行的线程：" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    */
    }

    final ConnectionPool connectionPool = new ConnectionPool();
    UseConnectionPool useConnectionPool = new UseConnectionPool();

    public void request(View view) {
        //测试连接池复用
        new Thread() {
            @Override
            public void run() {
                super.run();
                useConnectionPool.useConnectionPool(connectionPool, "restapi.amap.com", 80);
            }
        }.start();

    }

}
