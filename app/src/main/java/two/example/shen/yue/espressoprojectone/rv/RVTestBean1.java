package two.example.shen.yue.espressoprojectone.rv;

/**
 * Author: Queen
 * Date: 2020/5/11 8:17 AM
 * Describe: RVTestBean1
 */
public class RVTestBean1 {

    private String text = "";
    private int type = -1;

    public RVTestBean1() {

    }

    public RVTestBean1(String text) {
        this.text = text;
    }

    public RVTestBean1(int type) {
        this.type = type;
    }

    public RVTestBean1(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
