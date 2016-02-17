package com.example.mytabdemo.fragments;

import android.support.v4.app.Fragment;

/**
 * 继承此Fragment
 * Created by SMouse on 2016/1/25.
 */
public abstract class BaseFragment extends Fragment {

    //当前的Fragment是否可见
    protected boolean isVisible ;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint())
        {
            isVisible = true;
            onVisible();
        }
        else
        {
            isVisible = false;
            onInVisible();
        }
    }
    /**
     * 当Fragment可见时执行的函数
     */
    private void onVisible()
    {
        lazyLoad();
    }
    /**
     * 当Fragment不可见执行的函数
     */
    private void onInVisible() {}

    /**
     * 继承此Fragment 需实现此方法
     */
    protected abstract void lazyLoad();
}
