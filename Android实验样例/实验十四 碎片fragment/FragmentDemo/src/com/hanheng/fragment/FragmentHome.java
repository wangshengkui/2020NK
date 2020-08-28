package com.hanheng.fragment;
import com.hanheng.fragmentdemo.R;
import com.hanheng.fragmentdemo.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;



public class FragmentHome extends Fragment{
	private Button btn;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_index, null);
		btn=(Button)view.findViewById(R.id.button);
		btn.setOnClickListener
		(
				new View.OnClickListener(){
					public void onClick(View v){
						Log.i("触发事件", "被点击了");
//						页面跳转
						Intent intent=new Intent(getActivity(),MyActivity.class);
						startActivity(intent);
					}
				}
				);
		return view;
	}
}
