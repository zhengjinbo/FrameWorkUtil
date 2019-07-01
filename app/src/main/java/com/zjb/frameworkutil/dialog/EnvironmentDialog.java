package com.zjb.frameworkutil.dialog;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zjb.frameworkutil.R;
import com.zjb.frameworkutil.config.Constant;
import com.zjb.frameworkutil.views.CustomToast;


/**
 * Created by zjb on 2018/3/14.
 * 配置环境地址
 */

public class EnvironmentDialog extends Dialog implements View.OnClickListener {

    private Boolean cancelable = false;
    private Boolean canceledOnTouchOutside = false;
    private SearchManager.OnCancelListener cancelListener;
    private Context mContext;
    private String content;
    private int layoutId;
    private OnCloseListener listener;

    private RadioGroup radioGroup;
    private RadioButton rb_debug, rb_release, rb_dev3, rb_release_channel_1;
    private Button btn_ok;

    public EnvironmentDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public EnvironmentDialog(Context context, int themeResId, int layoutId, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.layoutId = layoutId;
        this.listener = listener;
    }


    public EnvironmentDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public EnvironmentDialog setCanceledOnTouchOutside(Boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public EnvironmentDialog setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        setCancelable(cancelable);
        initView();
    }

    private void initView() {

        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);


        setOnKeyListener(keylistener);

        radioGroup = findViewById(R.id.radioGroup);
        rb_debug = findViewById(R.id.rb_debug);
        rb_release = findViewById(R.id.rb_release);
        rb_release_channel_1 = findViewById(R.id.rb_release_channel_1);
        rb_dev3 = findViewById(R.id.rb_dev3);
        btn_ok = findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                if (rb_debug.isChecked()) {
                    CustomToast.showToast("debug");
                } else if (rb_release.isChecked()) {
                    CustomToast.showToast("release");
                } else if (rb_dev3.isChecked()) {
                    CustomToast.showToast("dev1");
                } else if (rb_release_channel_1.isChecked()) {
                    CustomToast.showToast("dev2");
                }

                if (listener != null) {
                    listener.onClick(this);
                }
                this.dismiss();
                break;


        }
    }


    public interface OnCloseListener {
        void onClick(Dialog dialog);
    }

    OnKeyListener keylistener = new OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };
}
