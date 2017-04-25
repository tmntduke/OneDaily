package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.Story;
import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.zhihu.adapter.ZhihuAdapter;

/**
 * Created by tmnt on 2017/4/25.
 */

public class ViewHolderFactory {
    public static BaseViewHolder create(int type, Context context, ViewGroup parent) {
        if (type == ZhihuAdapter.IS_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_zhihu_slide_item, parent, false);
            BaseViewHolder<List<TopStories>> header = new HeaderViewHolder(view, ZhihuAdapter.IS_HEADER);
            return header;
        } else if (type == ZhihuAdapter.IS_NORMAL) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_zhihu_news_item, parent, false);
            BaseViewHolder<Story> item = new NewsViewHolder(view, ZhihuAdapter.IS_NORMAL);
            return item;
        }
        return null;
    }
}
