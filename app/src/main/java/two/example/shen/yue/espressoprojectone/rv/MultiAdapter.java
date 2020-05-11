package two.example.shen.yue.espressoprojectone.rv;

import java.util.List;

/**
 * Author: Queen
 * Date: 2020/5/11 8:45 AM
 * Describe: MultiAdapter
 */
public class MultiAdapter extends RViewAdapter<RVTestBean1> {
    public MultiAdapter(List<RVTestBean1> data) {
        super(data);
        addStyles(new RVTestAdapter1());
        addStyles(new RVTestAdapter2());
        addStyles(new RVTestAdapter3());
        addStyles(new RVTestAdapter4());
        addStyles(new RVTestAdapter5());
    }
}
