package two.example.shen.yue.espressoprojectone.test17;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private LinkedBlockingDeque<Runnable> mQueen = new LinkedBlockingDeque<>();
    private DelayQueue<HttpTask> delayQueue = new DelayQueue<HttpTask>();
    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    public void addDelayTask(HttpTask task){
        if (task!=null){
            task.setDelayTime(3000);
            delayQueue.put(task);
        }
    }

    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    public void addTask(Runnable runnable) {
        if (runnable != null) {
            try {
                mQueen.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor =
                new ThreadPoolExecutor(
                        3,
                        5,
                        15,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(4),
                        (r, executor) -> addTask(r));

        mThreadPoolExecutor.execute(coreThread);
        mThreadPoolExecutor.execute(delayThread);
    }

    public Runnable coreThread = new Runnable() {
        Runnable run = null;
        @Override
        public void run() {
            while (true){
                try {
                    run = mQueen.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(run);
            }
        }
    };

    public Runnable delayThread = new Runnable() {
        HttpTask task = null;
        @Override
        public void run() {
            while (true){
                try {
                    task = delayQueue.take();
                    if (task.getRetryCount()<3){
                        mThreadPoolExecutor.execute(task);
                        task.setRetryCount(task.getRetryCount()+1);
                        System.out.println("正在重新请求:"+task.getRetryCount());
                    }else {
                        System.out.println("服务异常，次数上限，无法访问");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
