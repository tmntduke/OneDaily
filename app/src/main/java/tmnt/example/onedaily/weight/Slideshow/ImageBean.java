package tmnt.example.onedaily.weight.Slideshow;

import android.widget.ImageView;

/**
 * Created by tmnt on 2017/4/15.
 */

public class ImageBean<T> {
    private ImageView mImageView;
    private T t;

    public ImageBean(ImageView imageView, T t) {
        mImageView = imageView;
        this.t = t;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
