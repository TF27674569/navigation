package com.easy.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.api.ViewUtils;

/**
 * description：
 * <p/>
 * Created by TIAN FENG on 2017/12/29.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Utils.statusBarTintColor(this, Color.parseColor("#30000000"));

        setContentView();
        ViewUtils.bind(this);
        initTitle();
        initView();
        initData();
        initEvent();
    }

    protected abstract void setContentView();

    protected abstract void initTitle();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();


    protected void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
