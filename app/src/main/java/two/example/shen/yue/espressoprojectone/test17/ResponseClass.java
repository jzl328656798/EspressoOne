package two.example.shen.yue.espressoprojectone.test17;

import org.jetbrains.annotations.NotNull;

public class ResponseClass {
    private int resultCode;
    private String reason;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @NotNull
    @Override
    public String toString() {
        return "ResponseClass{" +
                "resultCode=" + resultCode +
                ", reason='" + reason + '\'' +
                '}';
    }
}
