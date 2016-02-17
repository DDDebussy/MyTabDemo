package com.example.mytabdemo;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytabdemo.fragments.DiscoverFragment;
import com.example.mytabdemo.fragments.HomeFragment;
import com.example.mytabdemo.fragments.MeFragment;
import com.example.mytabdemo.fragments.OrderFragment;
import com.example.mytabdemo.listener.OnTabSelectListener;
import com.example.mytabdemo.selfwidget.MyTabView;
import com.example.mytabdemo.selfwidget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnTabSelectListener{
    public static int count=4;
    private List<Integer> selectImgs;
    private List<Integer> unSelectImgs;
    private MyTabView myTabView;
    private MyViewPager mvp;
    private FragmentPagerAdapter mFragmentAdapter;
    private ArrayList<Fragment> mFragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mFragments.add(new HomeFragment());
        mFragments.add(new OrderFragment());
        mFragments.add(new DiscoverFragment());
        mFragments.add(new MeFragment());
        myTabView= (MyTabView) findViewById(R.id.mytabview);
        mvp= (MyViewPager) findViewById(R.id.mvp);
        mFragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),mFragments);
        mvp.setAdapter(mFragmentAdapter);
        mvp.setOffscreenPageLimit(count);
        mvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        selectImgs=new ArrayList<Integer>();
        selectImgs.add(R.mipmap.merchant_icon_press);
        selectImgs.add(R.mipmap.order_icon_press);
        selectImgs.add(R.mipmap.discovery_press);
        selectImgs.add(R.mipmap.usercoupons_press);

        unSelectImgs=new ArrayList<Integer>();
        unSelectImgs.add(R.mipmap.merchant_icon_normal);
        unSelectImgs.add(R.mipmap.order_icon_normal);
        unSelectImgs.add(R.mipmap.discovery_normal);
        unSelectImgs.add(R.mipmap.usercoupons_normal);
        myTabView.setDate(unSelectImgs,selectImgs);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void setPosition(int position) {
        for(int i=0;i<count;i++){
            if(position==i){
                MyTabView.mRadioList.get(i).setTextColor(getResources().getColor(R.color.checked));
                MyTabView.mRadioList.get(i).setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(selectImgs.get(i)),null,null);
            }else{
                MyTabView.mRadioList.get(i).setTextColor(getResources().getColor(R.color.unChecked));
                MyTabView.mRadioList.get(i).setCompoundDrawablesWithIntrinsicBounds(null,getResources().getDrawable(unSelectImgs.get(i)),null,null);
            }
        }
    }

    @Override
    public void selectTab(int position) {
        if (mvp!=null) {
            mvp.setCurrentItem(position,false);
        }
    }

    private class FragmentAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> mFragements;

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> mFragements) {
            super(fm);
            this.mFragements = mFragements;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragements.get(position);
        }

        @Override
        public int getCount() {
            return mFragements==null?0:mFragements.size();
        }
    }
}
