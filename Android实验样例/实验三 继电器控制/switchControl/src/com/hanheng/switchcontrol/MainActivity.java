package com.hanheng.switchcontrol;

import com.example.switchcontrol.R;
import com.hanheng.a53.relay.RelayClass;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener{
	private int[] array={R.drawable.open,R.drawable.close};
	private ImageView image1;
	private ImageView image2;
	private ToggleButton toggle1;
	private ToggleButton toggle2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		初始化
		toggle1 = (ToggleButton)findViewById(R.id.togglebtn1);
		toggle2=(ToggleButton)findViewById(R.id.togglebtn2);	
	    image1=(ImageView)findViewById(R.id.imgv1);
		image2=(ImageView)findViewById(R.id.imgv2);
		Button open_all=(Button)findViewById(R.id.button1);
		Button off_all=(Button)findViewById(R.id.button2);	
		RelayClass.Init();
//		调用点击事件
		toggle1.setOnClickListener(this);
		toggle2.setOnClickListener(this);
		open_all.setOnClickListener(this);
		off_all.setOnClickListener(this);
	}		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//继电器控制
	public void switchToggle(int num,ImageView img,ToggleButton btn){
		if(btn.isChecked()){
			img.setImageResource(array[1]);
			RelayClass.IoctlRelay(num, 0);
		}else{
			img.setImageResource(array[0]);
			RelayClass.IoctlRelay(num, 1);
		}
	}
//	全开
	public void allCotrol(int num,int imgId,boolean state){
		image1.setImageResource(imgId);
		image2.setImageResource(imgId);
		toggle1.setChecked(state);
		toggle2.setChecked(state);
		RelayClass.IoctlRelay(0, num);
		RelayClass.IoctlRelay(1, num);
	}	
	@Override
//	点击事件
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int key=arg0.getId();
		switch(key){
		case R.id.togglebtn1:	
			switchToggle(0,image1,toggle1);
			break;
		case R.id.togglebtn2:
			switchToggle(1,image2,toggle2);
			break;
		case R.id.button1:
			allCotrol(0,array[1],true);	
			break;
		case R.id.button2:		
			allCotrol(1,array[0],false);
			break;			
		}		
	}	
//	dialog退出
    public boolean onKeyDown(int keyCode, KeyEvent event){  
		if (keyCode == KeyEvent.KEYCODE_BACK  ){  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            isExit.setTitle("系统提示");  
            isExit.setMessage("确定要退出吗");  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            isExit.show();    
        }  
		return false;  
	}  
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){  
		public void onClick(DialogInterface dialog, int which){  
			switch (which){  
			case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
				RelayClass.Exit();
				finish();   
				break;  
			case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
				break;  
			default:  
				break;  
            }  
        }  
    };
}
