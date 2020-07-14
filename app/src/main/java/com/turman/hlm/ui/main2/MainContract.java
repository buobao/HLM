package com.turman.hlm.ui.main2;

import com.turman.hlm.base.inter.Presenter;
import com.turman.hlm.base.inter.IView;

public interface MainContract {
    interface MainIView extends IView {

    }

    interface MainPresenter extends Presenter<MainIView> {
        void locatUser();
    }
}
