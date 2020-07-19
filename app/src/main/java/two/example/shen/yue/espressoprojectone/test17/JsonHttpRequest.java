package two.example.shen.yue.espressoprojectone.test17;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] data;
    private CallbackListener callbackListener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    public void execute() {
        URL url = null;
        try {
            url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setUseCaches(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/json;charset-UTF-8");
            urlConnection.connect();
            OutputStream out = urlConnection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            bos.write(data);
            bos.flush();
            out.close();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = urlConnection.getInputStream();
                callbackListener.onSuccess(in);
            }else {
                throw new RuntimeException("运行失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败"+e.toString());
        }finally {
            urlConnection.disconnect();
        }
    }
}
