package com.zjb.frameworkutil.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * basefragment
 *
 * @author ZJB
 */
public abstract class BaseFragment extends Fragment {
    private Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initUI(view);
        initData();
        setListener();
        return view;
    }


    protected abstract int getLayoutId();

    protected abstract void initUI(View view);

    protected abstract void initData();

    protected void setListener() {}


}
