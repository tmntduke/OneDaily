package tmnt.example.onedaily.weight.Refresh;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import tmnt.example.onedaily.R;

/**
 * SmartPullableLayout
 * 一个通用的，支持上下拉动并触发相应事件的ViewGroup。
 * 在使用时，应该通过 @setOnPullListener 来设置组件的滑动监听。
 * @onPullDown 会在组件被成功下拉时回调；
 * @onPullUp 会在组件成功进行上拉时回调；
 * 可以在这里做SmartPullableLayout上、下拉动被成功触发时的事件处理。
 *
 */
public class SmartPullableLayout extends ViewGroup implements NestedScrollingParent {


    private static final float DECELERATE_INTERPOLATION_FACTOR = 2f; // 滑动阻力因子
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;

    private OnChildScrollUpCallback mChildScrollUpCallback; // 自定义mTarget是否还能进行上拉的判断依据
    private OnChildScrollDownCallback mChildScrollDownCallback; // 自定义mTarget是否还能进行下拉的判断依据

    private boolean enabled = true; // 当前是否允许视图滑动
    private boolean pullUpEnabled;  // 是否启用上拉功能
    private boolean pullDownEnabled;// 是否启用下拉功能

    private int currentState; // 视图当前状态
    private Context context;  // 上下文对象
    private OnPullListener mListener; // 滑动回调监听

    private View mTarget;  // 触发滑动手势的目标View
    private View mPullableHeader; // 滑动头部
    private View mPullableFooter; // 滑动尾部

    // 拉动部分背景(color|drawable)
    private Drawable mBackground;

    private ImageView ivArrowPullDown;   // 下拉状态指示器(箭头)
    private ImageView ivProgressPullDown;// 下拉加载进度条(圆形)
    private ImageView ivProgressPullUp;  // 上拉加载进度条(圆形)

    private TextView tvHintPullDown; // 下拉状态文本指示
    private TextView tvHintPullUp;   // 上拉状态文本指示

    private AnimationDrawable upProgressAnimation;   // 上拉加载进度条帧动画
    private AnimationDrawable downProgressAnimation; // 下拉加载进度条帧动画

    private Scroller mLayoutScroller;  // 用于平滑滑动的Scroller对象
    private int effectivePullRange;    // 使拉动回调生效(触发)的滑动距离
    private int ignorablePullRange;    // 可以忽略的拉动距离(超过此滑动距离，将不再允许parent view拦截触摸事件)

