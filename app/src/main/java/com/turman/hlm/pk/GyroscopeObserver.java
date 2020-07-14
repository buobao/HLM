package com.turman.hlm.pk;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.LinkedList;

public class GyroscopeObserver<T extends GyroscopeImageView> implements SensorEventListener {
    private static volatile GyroscopeObserver instance;

    // 陀螺仪触发持续时间
    private long mLastTimestamp = 0L;

    private LinkedList<T> views = new LinkedList();

    private float NS2S = 1.0f / 1000000000.0f;
    // 陀螺仪在X、Y轴旋转的最大弧度
    // The value must between (0, π/2].
    private double mMaxRotateRadian = Math.PI / 2;

    private SensorManager sensorManager = null;


    private GyroscopeObserver() {
    }

    public static GyroscopeObserver getInstance() {
        if (instance == null) {
            synchronized(GyroscopeObserver.class) {
                if (instance == null) {
                    instance = new GyroscopeObserver();
                }
            }
        }
        return instance;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_GYROSCOPE) {
            return;
        }
        if (mLastTimestamp == 0L) {
            mLastTimestamp = event.timestamp;
            return;
        }
        double rotateX = Math.abs(event.values[0]);
        double rotateY = Math.abs(event.values[1]);
        double rotateZ = Math.abs(event.values[2]);
        // X轴方向偏移量更多
        if (rotateX > rotateY + rotateZ) {

        }
        // Y轴方向偏移量更多
        else if (rotateY > rotateX + rotateZ) {

        }
        float dt = (event.timestamp - mLastTimestamp) * NS2S * 2.0f;
        for (T view : views) {
            view.mRotateRadianY += event.values[1] * dt;
            view.mRotateRadianX += event.values[0] * dt;
            if (view.mRotateRadianY > mMaxRotateRadian) {
                view.mRotateRadianY = (float) mMaxRotateRadian;
            }
            else if (view.mRotateRadianY < -mMaxRotateRadian) {
                view.mRotateRadianY = (float) -mMaxRotateRadian;
            }
            if (view.mRotateRadianX > mMaxRotateRadian) {
                view.mRotateRadianX = (float) mMaxRotateRadian;
            }
            else if (view.mRotateRadianX < -mMaxRotateRadian) {
                view.mRotateRadianX = (float) -mMaxRotateRadian;
            }
            // 注意此处，X与Y方向是反过来的
            view.updateProgress((float)(view.mRotateRadianY / mMaxRotateRadian), (float)(view.mRotateRadianX / mMaxRotateRadian));
        }
        mLastTimestamp = event.timestamp;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * 注册陀螺仪传感器
     */
    public void  register(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // 陀螺仪灵敏参数  SENSOR_DELAY_FASTEST = 0; SENSOR_DELAY_GAME = 1; SENSOR_DELAY_NORMAL = 3; SENSOR_DELAY_UI = 2;
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        // 重置持续时间参数
        mLastTimestamp = 0;
    }

    /**
     * 解绑陀螺仪传感器
     */
    public void unRegister() {
        sensorManager.unregisterListener(this);
        sensorManager = null;
    }

    /**
     * 添加ImageView
     */
    public void addGyroscopeImageViews(T view) {
        if (!views.contains(view))
            views.add(view);
    }

    public void removeGyroscopeImageViews(T view) {
        if (!views.contains(view))
            views.remove(view);
    }


}
