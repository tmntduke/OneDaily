package tmnt.example.onedaily.weight.Lable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.util.DensityUtils;

/**
 * Created by tmnt on 2017/3/17.
 */

public class LabelView extends ViewGroup {

    private float mTextSize;
    private int mTextColor;
    private int mLableMargin;
    private int mLabelPadding;
    private int mTextBackground;
    private Context mContext;

    private OnLabelListener mOnLabelListener;

    private static final String TAG = "LabelView";

    public void setOnLabelListener(OnLabelListener onLabelListener) {
        mOnLabelListener = onLabelListener;
    }

    public LabelView(Context context) {
        super(context);
        mContext = context;
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context, attrs);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.label);
        //mTextBackground = typedArray.getResourceId(R.styleable.label_labelBackground, -1);
        mTextColor = typedArray.getColor(R.styleable.label_textColor, Color.BLACK);
        mTextSize = typedArray.getDimension(R.styleable.label_textSize, 42.0f);
        mLabelPadding = typedArray.getDimensionPixelOffset(R.styleable.label_labelPadding, 0);
        mLableMargin = typedArray.getDimensionPixelOffset(R.styleable.label_labelMargin, 0);
        typedArray.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int maxItemHeight = 0;
        int currWidth = 0;
        int contentHeight = 0;
        boolean begin = false;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);

            if (maxWidth < currWidth + view.getMeasuredWidth()) {
                maxItemHeight += mLableMargin;
                maxItemHeight += view.getMeasuredHeight();
                currWidth = 0;
                begin = true;

            }
            maxItemHeight = Math.max(maxItemHeight, view.getMeasuredHeight());
            if (!begin) {
                currWidth += mLableMargin;
            } else {
                begin = false;
            }
            currWidth += view.getMeasuredWidth();

            maxWidth = Math.max(maxWidth, currWidth);

            setMeasuredDimension(measureWidth(widthMeasureSpec, maxWidth)
                    , measureHeight(heightMeasureSpec, maxItemHeight));

        }

    }

    private int measureWidth(int measureSpec, int contentWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = contentWidth + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumWidth());
        return result;
    }

    private int measureHeight(int measureSpec, int contentHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = contentHeight + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumHeight());
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = getPaddingLeft();
        int y = getPaddingTop();
        int contentWidth = r - l;
        int maxItemHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);

            if (contentWidth < x + view.getMeasuredWidth()) {
                x = getPaddingLeft();
                y += mLableMargin;
                y += maxItemHeight;
                maxItemHeight = 0;
            }
            view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
            x += view.getMeasuredWidth();
            x += mLabelPadding;
            maxItemHeight = Math.max(maxItemHeight, view.getMeasuredHeight());
        }
    }


    public void setLable(List<String> labels) {
        removeAllViews();
        if (labels != null) {
            int size = labels.size();
            for (int i = 0; i < size; i++) {
                addLabel(labels.get(i), i);
            }
        }
    }


    private void addLabel(String text, final int position) {
        TextView lable = new TextView(mContext);
        lable.setPadding(mLabelPadding, mLabelPadding, mLabelPadding, mLabelPadding);
        lable.setTextColor(mTextColor);
        Log.i(TAG, "addLabel: "+mTextSize);
        lable.setTextSize(mTextSize/3);
        lable.setText(text);
        if (mTextBackground != 0) {
            lable.setBackgroundResource(mTextBackground);
        }
        lable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLabelListener != null) {
                    mOnLabelListener.labelClick(lable, position);
                }
            }
        });
        addView(lable);

    }

    public void setLabelBackgroundResource(int res) {
        if (mTextBackground != res) {
            mTextBackground = res;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                TextView label = (TextView) getChildAt(i);
                label.setBackgroundResource(res);
            }
        }
    }

    public interface OnLabelListener {
        void labelClick(View view, int position);
    }

}
