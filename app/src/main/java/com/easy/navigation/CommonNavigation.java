package com.easy.navigation;

import android.content.Context;
import android.view.View;

import com.easy.R;

import org.navigation.mode.AbsNavigationBar;
import org.navigation.utils.Utils;

/**
 * description：
 * <p>
 * Created by TIAN FENG on 2017/12/29.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public class CommonNavigation extends AbsNavigationBar<CommonNavigation.Builder.Params> {

    protected CommonNavigation(Builder.Params params) {
        super(params);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.layout_title;
    }

    @Override
    public void applyView() {
        setText(R.id.tvTitle, getParams().mTitle);
        setOnClickListener(R.id.btnBack, getParams().mBackClickListener);
        if (getParams().mIsImmersion && !getParams().mRemoveStatusBar) {
            findViewById(R.id.vStatusBar).getLayoutParams().height = Utils.getStatusBarHeight(getParams().mContext);
            setBackgroundColor(R.id.vStatusBar, getParams().mImmersionColor);
        }
    }

    public static class Builder extends AbsNavigationBar.Builder {
        private Params P;

        public Builder(Context context) {
            super(context);
            P = new Params(context);
        }

        @Override
        protected AbsNavigationParams getLaParams() {
            return P;
        }


        public Builder setBackClickListener(View.OnClickListener listener) {
            P.mBackClickListener = listener;
            return this;
        }

        public Builder setTitle(String title) {
            P.mTitle = title;
            return this;
        }


        @Override
        public CommonNavigation build() {
            return new CommonNavigation(P);
        }

        public class Params extends AbsNavigationParams {
            private String mTitle;
            private View.OnClickListener mBackClickListener;

            public Params(Context context) {
                super(context);
            }
        }
    }
}
