package org.navigation.mode;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.navigation.utils.Utils;


/**
 * description：测量绑定高度处理
 * <p/>
 * Created by TIAN FENG on 2017/12/29.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
class NavigationRunable implements Runnable {
    private AbsNavigationBar.Builder.AbsNavigationParams mParams;
    private AbsNavigationBar mNavigationBar;
    private View mNavigationView;
    private ViewGroup mContentView;

    public NavigationRunable(AbsNavigationBar.Builder.AbsNavigationParams params, AbsNavigationBar navigationBar, View navigationView, ViewGroup contentView) {
        mParams = params;
        mNavigationBar = navigationBar;
        mNavigationView = navigationView;
        mContentView = contentView;
    }

    @Override
    public void run() {
        // 拿到自己的activity对应的布局对应的layoutparams
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mContentView.getChildAt(1).getLayoutParams();
        // 如果需要沉浸式 , 判断需不需要移除statusbar
        if (mParams.mIsImmersion && !mParams.mRemoveStatusBar) {
            // 设置间距为mNavigationView的高
            layoutParams.topMargin = mNavigationView.getMeasuredHeight() + Utils.getStatusBarHeight(mParams.mContext);
        } else {
            // 设置间距为mNavigationView的高
            layoutParams.topMargin = mNavigationView.getMeasuredHeight();
        }

        // 实现数据的绑定
        mNavigationBar.applyView();

    }

}
