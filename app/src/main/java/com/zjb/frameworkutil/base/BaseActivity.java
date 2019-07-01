package com.zjb.frameworkutil.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * BaseActivity
 *
 * @author ZJB
 */
public abstract class BaseActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initUI();
        initData();
        setListener();
    }


    protected abstract int getLayoutId();

    protected abstract void initUI();

    protected abstract void initData();

    protected void setListener() {
    }


}
