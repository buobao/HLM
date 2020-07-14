package com.turman.hlm.ui.main2;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.turman.hlm.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

public class MainPresenterImpl extends BasePresenter<MainContract.MainIView> implements MainContract.MainPresenter {
    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {
//        iView.showMsg("onCreate");
    }

    @Override
    public void onStart(@NotNull LifecycleOwner owner) {
//        iView.showMsg("onStart");
    }

    @Override
    public void onResume(@NotNull LifecycleOwner owner) {
//        iView.showMsg("onResume");
    }

    @Override
    public void onPause(@NotNull LifecycleOwner owner) {
//        iView.showMsg("onPause");
    }

    @Override
    public void onStop(@NotNull LifecycleOwner owner) {
//        iView.showMsg("onStop");
    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
//        iView.showMsg("onDestroy");
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {
//        iView.showMsg("onLifecycleChanged");
    }

    @Override
    public void onPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults!=null && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locatUser();
        }
    }

    @Override
    public void locatUser() {
        if (ContextCompat.checkSelfPermission(iView.getCurrentActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            iView.showMsg("成功定位用户");
        } else {
            ActivityCompat.requestPermissions(iView.getCurrentActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
    }
}
