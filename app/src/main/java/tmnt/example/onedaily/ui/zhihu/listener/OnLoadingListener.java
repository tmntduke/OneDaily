package tmnt.example.onedaily.ui.zhihu.listener;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by tmnt on 2017/4/28.
 */

public abstract class OnLoadingListener extends RecyclerView.OnScrollListener {

    private static boolean isLoadingMore;
    private LinearLayoutManager mLinearLayoutManager;
    private Handler mHandler = new LoadHandle();
    private static final String TAG = "OnLoadingListener";
    public static final int LOAD = 0201;
    public static final int LOAD_OVER = 0202;

    public OnLoadingListener(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
        // dy>0 表示向下滑动
        if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
            if (isLoadingMore) {
                Log.i(TAG, "onScrolled: loading");
            } else {
                onLoading(mHandler);
                Log.i(TAG, "onScrolled: load");

            }
        }
    }

    public abstract void onLoading(Handler handler);

    public static class LoadHandle extends Handler {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case LOAD:
                        isLoadingMore = true;
                        break;
                    case LOAD_OVER:
                        isLoadingMore = false;
                }
            }
    }
}
