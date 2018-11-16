package two.example.shen.yue.espressoprojectone.auth;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * CreateTime: 2018/11/14 - 下午12:19
 * Author: 姜卓磊
 * Remark: TODO
 */
public class NetManager {
    private static NetManager mInstance = null;
    public static synchronized NetManager getInstance() {
        if(mInstance == null){
            mInstance = new NetManager();
        }
        return mInstance;
    }

    public NetManager() {}

    //获取活体检测结果
    public JSONObject getDetectInfo(String token) {
        try {
            StringBuilder params = new StringBuilder("token=");
            params.append(token);
            params.append("&appid=");
            params.append(AppConfig.APP_ID);
            String input = token+'-' + AppConfig.APP_ID + "-authkey";
            String sig = Sign.MD5(input); //sig算法 MD5(参数1-参数2...-私钥key) ，其中私钥key="authkey"
            params.append("&sig=");
            params.append(sig);
            JSONObject response = SendHttpRequest(params.toString(), "getdetectinfo");
            return response;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject SendHttpRequest(String params, String method)
    {

        StringBuffer mySign = new StringBuffer("");
        Sign.appSign(AppConfig.APP_ID, method, AppConfig.SECRET_KEY,AppConfig.EXPIRED_SECONDS,mySign);

        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");

        DataOutputStream out = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(AppConfig.IDENTITY_SDK_URL + method + ".php");
            connection = (HttpURLConnection) url.openConnection();

            // set header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("signature", mySign.toString());

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            // POST请求
            out = new DataOutputStream(
                    connection.getOutputStream());

            out.write(params.getBytes("utf-8"));
            out.flush();
            out.close();

            if (connection.getResponseCode() == 200) {
                // 读取响应
                reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String lines;
                StringBuffer resposeBuffer = new StringBuffer("");
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    resposeBuffer.append(lines);
                }
                reader.close();

                // 断开连接
                connection.disconnect();

                JSONObject respose = new JSONObject(resposeBuffer.toString());

                return respose;
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

            // 断开连接
            if (connection!= null ) {
                connection.disconnect();
                connection = null;
            }
        }

        return null;

    }
}
