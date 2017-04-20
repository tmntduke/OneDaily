package tmnt.example.onedaily.weight.WaveView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import tmnt.example.onedaily.R;

/**
 * Created by wing on 16/1/14.
 */
public class WaveLoadingView extends View {
    private final Paint mSRCPaint;

    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private int y;
    private int x;

    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Path mPath;
    private boolean isLeft;
    //    private int y;
    private int mWidth;
    private int mHeight;
    private int mPercent;

    public WaveLoadingView(Context context) {
        this(context, null);
    }

    public WaveLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

        mPaint.setStrokeWidth(10);

        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));

        mSRCPaint = new Paint();
        mSRCPaint.setAntiAlias(true);
        mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mSRCPaint.setColor(Color.parseColor("#88dddddd"));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }

        y = mHeight;
        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {


//        if (y > -50) {
//            y--;
//        }
        if (x > 50) {
            isLeft = true;
        } else if (x < 0) {
            isLeft = false;
        }

        if (isLeft) {
            x = x - 10;
        } else {
            x = x + 10;
        }
        mPath.reset();
        y = (int) ((1 - mPercent / 100f) * mHeight);
        mPath.moveTo(0, y);
        mPath.cubicTo(100 + x * 2, 50 + y, 100 + x * 2, y - 50, mWidth, y);
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();


        //清除掉图像 不然path会重叠
        mBitmap.eraseColor(Color.parseColor("#00000000"));
        //dst
//        mCanvas.drawBitmap(bgBitmap,0,0,null);

//        mSRCPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        mCanvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mSRCPaint);

        mPaint.setXfermode(mMode);
        //src
        mCanvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);


        canvas.drawBitmap(mBitmap, 0, 0, null);

        postInvalidateDelayed(1);

    }


    public void setPercent(int percent) {
        mPercent = percent;
    }

}
