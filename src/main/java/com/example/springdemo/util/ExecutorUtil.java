package com.example.springdemo.util;

import com.alibaba.nacos.client.config.impl.ClientWorker;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExecutorUtil implements Cloneable{

    private String name;
    final ScheduledExecutorService executor;
    final ScheduledExecutorService executorService;

    public ExecutorUtil(String name) {
        this.name=name;
        this.executor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("MyDoSomethingThread." + name);
                //设置为守护线程
                t.setDaemon(true);
                return t;
            }
        });
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("MyDoSomethingThread." + name);
                //设置为守护线程
                t.setDaemon(true);
                return t;
            }
        });

        //1毫秒后开始，可以每隔10毫秒向executorService提交一个任务，任务时间不影响间隔
        this.executor.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    log.info("doSomeThing");
                } catch (Throwable var2) {
                    log.error("error");
                }

            }
        }, 1L, 10L, TimeUnit.MILLISECONDS);
    }
}