    private static final int STOP_PULL_BEHAVIOUR = 0X0502;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case STOP_PULL_BEHAVIOUR:
                    updateState(State.NORMAL);
                    break;
            }
        }
    };
    public SmartPullableLayout(Context context) {
        this(context, null);
        preparePullablePortion();
    }

    public SmartPullableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.SmartPullableLayout);
        try {
            pullDownEnabled = array.getBoolean(R.styleable.SmartPullableLayout_smart_ui_enable_pull_down, true);
            pullUpEnabled = array.getBoolean(R.styleable.SmartPullableLayout_smart_ui_enable_pull_up, true);
            mBackground = array.getDrawable(R.styleable.SmartPullableLayout_smart_ui_background);
        } finally {
            array.recycle();
        }

        // NestedScrollingParentHelper
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);

        mLayoutScroller = new Scroller(context);

        effectivePullRange = (int) getResources().getDimension(R.dimen.smart_ui_effective_pull_range);
        ignorablePullRange = (int) getResources().getDimension(R.dimen.smart_ui_ignorable_pull_range);



    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        preparePullablePortion();

    }

    private void preparePullablePortion() {
        if (pullDownEnabled) {
            mPullableHeader = LayoutInflater.from(context).inflate(R.layout.smart_ui_pullable_layout_header, null);
            if (mBackground != null)
                mPullableHeader.setBackgroundDrawable(mBackground);
            ivArrowPullDown = (ImageView) mPullableHeader.findViewById(R.id.smart_ui_iv_pull_down_arrow);
            ivProgressPullDown = (ImageView) mPullableHeader.findViewById(R.id.smart_ui_iv_pull_down_loading);
            downProgressAnimation = (AnimationDrawable) ivProgressPullDown.getBackground();
            tvHintPullDown = (TextView) mPullableHeader.findViewById(R.id.smart_ui_tv_pull_down_des);
            addView(mPullableHeader);

        }

        if (pullUpEnabled) {
            mPullableFooter = LayoutInflater.from(context).inflate(R.layout.smart_ui_pullable_layout_footer, null);
            if (mBackground != null)
                mPullableFooter.setBackgroundDrawable(mBackground);
            ivProgressPullUp = (ImageView) mPullableFooter.findViewById(R.id.smart_ui_iv_pull_up_loading);
            upProgressAnimation = (AnimationDrawable) ivProgressPullUp.getBackground();
            tvHintPullUp = (TextView) mPullableFooter.findViewById(R.id.smart_ui_tv_pull_up_des);
            addView(mPullableFooter);
        }
    }

    private void rotateArrow() {
        float offset = (ivArrowPullDown.getRotation() + 180) % 180;
        ObjectAnimator arrowAnimator = ObjectAnimator.ofFloat(ivArrowPullDown, "rotation",
                ivArrowPullDown.getRotation(), ivArrowPullDown.getRotation() + 180 - offset);
		arrowAnimator.setDuration(135);
        arrowAnimator.start();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setOnPullListener(OnPullListener listener) {
        this.mListener = listener;
    }

    public void setOnChildScrollUpCallback(OnChildScrollUpCallback childScrollUpCallback) {
        this.mChildScrollUpCallback = childScrollUpCallback;
    }

    public void setOnChildScrollDownCallback(OnChildScrollDownCallback childScrollDownCallback) {
        this.mChildScrollDownCallback = childScrollDownCallback;
    }

    private void ensureTarget() {
        if (mTarget == null) {
            if(pullDownEnabled) {
                mTarget = getChildAt(1);
            }else{
                mTarget = getChildAt(0);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mTarget == null) {
            ensureTarget();
        }
        if (mTarget == null) {
            return;
        }

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(
                    MeasureSpec.makeMeasureSpec(
                            getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                            MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(
                            getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),
                            MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == mPullableHeader) { // 头视图隐藏在顶端
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
            } else if (child == mPullableFooter) { // 尾视图隐藏在末端
                child.layout(0, mTarget.getMeasuredHeight(), child.getMeasuredWidth(), mTarget.getMeasuredHeight() + child.getMeasuredHeight());
            } else {
                child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
            }
        }
    }

    private int mLastMoveY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        int y = (int) event.getY();
        // 判断触摸事件类型
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                // 不拦截ACTION_DOWN，因为当ACTION_DOWN被拦截，后续所有触摸事件都会被拦截
                intercept = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if ((mTarget != null && !ViewCompat.isNestedScrollingEnabled(mTarget))) {
                    if (y > mLastMoveY) { // 下滑操作
                        intercept = !canChildScrollUp();
                    } else if (y < mLastMoveY) { // 上拉操作
                        intercept = !canChildScrollDown();
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercept = false;
                break;
            }
        }
        mLastMoveY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(getScrollY()) > ignorablePullRange)
                    getParent().requestDisallowInterceptTouchEvent(true);
                if (enabled)
                    doScroll(mLastMoveY - y);
                break;
            case MotionEvent.ACTION_UP:
                onStopScroll();
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        mLastMoveY = y;

        return true;
    }

    private void doScroll(int dy) {
        if (dy > 0) { // 上拉操作
            if (getScrollY() < 0) { // 此判断意味是在进行下拉操作的过程中，进行的上拉操作(可能代表用户视图取消此次下拉)
                if (Math.abs(getScrollY()) < effectivePullRange) {
                    if (currentState != State.PULL_DOWN)
                        updateState(State.PULL_DOWN);

                    if (dy > Math.abs(getScrollY()))
                        dy = -getScrollY();
                }
            } else {
                if (!pullUpEnabled)
                    return;
                if (currentState < State.PULL_UP && currentState != State.NORMAL)
                    return;

                if (getScrollY() > effectivePullRange) { // 当下拉已经达到有效距离，则为滑动添加阻力
                    dy /= DECELERATE_INTERPOLATION_FACTOR;
                    if (currentState != State.PULL_UP_RELEASEABLE)
                        updateState(State.PULL_UP_RELEASEABLE);
                }
            }
        } else if (dy < 0) { // 下拉操作
            if (getScrollY() > 0) {  // 此判断意味是在进行上拉操作的过程中，进行的下拉操作(可能代表用户视图取消此次上拉)
                if (getScrollY() < effectivePullRange) {
                    if (currentState != State.PULL_UP)
                        updateState(State.PULL_UP);

                    if (Math.abs(dy) > getScrollY())
                        dy = -getScrollY();
                }
            } else {
                if (!pullDownEnabled)
                    return;
                if (currentState > State.PULL_DOWN_RELEASEABLE)
                    return;

                if (Math.abs(getScrollY()) >= effectivePullRange) { // 当下拉已经达到有效距离，则为滑动添加阻力
                    dy /= DECELERATE_INTERPOLATION_FACTOR;
                    if (currentState != State.PULL_DOWN_RELEASEABLE)
                        updateState(State.PULL_DOWN_RELEASEABLE);
                }
            }
        }

        dy /= DECELERATE_INTERPOLATION_FACTOR;
        scrollBy(0, dy);
    }

    private void onStopScroll() {
        if (Math.abs(getScrollY()) >= effectivePullRange) { // 有效拉动行为
            if (getScrollY() < 0) { // 有效下拉行为
                mLayoutScroller.startScroll(0, getScrollY(), 0, -(getScrollY() + effectivePullRange));
                updateState(State.PULL_DOWN_RELEASE);
            } else if (getScrollY() > 0) { // 有效上拉行为
                updateState(State.PULL_UP_RELEASE);
                mLayoutScroller.startScroll(0, getScrollY(), 0, -((getScrollY() - effectivePullRange)));
            }
        } else { // 无效拉动行为
            if (getScrollY() != 0)
                mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY());
            updateState(State.NORMAL);
        }
    }

    private void updateState(int state) {
        switch (state) {
            case State.NORMAL:
                reset();
                break;
            case State.PULL_DOWN: {
                if (currentState != State.NORMAL)
                    rotateArrow();

                tvHintPullDown.setText(R.string.smart_ui_pull_down_normal);
            }
            break;
            case State.PULL_DOWN_RELEASEABLE: {
                rotateArrow();
                tvHintPullDown.setText(R.string.smart_ui_pull_down_release_able);
            }
            break;
            case State.PULL_DOWN_RELEASE: {
                setEnabled(false);

                ivArrowPullDown.setVisibility(View.INVISIBLE);

                ivProgressPullDown.setVisibility(View.VISIBLE);
                downProgressAnimation.start();

                tvHintPullDown.setText(R.string.smart_ui_pull_down_release);

                if (mListener != null) {
                    mListener.onPullDown();
                }
            }
            break;
            case State.PULL_UP: {
                tvHintPullUp.setText(R.string.smart_ui_pull_up_normal);
            }
            break;
            case State.PULL_UP_RELEASEABLE: {
                tvHintPullUp.setText(R.string.smart_ui_pull_up_release_able);
            }
            break;
            case State.PULL_UP_RELEASE: {
                setEnabled(false);

                ivProgressPullUp.setVisibility(View.VISIBLE);
                upProgressAnimation.start();
                tvHintPullUp.setText(R.string.smart_ui_pull_up_release);

                if (mListener != null) {
                    mListener.onPullUp();
                }
            }
            break;
        }

        currentState = state;
    }

    public void reset() {
        if (currentState != State.NORMAL) {
            if (currentState <= State.PULL_DOWN_RELEASE) {
                downProgressAnimation.stop();
                ivProgressPullDown.setVisibility(View.INVISIBLE);
                ivArrowPullDown.setVisibility(View.VISIBLE);
                ivArrowPullDown.setRotation(0);
                tvHintPullDown.setText(R.string.smart_ui_pull_down_normal);
            } else {
                upProgressAnimation.stop();
                ivProgressPullUp.setVisibility(View.GONE);
                tvHintPullUp.setText(R.string.smart_ui_pull_up_normal);
            }
        }

        setEnabled(true);
        if (getScrollY() != 0)
            mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY());
    }

    public void stopPullBehavior() {
        mHandler.sendEmptyMessage(STOP_PULL_BEHAVIOUR);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mLayoutScroller.computeScrollOffset()) {
            scrollTo(0, mLayoutScroller.getCurrY());
        }
        postInvalidate();
    }

    public boolean canChildScrollUp() {
        if (mChildScrollUpCallback != null) {
            return mChildScrollUpCallback.canChildScrollUp(this, mTarget);
        }
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, -1);
        }
    }

    public boolean canChildScrollDown() {
        if (mChildScrollDownCallback != null) {
            return mChildScrollDownCallback.canChildScrollDown(this, mTarget);
        }

        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0 && (absListView.getLastVisiblePosition() != absListView.getCount() -1
                        || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getMeasuredHeight());
            } else {
                if(mTarget instanceof ViewGroup)
                    return ViewCompat.canScrollVertically(mTarget, 1) || mTarget.getScrollY() < ((ViewGroup)mTarget).getChildAt(0).getMeasuredHeight() - mTarget.getMeasuredHeight();

                return ViewCompat.canScrollVertically(mTarget, 1) || mTarget.getScrollY() < mTarget.getMeasuredHeight() - getMeasuredHeight();
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, 1);
        }
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean b) {
        /*
         * 重写此方法的目的是:
         * 如果判断目标视图是一个版本低于Android-L的AbsListView，或另一个不支持嵌套滚动的视图。
         * 则请忽略此请求，以便垂直滚动事件不会被目标视图窃取(本视图无法再监听拦截到任何touchEvent)
         */
        if ((android.os.Build.VERSION.SDK_INT < 21 && mTarget instanceof AbsListView)
                || (mTarget != null && !ViewCompat.isNestedScrollingEnabled(mTarget))) {
            // Nope.
        } else {
            super.requestDisallowInterceptTouchEvent(b);
        }
    }

    // NestedScrollingParent

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return isEnabled() && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (getScrollY() != 0) { // 只有在SmartPullableLayout自身已经发生过位移的情况才处理预消耗
            if (dy > 0 && getScrollY() < 0 && Math.abs(dy) >= Math.abs(getScrollY())) {
                consumed[1] = getScrollY();
                scrollTo(0, 0);
                return;
            }

            if (dy < 0 && getScrollY() > 0 && Math.abs(dy) >= Math.abs(getScrollY())) {
                consumed[1] = getScrollY();
                scrollTo(0, 0);
                return;
            }

            int yConsume = Math.abs(dy) > Math.abs(getScrollY()) ? getScrollY() : dy;
            doScroll(yConsume);
            consumed[1] = yConsume;
        }
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View target) {
        onStopScroll();
        mNestedScrollingParentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        if (enabled)
            doScroll(dyUnconsumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    /**
     * 控件状态
     */
    public interface State {
        int NORMAL = 0;    // 正常状态
        int PULL_DOWN = 1; // 下拉中
        int PULL_DOWN_RELEASEABLE = 2; // 可释放下拉
        int PULL_DOWN_RELEASE = 3;     // 已释放下拉
        int PULL_UP = 4;    // 上拉中
        int PULL_UP_RELEASEABLE = 5;  // 可释放上拉
        int PULL_UP_RELEASE = 6;     // 已释放上拉
    }

    public interface OnPullListener {
        void onPullDown();

        void onPullUp();
    }

    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(SmartPullableLayout parent, View child);
    }

    public interface OnChildScrollDownCallback {
        boolean canChildScrollDown(SmartPullableLayout parent, View child);
    }
}