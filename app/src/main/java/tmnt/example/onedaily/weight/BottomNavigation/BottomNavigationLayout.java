package tmnt.example.onedaily.weight.BottomNavigation;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.util.SharedPreferencesUtil;


/**
 * Created by tmnt on 2017/4/14.
 */

public class BottomNavigationLayout extends LinearLayout {

    private List<TabItem> mTabItems = new ArrayList<>();
    private OnTabItemSelectListener mOnTabItemSelectListener;
    private int mIndex = 0;
    private Object mTag;
    private static final String TAG = "BottomNavigationLayout";

    private int index;
    private int width;
    private int height;

    private SharedPreferencesUtil util;

    public BottomNavigationLayout(Context context) {
        super(context);
        init(context);
    }

    public BottomNavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNavigationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        util = SharedPreferencesUtil.getInstance(context);

        if (util.getData("select") != null) {
            mIndex = util.getData("select");
        }

        setBackground(getResources().getDrawable(R.drawable.bottom_shape));


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public Controller create() {
        return new BaseController();
    }

    private void setSelectBottom() {
        mTabItems.get(mIndex).setIcon(true);
        if (mOnTabItemSelectListener != null) {
            mOnTabItemSelectListener.onSelected(mIndex, mTabItems.get(mIndex).getTag());
        }

    }

    public void setSelect(int bomIndex) {
        mTabItems.get(index).setIcon(false);
        mTabItems.get(bomIndex).setIcon(true);
//        if (mOnTabItemSelectListener != null) {
//            mOnTabItemSelectListener.onSelected(index, mTabItems.get(index).getTag());
//        }
        invalidate();
    }

    class BaseController implements Controller {
        private static final String TAG = "BottomNavigationLayout";

        @Override
        public Controller addTabItem(TabItem tabItem) {
            mTabItems.add(tabItem);
            return this;
        }


        @Override
        public Controller build() {
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1.0f;
            layoutParams.gravity = Gravity.CENTER;
            for (TabItem tabItem : mTabItems) {
                tabItem.setHandler(new BottomHanler());
                tabItem.setLayoutParams(layoutParams);
                BottomNavigationLayout.this.addView(tabItem, layoutParams);
            }

            setSelectBottom();
            return this;
        }

        @Override
        public void addTabItemClickListener(OnTabItemSelectListener listener) {

            mOnTabItemSelectListener = listener;

//            for (int i = 0; i < mTabItems.size(); i++) {
//                if (mTabItems.get(i).getTag().equals(mTag)) {
//                    mTabItems.get(i).setIcon(true);
//                    listener.onSelected(i, mTag);
//                    invalidate();
//                    return;
//                }
//            }

        }

        @Override
        public void setSelect(int index) {
            if (index < mTabItems.size()) {
                mIndex = index;
                util.putData("select", index);

            }

        }
    }

    class BottomHanler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2001) {
                mTag = msg.obj;

                for (int i = 0; i < mTabItems.size(); i++) {
                    TabItem tabItem = mTabItems.get(i);
                    if (mTabItems.get(i).getTag().equals(mTag)) {
                        index = i;

                        if (mOnTabItemSelectListener != null) {

                            mOnTabItemSelectListener.onSelected(index, mTag);
                        }


                        tabItem.setIcon(true);
                    } else {
                        tabItem.setIcon(false);
                    }
                }
                invalidate();
            }
        }
    }

}
