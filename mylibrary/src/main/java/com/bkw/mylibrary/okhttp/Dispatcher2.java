package com.bkw.mylibrary.okhttp;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 分发器
 * @author bkw
 */
public class Dispatcher2 {
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    /**
     * 防止等待队列
     */
    private final Deque<RealCall2.AsyncCall2> readyAsyncCalls = new ArrayDeque<>();

    /**
     * 防止运行队列
     */
    private final Deque<RealCall2.AsyncCall2> runningAsyncCalls = new ArrayDeque<>();


    public void enqueue(RealCall2.AsyncCall2 asyncCall2) {
        //同时运行的队列数必须小于maxRequests，并且同时访问同一个服务器域名，不能超过5个
        if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(asyncCall2) < maxRequests) {
            //加入执行队列
            runningAsyncCalls.add(asyncCall2);
            //执行
            executorService().execute(asyncCall2);
        } else {
            //加入等待队列
            readyAsyncCalls.add(asyncCall2);
        }
    }

    private ExecutorService executorService() {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(false);
                        thread.setName("自定义线程工厂");
                        return thread;
                    }
                });
        return executorService;
    }


    /**
     * 判断AsyncCall2中的host,在运行的队列中，计算数量并返回数量。
     * 返回正在运行的请求有几个域名。
     * Returns the number of running calls that share a host with {@code call}.
     */
    private int runningCallsForHost(RealCall2.AsyncCall2 call) {
        int result = 0;

        //用来解析请求得到请求的域名
        SocketRequestServer server = new SocketRequestServer();

        for (RealCall2.AsyncCall2 c : runningAsyncCalls) {
            //计算域名相同的请求
            if (server.getHost(c.getRequest2()).equals(call.getRequest2())) {
                result++;
            }
        }
        return result;
    }

    /**
     * 1.移除运行完成的任务
     * 2.把等待队列的所有任务执行
     *
     * @param asyncCall2
     */
    public void finished(RealCall2.AsyncCall2 asyncCall2) {
        //移除完成的任务
        runningAsyncCalls.remove(asyncCall2);

        //等待队列是否有任务
        if (readyAsyncCalls.isEmpty()) {
            return;
        }

        //把等待队列中的该任务移除
        for (Iterator<RealCall2.AsyncCall2> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
            RealCall2.AsyncCall2 asyncCall = i.next();

            if (runningAsyncCalls.size() >= maxRequests) {
                break; // Max capacity.
            }
            if (runningCallsForHost(asyncCall) >= maxRequestsPerHost) {
                continue; // Host max capacity.
            }

            i.remove();
            runningAsyncCalls.add(asyncCall);
            //开始执行任务
            executorService().execute(asyncCall);
        }
    }
}
