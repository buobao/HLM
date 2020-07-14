package com.turman.hlm.pk;

import android.graphics.Bitmap;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;

public class GyroscopeTransFormation implements Transformation<Bitmap> {

    private int mWidgetWidth;   //控件宽度
    private int mWidgetHeight;  //控件高度
    private double mTargetWidth;    //目标宽度
    private double mTargetHeight;   //目标高度

    public GyroscopeTransFormation(int widgetWidth, int widgetHeight) {
        mWidgetWidth = widgetWidth;
        mWidgetHeight = widgetHeight;
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        if (resource.get().getWidth() == 0 || resource.get().getHeight() == 0) {
            return resource;
        }

        mTargetWidth = resource.get().getWidth();
        mTargetHeight = resource.get().getHeight();

        double ratio = mTargetWidth / mTargetHeight;     //图片的宽高比
        int distance;                                    //图片缩放后与控件边的距离

        if (mWidgetHeight <= mWidgetWidth) {
            distance = mWidgetHeight / 8;
            mTargetWidth = mWidgetWidth + 2 * distance;
            mTargetHeight = mTargetWidth / ratio;
        } else {
            distance = mWidgetWidth / 8;
            mTargetHeight = mWidgetHeight + 2 * distance;
            mTargetWidth = mTargetHeight * ratio;
        }

        int desiredWidth = (int) mTargetWidth;
        int desiredHeight = (int) mTargetHeight;

        Bitmap result = Bitmap.createScaledBitmap(resource.get(), desiredWidth, desiredHeight, false);

        if (result != resource.get()) {
            // Same bitmap is returned if sizes are the same
            resource.get().recycle();
        }
        return resource;
    }

    @Override
    public String getId() {
        return null;
    }
}