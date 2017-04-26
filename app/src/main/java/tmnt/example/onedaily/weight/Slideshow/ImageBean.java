package tmnt.example.onedaily.weight.Slideshow;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by tmnt on 2017/4/15.
 */

public class ImageBean<T> {
    private View mImageView;
    private T t;

    public ImageBean(View imageView, T t) {
        mImageView = imageView;
        this.t = t;
    }

    public View getImageView() {
        return mImageView;
    }

    public void setImageView(View imageView) {
        mImageView = imageView;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public int hashCode() {
        return mImageView.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ImageBean))
            return false;
        ImageBean imageBean = (ImageBean) o;
        return imageBean.mImageView.hashCode() == this.mImageView.hashCode();
    }

}
