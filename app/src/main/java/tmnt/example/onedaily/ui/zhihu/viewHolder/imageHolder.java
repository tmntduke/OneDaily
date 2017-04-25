package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.weight.Lable.LabelView;
import tmnt.example.onedaily.weight.Slideshow.Holder;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ImageHolder implements Holder<TopStories> {

    ImageView imageView;
    TextView mTextView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhihu_slide_lay, null);
        imageView = (ImageView) view.findViewById(R.id.img_zhihu_slide);
        mTextView = (TextView) view.findViewById(R.id.tv_zhihu_slide);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, TopStories data, View v) {
        Glide.with(context).load(data.getImage()).into(imageView);
        mTextView.setText(data.getTitle());
    }
}
