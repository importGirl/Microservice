package com.wdg.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class CacheThreadPoolDemo {
    public static void main(String[] args) {
        /**
         * 创建一个可缓存式的线程池,线程池的大小完全依赖于操作系统(JVM)
         * 能够创建的最大线程大小
         */
        ExecutorService pool = Executors.newCachedThreadPool();
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        Thread t6 = new MyThread();
        Thread t7 = new MyThread();
        Thread t8 = new MyThread();
        Thread t9 = new MyThread();

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        pool.execute(t8);
        pool.execute(t9);

        pool.shutdown();

    }
}
