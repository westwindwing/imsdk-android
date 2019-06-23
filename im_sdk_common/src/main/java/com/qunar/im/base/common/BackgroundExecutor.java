package com.qunar.im.base.common;

import android.os.Looper;
import com.qunar.im.base.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BackgroundExecutor {
    //modify by hqlin
    public static Executor DEFAULT_EXECUTOR = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "Qtakl Upload task" + this.mCount.getAndIncrement());
        }
    }, new DiscardOldestPolicy());
    public static final WrongThreadListener DEFAULT_WRONG_THREAD_LISTENER = new WrongThreadListener() {
        public void onUiExpected() {
        }

        public void onBgExpected(String... expectedSerials) {
            if (expectedSerials.length == 0) {
            }
        }

        public void onWrongBgSerial(String currentSerial, String... expectedSerials) {
        }
    };
    private static final String TAG = "CommonBackgroundExecutor";
    private static final ThreadLocal<String> currentSerial = new ThreadLocal();
    private static Executor executor = DEFAULT_EXECUTOR;
    private static final int maxWorkCount = ((Runtime.getRuntime().availableProcessors() * 2) + 8);
    private static final List<Task> tasks = new ArrayList();
    private static WrongThreadListener wrongThreadListener = DEFAULT_WRONG_THREAD_LISTENER;

    public interface WrongThreadListener {
        void onBgExpected(String... strArr);

        void onUiExpected();

        void onWrongBgSerial(String str, String... strArr);
    }

    public static abstract class Task implements Runnable {
        private boolean executionAsked;
        private Future<?> future;
        private String id;
        private AtomicBoolean managed = new AtomicBoolean();
        private int remainingDelay;
        private String serial;
        private long targetTimeMillis;

        public abstract void execute();

        public Task(String id, int delay, String serial) {
            if (!"".equals(id)) {
                this.id = id;
            }
            if (delay > 0) {
                this.remainingDelay = delay;
                this.targetTimeMillis = System.currentTimeMillis() + ((long) delay);
            }
            if (!"".equals(serial)) {
                this.serial = serial;
            }
        }

        public void run() {
            if (!this.managed.getAndSet(true)) {
                try {
                    BackgroundExecutor.currentSerial.set(this.serial);
                    execute();
                } finally {
                    postExecute();
                }
            }
        }

        private void postExecute() {
            if (this.id != null || this.serial != null) {
                BackgroundExecutor.currentSerial.set(null);
                synchronized (BackgroundExecutor.class) {
                    BackgroundExecutor.tasks.remove(this);
                    if (this.serial != null) {
                        Task next = BackgroundExecutor.take(this.serial);
                        if (next != null) {
                            if (next.remainingDelay != 0) {
                                next.remainingDelay = Math.max(0, (int) (this.targetTimeMillis - System.currentTimeMillis()));
                            }
                            BackgroundExecutor.execute(next);
                        }
                    }
                }
            }
        }
    }

    static {
        ((ScheduledThreadPoolExecutor) DEFAULT_EXECUTOR).setKeepAliveTime(15, TimeUnit.SECONDS);
        ((ScheduledThreadPoolExecutor) DEFAULT_EXECUTOR).allowCoreThreadTimeOut(true);
    }

    public static Executor getExecutor() {
        return executor;
    }

    private static Future<?> directExecute(Runnable runnable, int delay) {
        if (delay > 0) {
            if (executor instanceof ScheduledExecutorService) {
                //return executor.schedule(runnable, (long) delay, TimeUnit.MILLISECONDS);
                return null;
            }
            throw new IllegalArgumentException("The executor set does not support scheduling");
        } else if (executor instanceof ExecutorService) {
            //return executor.submit(runnable);
            return null;
        } else {
            executor.execute(runnable);
            return null;
        }
    }

    public static synchronized void execute(Task task) {
        synchronized (BackgroundExecutor.class) {
            Future<?> future = null;
            if (task.serial == null || !hasSerialRunning(task.serial)) {
                task.executionAsked = true;
                future = directExecute(task, task.remainingDelay);
            }
            if (!(task.id == null && task.serial == null)) {
                task.future = future;
                tasks.add(task);
            }
        }
    }

    public static void execute(final Runnable runnable, String id, int delay, String serial) {
        execute(new Task(id, delay, serial) {
            public void execute() {
                runnable.run();
            }
        });
    }

    public static void execute(Runnable runnable, int delay) {
        directExecute(runnable, delay);
    }

    public static void execute(Runnable runnable) {
        directExecute(runnable, 0);
    }

    public static void execute(Runnable runnable, String id, String serial) {
        execute(runnable, id, 0, serial);
    }

    public static void setExecutor(Executor executor) {
        executor = executor;
    }

    public static void setWrongThreadListener(WrongThreadListener listener) {
        wrongThreadListener = listener;
    }

    public static synchronized void cancelAll(String id, boolean mayInterruptIfRunning) {
        synchronized (BackgroundExecutor.class) {
            for (int i = tasks.size() - 1; i >= 0; i--) {
                Task task = (Task) tasks.get(i);
                if (id.equals(task.id)) {
                    if (task.future != null) {
                        task.future.cancel(mayInterruptIfRunning);
                        if (!task.managed.getAndSet(true)) {
                            task.postExecute();
                        }
                    } else if (task.executionAsked) {
                        LogUtil.w("CommonBackgroundExecutor", "A task with id " + task.id + " cannot be cancelled (the executor set does not support it)");
                    } else {
                        tasks.remove(i);
                    }
                }
            }
        }
    }

    public static void checkUiThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            wrongThreadListener.onUiExpected();
        }
    }

    public static void checkBgThread(String... serials) {
        if (serials.length != 0) {
            String current = (String) currentSerial.get();
            if (current == null) {
                wrongThreadListener.onWrongBgSerial(null, serials);
                return;
            }
            int length = serials.length;
            int i = 0;
            while (i < length) {
                if (!serials[i].equals(current)) {
                    i++;
                } else {
                    return;
                }
            }
            wrongThreadListener.onWrongBgSerial(current, serials);
        } else if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            wrongThreadListener.onBgExpected(serials);
        }
    }

    private static boolean hasSerialRunning(String serial) {
        for (Task task : tasks) {
            if (task.executionAsked && serial.equals(task.serial)) {
                return true;
            }
        }
        return false;
    }

    private static Task take(String serial) {
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            if (serial.equals(((Task) tasks.get(i)).serial)) {
                return (Task) tasks.remove(i);
            }
        }
        return null;
    }
}
