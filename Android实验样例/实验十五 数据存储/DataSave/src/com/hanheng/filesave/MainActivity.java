package com.hanheng.filesave;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
	private Button saveData;
	private Button restoreData;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		saveData.setOnClickListener(this);
		restoreData.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	初始化Button
	private void init(){
		saveData=(Button)findViewById(R.id.save_data);
		restoreData=(Button)findViewById(R.id.restore_data);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getId();
		switch(id){
		case R.id.save_data:
//			得到SharedPreferences.Editor对象，指定SharedPreferences的文件名为data
			SharedPreferences.Editor editor=getSharedPreferences("data", MODE_PRIVATE).edit();
//			以键值对形式存放数据
			editor.putString("name", "Tom");
			editor.putInt("age", 28);
			editor.putBoolean("married",false);
			editor.commit();
			Toast.makeText(getApplicationContext(), "保存数据成功",Toast.LENGTH_SHORT).show();
			break;
		case R.id.restore_data:
//			得到SharedPreferences对象
			SharedPreferences pref=getSharedPreferences("data", MODE_PRIVATE);
//			通过键值获取数据
			String name=pref.getString("name", "");
			int age=pref.getInt("age", 0);
			boolean married=pref.getBoolean("married", false);
			Toast.makeText(getApplicationContext(), "获取数据"+name+"年龄"+age+"是否结婚"+married, 0).show();
			break;
			default:
				break;
		}	
	}
}
