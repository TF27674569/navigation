package com.easy;

import android.graphics.Color;
import android.widget.TextView;

import com.easy.base.BaseActivity;
import com.easy.navigation.CommonNavigation;

import org.annotation.ViewById;

/**
 * description：
 * <p>
 * Created by TIAN FENG on 2017/12/29.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public class MainActivity extends BaseActivity {


    @ViewById(R.id.textView)
    public TextView textView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initTitle() {
        new CommonNavigation.Builder(this)
                .setTitle("首页")
                .setImmersionColor(Color.parseColor("#60000000"))
//                .removeStatusBar()
//                .setImmersion()
                .build();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
