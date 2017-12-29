package org.navigation.mode;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.navigation.utils.Utils;


/**
 * description：title导航栏，通过DecorView来加载title控件，不用在布局中incloud布局
 * <p/>
 * Created by TIAN FENG on 2017/11/8
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */

public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbsNavigationParams> implements INavigationBar {

    // 无状态栏
    public static final int NO_IMMERSION = -1;

    private P mParams;

    private View mNavigationView;

    protected AbsNavigationBar(P params) {
        this.mParams = params;
        createAndBindView();
    }


    protected View getRootView() {
        return mNavigationView;
    }

    public P getParams() {
        return mParams;
    }


    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    protected void setText(int viewId, String text) {
        TextView tv = findViewById(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }


    /**
     * 设置是否显示
     */
    protected void setVisibility(int viewId, int visibility) {
        findViewById(viewId).setVisibility(visibility);
    }


    /**
     * 设置点击
     *
     * @param viewId
     * @param listener
     */
    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        if (listener != null) {
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(listener);
        }
    }

    /**
     * 设置背景颜色
     *
     * @param viewId
     * @param color
     */
    protected void setBackgroundColor(int viewId, int color) {
        findViewById(viewId).setBackgroundColor(color);
    }


    public <T extends View> T findViewById(int viewId) {
        return (T) mNavigationView.findViewById(viewId);
    }

    /**
     * 绑定和创建View
     */
    private void createAndBindView() {
        // 获取根View
        ViewGroup decorView = (ViewGroup) ((Activity) mParams.mContext).getWindow().getDecorView();
        // 拿 screen_simple
        ViewGroup screenSimple = (ViewGroup) decorView.getChildAt(0);
        /**
         * 一般情况下 screenSimple view直接可以添加title了
         * 这是添加的title在 状态栏之上
         * 但是在4.4 - 5.0 的手机上 状态栏透明为渐变色 体验较差
         * 5.0以上没问题
         *
         * 这里去找android.R.id.content
         */
        ViewGroup contentView = (ViewGroup) screenSimple.findViewById(android.R.id.content);

        // 状态栏view
        mNavigationView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(), contentView, false);

        // 添加NavigationView
        contentView.addView(mNavigationView, 0);

        // 是否需要沉浸式
        if (mParams.mIsImmersion) {
            Utils.statusBarTranslucent((Activity) mParams.mContext);
        }

        // 实现绑定和计算
        contentView.post(new NavigationRunable(mParams, this, mNavigationView, contentView));
    }


    public abstract static class Builder {

        // 构造器
        public Builder(Context context) {

        }

        protected abstract AbsNavigationParams getLaParams();

        // 沉浸式
        public Builder setImmersion() {
            getLaParams().mIsImmersion = true;
            return this;
        }

        // 沉浸式状态栏颜色
        public Builder setImmersionColor(int color) {
            getLaParams().mIsImmersion = true;
            getLaParams().mImmersionColor = color;
            return this;
        }

        // 移除状态栏
        public Builder removeStatusBar() {
            getLaParams().mIsImmersion = true;
            getLaParams().mRemoveStatusBar = true;
            return this;
        }

        public abstract <T extends AbsNavigationBar> T build();


        public class AbsNavigationParams {
            // 上下文 activity
            public Context mContext;
            // 是否需要沉浸式 需要子类赋值
            public boolean mIsImmersion;
            // 是否需要沉浸式 需要子类赋值
            public boolean mRemoveStatusBar = false;
            // 沉浸式颜色 默认透明
            public int mImmersionColor = Color.parseColor("#00000000");

            public AbsNavigationParams(Context context) {
                this.mContext = context;
            }
        }
    }
}
