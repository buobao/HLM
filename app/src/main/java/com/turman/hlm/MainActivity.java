package com.turman.hlm;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SenorImageView ivBg;
    private SensorManager sensorManager;

    private long timestamp = 0l;

    private float NS2S = 1.0f / 1000000000.0f;

    private long[] angle = new long[]{0,0,0};

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivBg = findViewById(R.id.img);
        mHandler = new Handler();



        ivBg.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(MainActivity.this)
                        .load("https://infocdn.3dnest.cn/22190244_kiuT_b6f9/2019-07-02-17-55-13/resources/background_1562112307.jpg")
                        .into(ivBg);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivBg.setLocation(5,5);
                    }
                },1000);
            }
        });

        //获取系统传感器服务
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //设置监听器监听加速度传感器（重力感应器）的状态，精度为普通（SENSOR_DELAY_NORMAL）
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_GYROSCOPE:


//                if (timestamp == 0L) {
//                    // 从 x、y、z 轴的正向位置观看处于原始方位的设备，如果设备逆时针旋转，将会收到正值；否则，为负值
//                    timestamp = event.timestamp;
//                    return;
//                }
//                // 得到两次检测到手机旋转的时间差（纳秒），并将其转化为秒
//                float dT = (event.timestamp -timestamp) * NS2S;
//                // 将手机在各个轴上的旋转角度相加，即可得到当前位置相对于初始位置的旋转弧度
//                angle[0] += (event.values[0] * dT);
//                angle[1] += (event.values[1] * dT);
//                angle[2] += (event.values[2] * dT);
//                // 将弧度转化为角度
//                double anglex = Math.toDegrees(angle[0]);
//                double angley = Math.toDegrees(angle[1]);
//                double anglez = Math.toDegrees(angle[2]);
//                timestamp = event.timestamp;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}