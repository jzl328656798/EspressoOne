package two.example.shen.yue.espressoprojectone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Created by queen on 2018/6/21.
 * Author: Queen
 * Date: 2018/6/21
 * Time: 下午4:58
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity7PopAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> list;

    public Activity7PopAdapter(Context mContext,List<String> list){
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_activity7_pop_adapter,null,false);
            holder = new ViewHolder();
            holder.tv_activity7_item = view.findViewById(R.id.tv_activity7_item);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }


    static class ViewHolder{
        public TextView tv_activity7_item;
    }
}

