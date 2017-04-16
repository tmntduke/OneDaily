package tmnt.example.onedaily.weight.Slideshow;

import android.content.Context;
import android.view.View;

/**
 * Created by tmnt on 2017/4/15.
 */

public interface Holder<T> {
    View createView(Context context);
    void UpdateUI(Context context, int position, T data, View v);

}
