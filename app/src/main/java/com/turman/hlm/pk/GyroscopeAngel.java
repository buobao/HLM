package com.turman.hlm.pk;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class GyroscopeAngel implements SensorEventListener {
    private long timestamp = 0;

    private float NS2S = 1.0f / 1000000000.0f;

    private long[] angle = new long[]{0,0,0};

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (timestamp == 0L) {
            // 从 x、y、z 轴的正向位置观看处于原始方位的设备，如果设备逆时针旋转，将会收到正值；否则，为负值
            timestamp = event.timestamp;
            return;
        }
        // 得到两次检测到手机旋转的时间差（纳秒），并将其转化为秒
        float dT = (event.timestamp -timestamp) * NS2S;
        // 将手机在各个轴上的旋转角度相加，即可得到当前位置相对于初始位置的旋转弧度
        angle[0] += (event.values[0] * dT);
        angle[1] += (event.values[1] * dT);
        angle[2] += (event.values[2] * dT);
        // 将弧度转化为角度
        double anglex = Math.toDegrees(angle[0]);
        double angley = Math.toDegrees(angle[1]);
        double anglez = Math.toDegrees(angle[2]);
        timestamp = event.timestamp;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
