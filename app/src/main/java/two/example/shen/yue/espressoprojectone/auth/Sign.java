package two.example.shen.yue.espressoprojectone.auth;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by amytwang on 2016/9/6.
 */
public class Sign {

    /**
     app_sign	     时效性签名
     @param  appId	  申请的业务ID,由API提供方分配
     @param  method    调用的api名称
     @param  secret_key  申请的密钥key,由API提供方分配
     @param  expired	 签名过期时间
     @param  mySign	  生成的签名

     @return 0表示成功
     */
    public static int appSign(String appId, String method, String secret_key,long expired, StringBuffer mySign) {
        return appSignBase(appId, method, secret_key, expired, mySign);
    }


    private static int appSignBase(String appId, String method,String secret_key, long expired,StringBuffer mySign) {


        if (empty(appId) || empty(secret_key))
        {
            return -1;
        }


        long now = System.currentTimeMillis()/1000 ;

        String plain_text = "a=" + appId + "&m=" + method + "&t=" + now + "&e=" + expired  ;

        byte[] bin = hashHmac(plain_text, secret_key);

        byte[] all = new byte[bin.length + plain_text.getBytes().length];
        System.arraycopy(bin, 0, all, 0, bin.length);
        System.arraycopy(plain_text.getBytes(), 0, all, bin.length, plain_text.getBytes().length);
        mySign.append(Base64Utils.encode(all));

        return 0;
    }

    private static byte[] hashHmac(String plain_text, String accessKey) {

        try {
            return HMACSHA1.getSignature(plain_text, accessKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean empty(String s){
        return s == null || s.trim().equals("") || s.trim().equals("null");
    }

    /**
     MD5	  MD5算法的完整性校验签名（用于各接口的完整性校验）
     @param  input  要签名的接口参数串

     @return          MD5签名串
     */
    public static String MD5(String input) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }

        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}

