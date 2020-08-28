package com.hanheng.fragmentdemo;

import com.hanheng.fragment.FragmentHome;
import com.hanheng.fragment.FragmentMy;
import com.hanheng.fragment.FragmentSearch;
import com.hanheng.fragment.FragmentTuan;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
	private RadioGroup group;
	private RadioButton main_home;
	//	Fragment代理
	private FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		//初始化FragmentManager
		fragmentManager=getSupportFragmentManager();		
		main_home.setChecked(true);
		group.setOnCheckedChangeListener( this);
		//切换不同的fragment
		changeFragment(new FragmentHome(), false);
	}
	public void initView(){
		group=(RadioGroup)findViewById(R.id.main_bottom_tabs);
		main_home=(RadioButton)findViewById(R.id.main_home);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.main_home:
			changeFragment(new FragmentHome(), true);
			break;
		case R.id.main_my://我的
			changeFragment(new FragmentMy(), true);
			break;
		case R.id.main_search://发现
			changeFragment(new FragmentSearch(), true);
			break;
		case R.id.main_tuan://团购
			changeFragment(new FragmentTuan(), true);
			break;
		default:
			break;
		}

	}

	public void changeFragment(Fragment fragment,boolean isInit){
		//		开启事务
		FragmentTransaction transaction =fragmentManager.beginTransaction();
		transaction.replace(R.id.main_content, fragment);
		if(!isInit){
			transaction.addToBackStack(null);
		}
		transaction.commit();
	}
}
