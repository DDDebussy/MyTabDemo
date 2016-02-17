package com.example.mytabdemo.selfwidget;

import android.content.Context;
import android.util.AttributeSet;

import com.example.mytabdemo.R;


public class MyRadioButton extends android.widget.RadioButton {

	private String mValue;

	public MyRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, R.style.myTabStyle);
		
	}
	public String getValue() {
		return this.mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}

}