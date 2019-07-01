package com.zjb.frameworkutil.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjb.frameworkutil.R;


/**
 * Created by Longer on 2016/10/26.
 */
public class TitleBarView extends RelativeLayout {


    private TextView mTv_in_title;
    private ImageView mIv_in_title_back;
    private TextView mTv_in_title_back_tv;
    private TextView mTv_in_title_right;
    private ImageView mIv_in_title_right;
    private RelativeLayout mRl_title_container;
    private FrameLayout mFl_title_cart;
    private TextView mTv_title_cart_amount_note;
    private ImageView iv_title_more;

    public TitleBarView(Context context) {
        this(context, null);
        //initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        //initView(context);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.titlebar_layout, this);
        mTv_in_title = (TextView) findViewById(R.id.tv_in_title);
        mIv_in_title_back = (ImageView) findViewById(R.id.iv_in_title_back);
        mTv_in_title_back_tv = (TextView) findViewById(R.id.tv_in_title_back_tv);
        mTv_in_title_right = (TextView) findViewById(R.id.tv_in_title_right);
        mIv_in_title_right = (ImageView) findViewById(R.id.iv_in_title_right);
        mRl_title_container = (RelativeLayout) findViewById(R.id.rl_title_container);
        iv_title_more = (ImageView) findViewById(R.id.iv_title_more);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mytitlebar, defStyleAttr, 0);
        String text = a.getString(R.styleable.mytitlebar_text);
        Integer backSrc = a.getResourceId(R.styleable.mytitlebar_back_src, 0);
        mTv_in_title.setText(text);
        int color = a.getColor(R.styleable.mytitlebar_background_color , ContextCompat.getColor(context, R.color.white));
        this.setBackgroundColor(color);
        int textColor = a.getColor(R.styleable.mytitlebar_text_color , ContextCompat.getColor(context, R.color.black));
        mTv_in_title.setTextColor(textColor);
        mIv_in_title_back.setImageResource(backSrc);
        a.recycle(); // 回收资源
        //initView(context);
    }

    private void initView(Context context) {

    }


    public RelativeLayout getRl_title_container() {
        return mRl_title_container;
    }


    public void setTitleText(String titleText) {
        mTv_in_title.setText(titleText);
    }

    public ImageView getIv_in_title_back() {
        mIv_in_title_back.setVisibility(VISIBLE);
        return mIv_in_title_back;
    }

    public TextView getmTv_in_title_back_tv() {
        mTv_in_title_back_tv.setVisibility(VISIBLE);
        return mTv_in_title_back_tv;
    }

    public TextView getTv_in_title_right() {
        mTv_in_title_right.setVisibility(VISIBLE);
        return mTv_in_title_right;
    }

    public ImageView getIv_in_title_right() {
        mIv_in_title_right.setVisibility(VISIBLE);
        return mIv_in_title_right;
    }

    public FrameLayout getFl_title_cart() {
        mFl_title_cart.setVisibility(VISIBLE);
        return mFl_title_cart;
    }

    public TextView getTv_title_cart_amount_note() {
        return mTv_title_cart_amount_note;
    }

    public ImageView getIv_title_more() {
        iv_title_more.setVisibility(VISIBLE);
        return iv_title_more;
    }

    public TextView getTv_in_title() {
        return mTv_in_title;
    }
}