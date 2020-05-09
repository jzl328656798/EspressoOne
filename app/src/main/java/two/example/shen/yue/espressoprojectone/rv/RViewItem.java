package two.example.shen.yue.espressoprojectone.rv;

/**
 * Author: Queen
 * Date: 2020/5/9 8:30 AM
 * Describe: 条目数据
 */
public interface RViewItem<T> {

    /**
     * 布局
     */
    int getItemLayout();

    /**
     * 是否可以点击
     */
    boolean openClick();

    /**
     * 验证布局
     */
    boolean isItemView(T entity, int position);

    /**
     * 数据绑定
     */
    boolean convert(RViewHolder holder, T entity, int position);
}
