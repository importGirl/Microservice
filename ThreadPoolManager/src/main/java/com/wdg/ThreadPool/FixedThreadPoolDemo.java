package com.wdg.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		// 创建一个可重用固定线程数的线程池
        /**
         * 创建固定大小的线程池,每次提交一个任务就创建一个线程,直到线程达到线程池的最大大小
         * 线程池池的大小一旦达到最大值就会保持不变,如果某个线程因为执行异常而结束,那么线程池
         * 会补充一个新线程
         */
        ExecutorService pool = Executors.newFixedThreadPool(3);
        // 创建实现了Runnable接口对象,Thread对象当然也实现了Runnable接口
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
    }
}
