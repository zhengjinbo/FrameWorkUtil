package com.zjb.frameworkutil.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zjb.frameworkutil.R;
import com.zjb.frameworkutil.activity.KotlinActivity;
import com.zjb.frameworkutil.api.SendIdentifyCodeApi;
import com.zjb.frameworkutil.base.BaseFragment;
import com.zjb.frameworkutil.dialog.EnvironmentDialog;
import com.zjb.frameworkutil.entity.SendIdentifyCodeBean;
import com.zjb.frameworkutil.https.HttpManager;
import com.zjb.frameworkutil.https.RetryWhenNetworkException;
import com.zjb.frameworkutil.impl.HttpOnNextListener;
import com.zjb.frameworkutil.utils.LogUtil;
import com.zjb.frameworkutil.views.CustomToast;
import com.zjb.frameworkutil.views.TitleBarView;

/**
 * @author ZJB
 * @date 2019/6/27 16:48
 */
public class Fragment1 extends BaseFragment {
    private TitleBarView titleBarView;
    private TextView textView;
    private Button btn;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }

    @Override
    protected void initUI(View view) {
        titleBarView = view.findViewById(R.id.titleBarView);
        titleBarView.getTv_in_title().setText("Fragment1");
        textView = view.findViewById(R.id.textView);
        btn = view.findViewById(R.id.btn);
    }

    @Override
    protected void initData() {
        LogUtil.e("Fragment1");
    }

    @Override
    protected void setListener() {
        super.setListener();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnvironmentDialog   environmentDialog = new EnvironmentDialog(getActivity(), R.style.TopDialog_Animation, R.layout.dialog_environment, new EnvironmentDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                       dialog.cancel();
                    }
                });
                environmentDialog.setCancelable(false);
                environmentDialog.setCanceledOnTouchOutside(false);
                environmentDialog.show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),KotlinActivity.class);
                startActivity(intent);
//                sendSmsCode("15959513145");
            }
        });

    }

    /**
     * 发送短信验证码
     *
     * @param phoneNum
     */
    private void sendSmsCode(String phoneNum) {
        SendIdentifyCodeApi sendIdentifyCodeApi = new SendIdentifyCodeApi((RxAppCompatActivity) getActivity());
        sendIdentifyCodeApi.setPhone(phoneNum);
        sendIdentifyCodeApi.setSmsType("1");
        sendIdentifyCodeApi.setAreaCode("86");
        sendIdentifyCodeApi.setListener(new HttpOnNextListener<SendIdentifyCodeBean>() {
            @Override
            public RetryWhenNetworkException.Wrapper onNext(SendIdentifyCodeBean sendIdentifyCodeBean) {
                return null;
            }
        });
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(getActivity(), sendIdentifyCodeApi);
    }



}
