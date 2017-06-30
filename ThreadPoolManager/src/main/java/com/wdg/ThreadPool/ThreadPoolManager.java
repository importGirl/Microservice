package com.wdg.ThreadPool;

import java.util.concurrent.*;

/**
 * @author wangdg
 * @ClassName:
 * @Description: 线程池: newScheduledThreadPool()+ArrayBlockingQueue
 * @date 2017-06-11 00:50:22
 */
public class ThreadPoolManager {

    /** 线程池 */
    private ThreadPoolExecutor threadPool;
    /** 队列模式 */
    private ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);
    /** 核心线程数 */
    private static  int corePoolSize = Runtime.getRuntime().availableProcessors()+2;
    /** 最大线程数 */
    private static int maximumPoolSize = Runtime.getRuntime().availableProcessors()*2+2;
    /** 保持心跳时间 */
    private static int keepAliveTime = 1;
    /** 定时执行线程个数 */
    private final static int minSchedule = 2;
    /** 延时执行线程 */
    private ScheduledExecutorService appSchedule;
    /** 线程池实例化 */
    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();
    /** 构造器 */
    private ThreadPoolManager() {
        // 抛出异常专用
        RejectedExecutionHandler myHandler = new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                taskQueue.offer(r);
            }
        };
        // 任务
        Runnable command = new Runnable() {
            public void run() {
                Runnable task = null;
                try {
                    task = taskQueue.take();//使用具备阻塞特性的方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                threadPool.execute(task);
            }
        };
        // 线程池的实现
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);
        // 每一次执行终止和下一次执行开始之间都存在给定的延迟35毫秒
        scheduledPool.scheduleWithFixedDelay(command, 0L, 35L, TimeUnit.MILLISECONDS);
        new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(20), new NamedThreadFactory("CoreImplServiceHandler"), myHandler) {
            public void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                PrintException(r, t);
            }
        };
        appSchedule = Executors.newScheduledThreadPool(minSchedule);
    }
    /** 打印异常 */
    private static void PrintException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            Future<?> future = (Future<?>)r;
            if(future.isDone())
                try {
                    future.get();// 获取方法的异常
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();// 中断线程
                } catch (ExecutionException e) {
                    t = e.getCause();
                } catch (CancellationException ce) {
                    t = ce;
                }
        }
        if(t!=null){
            System.out.println("系統自有线程池异常,error_msg==" +t+ t.getMessage());
        }
    }

    /**
     * 功能描述: 得到线程池的实例
     * @return
     */
    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    /**
     * 功能描述: 获取活动的线程数
     * @return
     */
    public int getActiveCount(){
        return threadPool.getActiveCount();
    }

    /**
     * 功能描述: 获取核心线程数
     * @return
     */
    public int getCorePoolSize(){
        return threadPool.getCorePoolSize();
    }

    /**
     * 功能描述: 任务添加到线程池中
     * @param paramRunnable
     * @return
     */
    public Future<?> addExecuteTask(Runnable paramRunnable){
        if (paramRunnable == null) {
            return null;
        }
        // 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
        return this.threadPool.submit(paramRunnable);
    }

    /**
     *  功能描述: 延时任务添加到线程池中
     * @param task
     * @param delayTime
     */
    public void addDelayExecuteTask(Runnable task, int delayTime) {
        appSchedule.schedule(new DelayTask(task), delayTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 功能描述: 延时固定周期执行
     * @param task 周期性执行的任务
     * @param delay 第一次开始延时的时间
     * @param period 周期性执行间隔时间
     */
    public void addPeriodDelayExecuteTask(Runnable task,Long delay,Long period){
        this.appSchedule.scheduleWithFixedDelay(new DelayTask(task), delay, period, TimeUnit.MILLISECONDS);
    }
    /** 延时任务 */
    class DelayTask implements Runnable{
        private Runnable task;
        public DelayTask(Runnable paramTask){
            this.task = paramTask;
        }
        public void run() {
            ThreadPoolManager.getInstance().addExecuteTask(task);
        }
    }

    public static void main(String[] args) {
        ThreadPoolManager pool = ThreadPoolManager.getInstance();
        System.out.println("线程池:----->" + pool);
//        System.out.println("活动线程数:----------->"+pool.getActiveCount());
        System.out.println("核心线程数:---------->" + corePoolSize);
        Thread t1 = new MyThread();

        pool.addExecuteTask(t1);
//        pool.addPeriodDelayExecuteTask(t1,1000L,1000L);
    }


 }

























