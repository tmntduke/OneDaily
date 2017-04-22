package tmnt.example.onedaily.weight.ClearEditText;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.util.DensityUtils;


/**
 * Created by MrFu on 15/7/30.
 */
public class ClearEditText extends AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;
    private Drawable search;
    private Context mContext;
    private Drawable scan;
    private OnScanLisenter mOnScanLisenter;

    public void setOnScanLisenter(OnScanLisenter onScanLisenter) {
        mOnScanLisenter = onScanLisenter;
    }

    public ClearEditText(final Context context) {
        super(context);
        init(context);
        this.mContext = context;
    }

    public ClearEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
        this.mContext = context;
    }

    public ClearEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        this.mContext = context;
    }

    private void init(final Context context) {
        final Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_cancel);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, (int) (mClearTextIcon.getIntrinsicHeight() * 0.3f), (int) (mClearTextIcon.getIntrinsicHeight() * 0.3f));
        search = ContextCompat.getDrawable(context, R.drawable.ic_book_search);

        scan = ContextCompat.getDrawable(context, R.drawable.ic_book_scan);
        scan.setBounds(0, 0, (int) (scan.getMinimumWidth() * 0.3f), (int) (scan.getMinimumHeight() * 0.3f));

        search.setBounds(0, 0, (int) (search.getMinimumWidth() * 0.3f), (int) (search.getMinimumHeight() * 0.3f));
        setCompoundDrawables(search, null, null, null);
        setCompoundDrawablePadding(DensityUtils.dp2px(context, 4));
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText("");
            }
            return true;
        } else if (scan.isVisible() && x > getWidth() - getPaddingRight() - scan.getIntrinsicWidth()){
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (mOnScanLisenter != null) {
                    mOnScanLisenter.onScan(view);
                }
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    @Override
    public final void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setClearIconVisible(final boolean visible) {
        mClearTextIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? mClearTextIcon : scan,
                compoundDrawables[3]);
    }

    public interface OnScanLisenter {
        void onScan(View view);
    }
}
