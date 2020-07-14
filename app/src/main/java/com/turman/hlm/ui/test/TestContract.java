package com.turman.hlm.ui.test;

import com.turman.hlm.base.inter.IFragmentView;
import com.turman.hlm.base.inter.Presenter;

public interface TestContract {
    interface TestIView extends IFragmentView {

    }

    interface TestPresenter extends Presenter<TestContract.TestIView> {
        void photo();
    }
}
