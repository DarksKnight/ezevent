package com.ez.event.internal.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shy
 */
public class ThreadExecutors {

    private static final ThreadExecutors INSTANCE = new ThreadExecutors();

    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    private MainThread mainThread;

    private ThreadPoolExecutor threadPoolExecutor;

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private ThreadExecutors() {
        mainThread = new MainThread();
        threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(0);
    }

    private static Executor mainThread(int delay) {
        return INSTANCE.mainThread.delay(delay);
    }

    private static ExecutorService backgroundThread() {
        return INSTANCE.threadPoolExecutor;
    }

    private static ScheduledThreadPoolExecutor scheduledThread() {
        return INSTANCE.scheduledThreadPoolExecutor;
    }

    public static void run(ExecRunnable runnable, ThreadToken threadToken, int delay) {
        if (null == runnable) {
            return;
        }
        switch (threadToken) {
            case UI:
                mainThread(delay).execute(runnable);
                break;
            case BACKGROUND:
                if (delay > 0) {
                    scheduledThread().schedule(runnable, delay, TimeUnit.SECONDS);
                } else {
                    backgroundThread().execute(runnable);
                }
                break;
            default:
                runnable.execute();
                break;
        }
    }

    private final class MainThread implements Executor {

        private int delay = 0;

        public MainThread delay(int delay) {
            this.delay = delay;
            return this;
        }

        @Override
        public void execute(Runnable runnable) {
            mHandler.postDelayed(runnable, delay * 1000);
        }
    }
}
