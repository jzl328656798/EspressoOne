package two.example.shen.yue.espressoprojectone.test17;

public class HttpManager {

    public static <T,M> void sendJsonRequest(String url ,T requestData,Class<M> response,IJsonDataTransform iJsonDataTransform){
        IHttpRequest httpRequest = new JsonHttpRequest();
        CallbackListener listener = new JsonCallListener<>(response,iJsonDataTransform);
        HttpTask task = new HttpTask(url,requestData,httpRequest,listener);
        ThreadPoolManager.getInstance().addTask(task);

    }

}
