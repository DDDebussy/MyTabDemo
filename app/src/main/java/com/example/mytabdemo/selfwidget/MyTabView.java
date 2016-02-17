package com.example.mytabdemo.selfwidget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mytabdemo.MainActivity;
import com.example.mytabdemo.R;
import com.example.mytabdemo.listener.OnTabSelectListener;
import com.example.mytabdemo.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Created by 王宗贤 on 2016/1/25.
 */
public class MyTabView extends LinearLayout implements View.OnClickListener {
    private ArrayList<String> mTabNameList;
    public static List<RadioButton> mRadioList;
    private int count;
    private Context context;
    private int currentPosition;
    private OnTabSelectListener listener;
    private List<Integer> unSelectImgs;
    private List<Integer> selectImgs;


    public MyTabView(Context context) {
        super(context);
    }

    public MyTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        count = MainActivity.count;
        this.context = context;
//        initView();
    }

    public MyTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        count = MainActivity.count;
        this.context = context;
//        initView();
    }

    /**
     * 传入选中时和未选中是的图片
     * @param unSelectImgs
     * @param selectImgs
     */
    public void setDate(List<Integer> unSelectImgs,List<Integer> selectImgs){
        this.unSelectImgs=unSelectImgs;
        this.selectImgs=selectImgs;
        initView();
    }
    /**
     * 初始化布局
     */
    private void initView() {
//        View tabView = LayoutInflater.from(context).inflate(R.layout.tab_view, this);
        View tabView=LayoutInflater.from(getContext()).inflate(R.layout.tab_view,this);
        RadioGroup radioGroup = (RadioGroup) tabView.findViewById(R.id.radiogroup);
//        int screenWith = ScreenUtils.getScreenWidth(context);
        int screenWith=ScreenUtils.getScreenWidth(getContext());
        radioGroup.setGravity(Gravity.CENTER_HORIZONTAL);

        mTabNameList = new ArrayList<String>();
        mTabNameList.add("首页");
        mTabNameList.add("订单");
        mTabNameList.add("发现");
        mTabNameList.add("我");
        mRadioList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            FrameLayout frameLayout = new FrameLayout(getContext());
//            FrameLayout frameLayout = new FrameLayout(context);

//            RadioButton radioButton = new RadioButton(context,null, R.style.myTabStyle);
            RadioButton radioButton = new RadioButton(getContext(),null, R.style.myTabStyle);
            radioButton.setText(mTabNameList.get(i));
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            radioButton.setGravity(Gravity.CENTER_HORIZONTAL);
            radioButton.setPadding(0, 4, 0, 6);
            radioButton.setCompoundDrawablePadding(2);
            if(i==0){
                radioButton.setCompoundDrawablesWithIntrinsicBounds(0, selectImgs.get(0), 0, 0);
                radioButton.setTextColor(getResources().getColor(R.color.checked));
            }else{
                radioButton.setCompoundDrawablesWithIntrinsicBounds(0, unSelectImgs.get(i), 0, 0);
                radioButton.setTextColor(getResources().getColor(R.color.unChecked));
            }

            frameLayout.addView(radioButton);
            radioGroup.addView(frameLayout, screenWith / count, ViewGroup.LayoutParams.WRAP_CONTENT);
            mRadioList.add(radioButton);


        }
        for (int i = 0; i < count; i++) {
            mRadioList.get(i).setOnClickListener(this);
        }

        Activity activity = (Activity) getContext();

        //如果activity不是onTabSelectListener的一个引用，则抛出异常
        if (!(activity instanceof OnTabSelectListener)) {
            throw new IllegalStateException(
                    "Activity must implement TabSelectView OnTableSelectListener.");
        }
        listener = (OnTabSelectListener) activity;
//        listener.setPosition(0);

    }

    @Override
    public void onClick(View view) {
        currentPosition = getCurrentPosition(view);
        listener.setPosition(currentPosition);
        listener.selectTab(currentPosition);
    }


    public int getCurrentPosition(View view) {
        for (int i = 1; i < mRadioList.size(); i++) {
            if (view == mRadioList.get(i)) {
                return i;
            }
        }
        return 0;
    }
}
