package com.turman.hlm.ui.test;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.turman.hlm.base.BasePresenter;

public class TestPresenterImpl extends BasePresenter<TestContract.TestIView> implements TestContract.TestPresenter {

    @Override
    public void onPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults!=null && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            photo();
        }
    }

    @Override
    public void photo() {
        if (ContextCompat.checkSelfPermission(iView.getCurrentActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            iView.showMsg("拍照成功");
        } else {
            iView.getFragment().requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
        }
    }
}
