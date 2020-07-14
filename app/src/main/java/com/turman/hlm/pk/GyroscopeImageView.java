package com.turman.hlm.pk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class GyroscopeImageView extends AppCompatImageView implements  GyroscopeImpl {

    private int mWidth = 0;
    private int mHeight = 0;
    private int mDrawableWidth = 0;
    private int mDrawableHeight = 0;

    // 图片最大偏移量
    private float mMaxOffsetX = 0f;
    private float mMaxOffsetY = 0f;

    // 相对于初始位置的旋转弧度
    public float mRotateRadianX = 0f;
    public float mRotateRadianY = 0f;

    private float progressX = 0f;
    private float progressY = 0f;

    public GyroscopeImageView(Context context) {
        super(context);
        init();
    }

    public GyroscopeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GyroscopeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getDrawable()!=null) {
            int mWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
            int mHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

            int mDrawableWidth = getDrawable().getIntrinsicWidth();
            int mDrawableHeight = getDrawable().getIntrinsicHeight();

            if (mDrawableWidth <= mWidth) {
                Bitmap oldbmp = drawableToBitmap(getDrawable());
                Matrix matrix = new Matrix();   // 创建操作图片用的 Matrix 对象
                float scaleWidth = ((float)mWidth / mDrawableWidth)*1.2f;   // 计算缩放比例
//                float scaleHeight = ((float)mHeight / mDrawableHeight)*1.2f;
                matrix.postScale(scaleWidth, scaleWidth);
                Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, mDrawableWidth, mDrawableHeight, matrix, true);
                setImageDrawable(new BitmapDrawable(getResources(),newbmp));

                mDrawableWidth = getDrawable().getIntrinsicWidth();
                mDrawableHeight = getDrawable().getIntrinsicHeight();
            }

            mMaxOffsetX = (int) Math.abs((mDrawableWidth - mWidth) * 0.5f);
            mMaxOffsetY = (int) Math.abs((mDrawableHeight - mHeight) * 0.5f);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null || isInEditMode()) {
            super.onDraw(canvas);
            return;
        }
        float currentOffsetX = mMaxOffsetX * progressX;
        float currentOffsetY = mMaxOffsetY * progressY;
        canvas.save();
        canvas.translate(currentOffsetX, currentOffsetY);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        GyroscopeObserver.getInstance().addGyroscopeImageViews(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GyroscopeObserver.getInstance().removeGyroscopeImageViews(this);
    }

    @Override
    public void updateProgress(Float progressX, Float progressY) {
        this.progressX = progressX;
        this.progressY = progressY;
        invalidate();
    }


    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();   // 取 drawable 的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;         // 取 drawable 的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);     // 建立对应 bitmap
        Canvas canvas = new Canvas(bitmap);         // 建立对应 bitmap 的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);      // 把 drawable 内容画到画布中
        return bitmap;
    }
}
