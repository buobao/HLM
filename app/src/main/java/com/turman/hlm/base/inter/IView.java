package com.turman.hlm.base.inter;

import android.app.Activity;

public interface IView {
    void showLoading();
    void dismissLoading();
    Activity getCurrentActivity();
    void showMsg(String msg);
}
