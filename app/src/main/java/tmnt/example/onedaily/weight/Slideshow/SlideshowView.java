package tmnt.example.onedaily.weight.Slideshow;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import tmnt.example.onedaily.R;


/**
 * Created by tmnt on 2017/4/15.
 */

public class SlideshowView extends FrameLayout {

    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private View mView;
    private List<ImageBean> mViewList;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private int dotSize = 12;
    private int dotSpace = 12;
    private Animator animatorToLarge;
    private Animator animatorToSmall;

    private int currentItem;

    private Subscription mSubscription;
    private SlideshowAdapter adapter;

    private SparseBooleanArray isLarge;

    private static final String TAG = "SlideshowView";

    public SlideshowView(Context context) {
        super(context);
        init(context);
        this.mContext = context;
        initAnimator();
    }

    public SlideshowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        this.mContext = context;
        initAnimator();
    }

    public SlideshowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        this.mContext = context;
        initAnimator();
    }

    private void init(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.slideshow_lay, null);
        mViewPager = (ViewPager) mView.findViewById(R.id.slide);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.selector_contain);
        mViewList = new ArrayList<>();
        isLarge = new SparseBooleanArray();
        addView(mView);

    }

    private void initAnimator() {
        animatorToLarge = AnimatorInflater.loadAnimator(mContext, R.animator.scale_to_large);
        animatorToSmall = AnimatorInflater.loadAnimator(mContext, R.animator.scale_to_small);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        adapter.setOnItemClickListener(mOnItemClickListener);
    }

    public void setData(Holder holder, List<?> list) {

        mViewList = createImage(holder, list);
        Log.i(TAG, "holder: " + holder);
        adapter = new SlideshowAdapter(mViewList, holder, mContext);
        mViewPager.setAdapter(adapter);
        setSelector(list.size());
        Log.i(TAG, "setData: " + mViewList.size());


        showImage(list.size() + 2);
        autoPlay();
    }

    private void autoPlay() {
        mSubscription = Observable.interval(5, 5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        int posttion = (currentItem + 1) % mViewList.size();
                        mViewPager.setCurrentItem(posttion, true);
                    }
                });
    }

    private void stop() {
        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }


    private List<ImageBean> createImage(Holder holder, List<?> list) {
        List<ImageBean> views = new ArrayList<>();
        int count = list.size();
        for (int i = 0; i < count + 2; i++) {
            if (i == 0) {
                ImageBean imageBean = new ImageBean((ImageView) holder.createView(mContext), list.get(count - 1));
                views.add(imageBean);
            } else if (i == count + 1) {
                ImageBean imageBean = new ImageBean((ImageView) holder.createView(mContext), list.get(0));
                views.add(imageBean);
            } else {
                ImageBean imageBean = new ImageBean((ImageView) holder.createView(mContext), list.get(i - 1));
                views.add(imageBean);
            }
        }

        Log.i(TAG, "image: " + views.get(2).getImageView() + "  " + views.get(3).getT());

        return views;
    }

    private void setSelector(int count) {
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(R.drawable.unselect_shape);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dotSize, dotSize);
            layoutParams.leftMargin = dotSpace / 2;
            layoutParams.rightMargin = dotSpace / 2;
            layoutParams.topMargin = dotSpace / 2;
            layoutParams.bottomMargin = dotSpace / 2;
            mLinearLayout.addView(imageView, layoutParams);
        }

        mLinearLayout.getChildAt(0).setBackgroundResource(R.drawable.select_shape);
        animatorToLarge.setTarget(mLinearLayout.getChildAt(0));
        animatorToLarge.start();
        isLarge.put(0, true);
    }

    private void showImage(int count) {
        currentItem = 1;

        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
                    if (i == position - 1) {// 被选中
                        mLinearLayout.getChildAt(i).setBackgroundResource(R.drawable.select_shape);
                        if (!isLarge.get(i)) {
                            animatorToLarge.setTarget(mLinearLayout.getChildAt(i));
                            animatorToLarge.start();
                            isLarge.put(i, true);
                            Log.i(TAG, "onPageSelected: "+isLarge.size());
                        }
                    } else {// 未被选中
                        mLinearLayout.getChildAt(i).setBackgroundResource(R.drawable.unselect_shape);
                        if (isLarge.get(i)) {
                            animatorToSmall.setTarget(mLinearLayout.getChildAt(i));
                            animatorToSmall.start();
                            isLarge.put(i, false);
                            Log.i(TAG, "onPageSelected: "+isLarge.size());
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (mViewPager.getCurrentItem() == 0) {
                            mViewPager.setCurrentItem(count, false);
                        } else if (mViewPager.getCurrentItem() == count - 1) {
                            mViewPager.setCurrentItem(1, false);
                        }
                        currentItem = mViewPager.getCurrentItem();
                        break;
                }
            }
        });



    }

}
