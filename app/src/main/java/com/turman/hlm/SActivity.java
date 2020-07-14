package com.turman.hlm;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.turman.hlm.pk.GyroscopeImageView;
import com.turman.hlm.pk.GyroscopeObserver;

public class SActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_act);

        GyroscopeImageView img = findViewById(R.id.img);

        Glide.with(this)
                .load("https://infocdn.3dnest.cn/22190244_kiuT_b6f9/2019-07-02-17-55-13/resources/background_1562112307.jpg")
                .into(img);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GyroscopeObserver.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GyroscopeObserver.getInstance().unRegister();
    }
}
