package two.example.shen.yue.espressoprojectone.rv;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Author: Queen
 * Date: 2020/5/9 8:40 AM
 * Describe: RecyclerView.ViewHolder 封装
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    /**
     * View 控件集合
     */
    private SparseArray<View> mViews;

    /**
     * 当前条目View
     */
    private View mConvertView;

    RViewHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 初始化入口
     */
    static RViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RViewHolder(itemView);
    }

    /**
     * 根据id 获取View
     */
    <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取条目view
     */
    View getConvertView() {
        return mConvertView;
    }

}
