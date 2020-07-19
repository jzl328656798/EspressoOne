package two.example.shen.yue.espressoprojectone.test17;

public interface IHttpRequest {

    void setUrl(String url);

    void setData(byte[] data);

    void setListener(CallbackListener callbackListener);

    void execute();

}
