package com.wdg.ThreadPool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdg
 * @ClassName:
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        /**
         * 1.创建一个大小无限的线程池
         * 2.此线程支持定时以及周期性执行任务的需求
         */
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() { // 每隔一段时间就触发异常
            public void run() {
                System.out.println("===========================");

            }
        },1000,5000, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间就打印系统时间,证明两者是互不影响的
            public void run() {
                System.out.println(System.nanoTime());
            }
        },1000,2000,TimeUnit.MILLISECONDS);
    }
}
