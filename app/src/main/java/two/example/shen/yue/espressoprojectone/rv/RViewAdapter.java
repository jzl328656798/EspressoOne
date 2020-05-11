package two.example.shen.yue.espressoprojectone.rv;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Queen
 * Date: 2020/5/9 8:56 AM
 * Describe: RV
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private ItemListener<T> itemListener;

    private List<T> data;

    private RViewItemManager<T> itemStyle;

    public RViewAdapter(List<T> data) {
        this(data, null);
    }

    public RViewAdapter(List<T> data, RViewItem<T> item) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        itemStyle = new RViewItemManager<>();
        if (item != null) {
            addStyles(item);
        }
    }

    void updateData(List<T> data) {
        if (data == null) {
            return;
        }
        this.data = data;
        notifyDataSetChanged();
    }

    void addData(List<T> data) {
        if (data == null) {
            return;
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

    public void addStyles(RViewItem<T> item) {
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
