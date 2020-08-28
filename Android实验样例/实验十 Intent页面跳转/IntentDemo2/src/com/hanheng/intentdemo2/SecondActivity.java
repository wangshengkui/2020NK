package com.hanheng.intentdemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity{
	private Button button2;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		button2=(Button)findViewById(R.id.button_2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				获取到intent
				Intent intent=new Intent();
//				将数据传递
				intent.putExtra("data_return", "Hello FirstActivity");
				setResult(RESULT_OK, intent);
//				销毁
				finish();			
			}
		});
	}
}
