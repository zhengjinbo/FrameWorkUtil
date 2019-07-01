package com.zjb.frameworkutil.fragment;

import android.view.View;

import com.zjb.frameworkutil.R;
import com.zjb.frameworkutil.base.BaseFragment;
import com.zjb.frameworkutil.utils.LogUtil;
import com.zjb.frameworkutil.views.TitleBarView;

/**
 * @author ZJB
 * @date 2019/6/27 16:48
 */
public class Fragment4 extends BaseFragment {
    private TitleBarView titleBarView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment4;
    }

    @Override
    protected void initUI(View view) {
        titleBarView = view.findViewById(R.id.titleBarView);
        titleBarView.getTv_in_title().setText("Fragment4");

    }

    @Override
    protected void initData() {
        LogUtil.e("Fragment4");

    }
}
