package com.turman.hlm.ui.test;

import com.turman.hlm.R;
import com.turman.hlm.base.BaseFragment;

public class TestFragment extends BaseFragment<TestContract.TestPresenter> implements TestContract.TestIView {
    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected TestContract.TestPresenter getPresenter() {
        return new TestPresenterImpl();
    }

    @Override
    protected void init() {
        mPresenter.photo();
    }
}
