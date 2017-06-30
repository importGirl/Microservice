package com.wdg.ThreadPool;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class MyThread extends Thread {
	@Override
	public void run() {
//		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + "正在执行...");
//		}
	}
}
