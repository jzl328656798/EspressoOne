package two.example.shen.yue.espressoprojectone.test17;


import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonCallListener<T> implements CallbackListener {

    private Class<T> responseClass;
    private IJsonDataTransform<T> iJsonDataTransform;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallListener(Class<T> responseClass,IJsonDataTransform<T> iJsonDataTransform) {
        this.responseClass = responseClass;
        this.iJsonDataTransform = iJsonDataTransform;
    }

    @Override
    public void onSuccess(InputStream inputStream) {

        String response = getContent(inputStream);
        T t = new Gson().fromJson(response, responseClass);
        handler.post(() -> {
            iJsonDataTransform.onSuccess(t);
        });

    }

    private String getContent(InputStream inputStream) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error=" + e.toString());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error=" + e.toString());
            }
        }
        return sb.toString();
    }


    @Override
    public void onFailure() {

    }
}
