package two.example.shen.yue.espressoprojectone.test17;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest httpRequest;

    private long delayTime;

    private int retryCount;

    public HttpTask(String url, T requestData, IHttpRequest httpRequest, CallbackListener listener) {
        this.httpRequest = httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(listener);
        String content = new Gson().toJson(requestData);
        httpRequest.setData(content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void run() {
        try {
            this.httpRequest.execute();
        }catch (Exception e){
            e.printStackTrace();
            ThreadPoolManager.getInstance().addDelayTask(this);
        }

    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
