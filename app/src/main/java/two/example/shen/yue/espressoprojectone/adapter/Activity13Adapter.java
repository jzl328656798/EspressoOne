package two.example.shen.yue.espressoprojectone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Created by queen on 2018/8/7.
 * Author: Queen
 * Date: 2018/8/7
 * Time: 下午4:15
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity13Adapter extends BaseAdapter {

    private Context mContext;
    private List<String> list;

    public Activity13Adapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_actvity13, null);
            holder.text = convertView.findViewById(R.id.tv_item_activity13);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String str = list.get(position);
        holder.text.setText(str);

        return convertView;
    }


    static class ViewHolder {
        public TextView text;
    }
}
