package com.hanheng.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends Activity{
	private Button btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		btn2=(Button)findViewById(R.id.button_2);
		btn2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				获得跳转的intent
				Intent intent=getIntent();
//				通过key获得跳转传过来的数据
				String data=intent.getStringExtra("extra_data");
//				打印传递的数据
				Toast.makeText(getApplicationContext(),"接收到数据"+data, 0).show();
				Log.d("SecondActivity", data);			
			}
		});
			
	}
	
}
