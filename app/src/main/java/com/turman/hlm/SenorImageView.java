package com.turman.hlm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class SenorImageView extends AppCompatImageView {

    private int currentOffsetX=0, currentOffsetY=0;
    private int mMaxOffsetX,mMaxOffsetY;

    public SenorImageView(Context context) {
        super(context);
        init(context);
    }

    public SenorImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SenorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setScaleType(ScaleType.CENTER);
    }

    public void setLocation(int x,int y) {
        currentOffsetX +=x;
        currentOffsetY +=y;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getDrawable()!=null) {
            int mWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
            int mHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

            int mDrawableWidth = getDrawable().getIntrinsicWidth();
            int mDrawableHeight = getDrawable().getIntrinsicHeight();

            mMaxOffsetX = (int) Math.abs((mDrawableWidth - mWidth) * 0.5f);
            mMaxOffsetY = (int) Math.abs((mDrawableHeight - mHeight) * 0.5f);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(currentOffsetX, currentOffsetY);
        canvas.restore();
    }
}
