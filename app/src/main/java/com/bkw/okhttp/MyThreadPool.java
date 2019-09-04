package com.bkw.okhttp;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    public static void main(String[] args) {

        /**
         * @param 核心线程数 corePoolSize
         * @param 线程池最大线程数 maximumPoolSize
         * @param 空闲存活时间 keepAliveTime
         * @param 时间单位：时分秒 unit
         *            作用：Runnable1执行完毕后，闲置60s,60s过后，会回收掉Runnable1任务。
         *            如果在闲置时间内，会复用此线程Runnable1。
         *
         * @param 任务队列模式 workQueue
         *         LinkedBlockingQueue：把超出的任务加入到队列中，缓存起来。
         * @param 线程工厂 threadFactory the factory to use when the executor
         */
//        final ExecutorService executorService = new ThreadPoolExecutor(
//                1,
//                1,
//                60,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>()
//        );

//        final ExecutorService executorService = new ThreadPoolExecutor(
//                5,
//                1,
//                60,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>()
//        );

        //核心线程5，最大线程数10，这里执行20个线程任务。
        //在runnable1的闲置时间60s还有任务需要执行，就会复用runnable1
//        final ExecutorService executorService = new ThreadPoolExecutor(
//                5,
//                10,
//                60,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>()
//        );


        //实现缓存，线程池方案
        ExecutorService executorService = new ThreadPoolExecutor(
                0,
                Integer.MAX_VALUE,
                60,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        Thread thread = new Thread();
                        thread.setName("bkw");
                        thread.setDaemon(false);
                        return thread;
                    }
                }
        );


        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前线程，执行任务，线程是：" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //Java设计者，考虑到不使用线程池的参数配置，提供了API
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//
//        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

        //指定线程池数
//        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
    }
}
