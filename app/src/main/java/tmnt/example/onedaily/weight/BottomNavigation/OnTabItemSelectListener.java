package tmnt.example.onedaily.weight.BottomNavigation;

/**
 * Created by tmnt on 2017/4/14.
 */

public interface OnTabItemSelectListener {
    /**
     * 选中导航栏的某一项
     *
     * @param index 索引导航按钮，按添加顺序排序
     * @param tag   默认是导航按钮的文字内容
     */
    void onSelected(int index, Object tag);

    /**
     * 已经选中状态下点击
     *
     * @param index 索引导航按钮，按添加顺序排序
     * @param tag   默认是导航按钮的文字内容
     */
    void onRepeatClick(int index, Object tag);
}
