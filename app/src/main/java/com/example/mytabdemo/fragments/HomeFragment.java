package com.example.mytabdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytabdemo.R;


/**
 * Created by SMouse on 2016/1/26.
 */
public class HomeFragment extends BaseFragment{

    private static final String TAG = "HomeFragment";
    private Context mContext = null;
    View rootView=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }
    @Override
    protected void lazyLoad() {

    }
}
