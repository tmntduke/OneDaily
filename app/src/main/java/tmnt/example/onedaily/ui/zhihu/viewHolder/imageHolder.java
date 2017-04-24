package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.weight.Slideshow.Holder;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ImageHolder implements Holder<TopStories> {
    @Override
    public View createView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, TopStories data, View v) {
        Glide.with(context).load(data.getImage()).into((ImageView) v);
    }
}
