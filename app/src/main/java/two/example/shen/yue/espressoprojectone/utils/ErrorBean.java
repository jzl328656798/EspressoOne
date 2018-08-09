package two.example.shen.yue.espressoprojectone.utils;

import java.io.Serializable;

/**
 * Created by queen on 2018/7/30.
 * Author: Queen
 * Date: 2018/7/30
 * Time: 上午9:25
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class ErrorBean implements Serializable {

    private String storeName;
    private String SN;
    private String errorMsg;
    private String ALI_SLS_LOG_TYPE;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getALI_SLS_LOG_TYPE() {
        return ALI_SLS_LOG_TYPE;
    }

    public void setALI_SLS_LOG_TYPE(String ALI_SLS_LOG_TYPE) {
        this.ALI_SLS_LOG_TYPE = ALI_SLS_LOG_TYPE;
    }
}
