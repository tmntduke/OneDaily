package tmnt.example.onedaily.weight.BottomNavigation;

/**
 * Created by tmnt on 2017/4/14.
 */

public interface Controller {
    Controller addTabItem(TabItem tabItem);

    Controller build();

    void addTabItemClickListener(OnTabItemSelectListener listener);

    /**
     * 设置选中项
     *
     * @param index 顺序索引
     */
    void setSelect(int index);

}
