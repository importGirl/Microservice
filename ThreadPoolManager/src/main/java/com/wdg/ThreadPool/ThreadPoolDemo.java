package com.wdg.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		// 创建一个可重用固定线程数的线程池
		/**
		 * 1.new SingleThreadExecutor 为创建一个单线程的线程池,这个线程只有一个线程执行所有
         * 任务,如果这个唯一的线程异常中断了,那么就会有一个新的线程来替代它,
         * 2.线程池保证所有任务执行顺序按照任务的提交顺序执行
         */
		ExecutorService pool = Executors.newSingleThreadExecutor();
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
		// 关闭线程池

		pool.shutdown();
	}
}
