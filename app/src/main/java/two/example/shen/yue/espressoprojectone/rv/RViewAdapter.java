package two.example.shen.yue.espressoprojectone.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import two.example.shen.yue.espressoprojectone.RViewItemManager;

/**
 * Author: Queen
 * Date: 2020/5/9 8:56 AM
 * Describe: RV
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private ItemListener<T> itemListener;

    private List<T> data;

    private RViewItemManager<T> itemStyle;

    public RViewAdapter(List<T> data, RViewItem<T> item) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        itemStyle = new RViewItemManager<>();
        addStyles(item);
    }

    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

    private void addStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    private boolean hasMultiStyle() {
        return itemStyle.getItemViewStylesCount() > 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMultiStyle()) {
            return itemStyle.getItemViewType(data.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RViewItem item = itemStyle.getRViewItem(viewType);
        int layoutID = item.getItemLayout();
        RViewHolder viewHolder = RViewHolder.createViewHolder(parent.getContext(), parent, layoutID);
        if (item.openClick()) {
            setListener(viewHolder);
        }
        return viewHolder;
    }

    private void setListener(RViewHolder viewHolder) {
        viewHolder.getConvertView().setOnClickListener(v -> {
            if (itemListener != null) {
                int position = viewHolder.getAdapterPosition();
                itemListener.onItemClick(v, data.get(position), position);
            }
        });
        viewHolder.getConvertView().setOnLongClickListener(v -> {
            if (itemListener != null) {
                int position = viewHolder.getAdapterPosition();
                return itemListener.onItemLongClick(v, data.get(position), position);
            }
            return false;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder, entity, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
