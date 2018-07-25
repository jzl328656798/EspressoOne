package two.example.shen.yue.espressoprojectone.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.adapter.Activity7PopAdapter;

/**
 * Created by queen on 2018/6/21.
 * Author: Queen
 * Date: 2018/6/21
 * Time: 下午4:22
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class SelectPopupWindow {

    private Context mContext;
    private View contentView;
    private PopupWindow popupWindow;

    private ListView lv_pop;

    public SelectPopupWindow(Context mContext,int width, int height) {
        this.mContext = mContext;
        contentView = LayoutInflater.from(mContext).inflate(R.layout.item_activity7_pop, null, false);
        popupWindow = new PopupWindow(contentView, width, height);
        initWindow();
        initView();
    }

    private void initView() {
        lv_pop = contentView.findViewById(R.id.lv_pop);
    }

    public void setAdapter(Activity7PopAdapter adapter){
        lv_pop.setAdapter(adapter);
    }

    private void initWindow() {
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#50444444")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        popupWindow.showAsDropDown(anchor, xoff, yoff);
    }
}
