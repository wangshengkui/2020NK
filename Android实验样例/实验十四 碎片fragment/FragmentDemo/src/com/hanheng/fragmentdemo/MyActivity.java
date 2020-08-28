package com.hanheng.fragmentdemo;

import com.hanheng.fragment.FragmentHome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity{
	private TextView index_city_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next_index);
		index_city_back=(TextView)findViewById(R.id.index_city_back);
		
		index_city_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("事件触发", "触发成功");
				finish();
			}
		});
	}
}
