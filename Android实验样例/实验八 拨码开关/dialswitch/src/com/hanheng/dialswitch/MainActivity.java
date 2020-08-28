package com.hanheng.dialswitch;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.security.auth.PrivateCredentialPermission;
import com.hanheng.a53.dip.DipClass;
import com.hanheng.dialswitch.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.provider.MediaStore.Video;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener{
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;
	private TextView text5;
	private TextView text6;
	private TextView text7;
	private TextView text8;	
	private ToggleButton tb1;
	private ToggleButton tb2;
	private ToggleButton tb3;
	private ToggleButton tb4;
	private ToggleButton tb5;
	private ToggleButton tb6;
	private ToggleButton tb7;
	private ToggleButton tb8;
	private Button btnButton;
	private boolean flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		this.btnButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	初始化组件
	public void initView(){
		tb1=(ToggleButton)findViewById(R.id.toggleButton1);
		tb2=(ToggleButton)findViewById(R.id.toggleButton2);
		tb3=(ToggleButton)findViewById(R.id.toggleButton3);
		tb4=(ToggleButton)findViewById(R.id.toggleButton4);
		tb5=(ToggleButton)findViewById(R.id.toggleButton5);
		tb6=(ToggleButton)findViewById(R.id.toggleButton6);
		tb7=(ToggleButton)findViewById(R.id.toggleButton7);
		tb8=(ToggleButton)findViewById(R.id.toggleButton8);	
		text1=(TextView)findViewById(R.id.textView1);
		text2=(TextView)findViewById(R.id.textView2);
		text3=(TextView)findViewById(R.id.textView3);
		text4=(TextView)findViewById(R.id.textView4);
		text5=(TextView)findViewById(R.id.textView5);
		text6=(TextView)findViewById(R.id.textView6);
		text7=(TextView)findViewById(R.id.textView7);
		text8=(TextView)findViewById(R.id.textView8);
		btnButton=(Button)findViewById(R.id.button1);		
		Log.i("初始化", "消息："+DipClass.Init());
		openThread();

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int key=arg0.getId();
		switch (key) {
		case R.id.button1:
			exit();
			break;
		default:
			break;
		}

	}
	//	拨码开关状态显示
	public void computed(int val){
		String str=addZero(val);
		char[] cr=str.toCharArray();
		int tag;
		for(int i=0;i<cr.length;i++){
			if(cr[i]=='0'){
				tag=0;
				changeState(i,tag);
			}else{		
				changeState(i,1);				
			}
		}
		
	}
//	字符串补零
	public String addZero(int b){
		String val = Integer.toBinaryString(b&0xFF);
		String str="";
		if(val.length()<8){
			for(int i=0;i<8-val.length();i++){
				str+=0;
			}			
			return str+=val;
		}
		return val;
	}
	
	public void exit(){		
		AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("程序退出")
		.setMessage("您确定要退出吗？")
		.setIcon(R.drawable.ic_launcher);
		dialog.setCancelable(false);
		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialog, int which) {
				flag=false;
				DipClass.Exit();
				MainActivity.this.finish(); //操作结束
			}
		});
		
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
			
	}

//	改变拨码开关的状态
	public void changeState(int i,int tag){
		if(tag==0){
			switch (i) {
			case 0:
				tb8.setChecked(true);				
				break;
			case 1:
				tb7.setChecked(true);				
				break;
			case 2:
				tb6.setChecked(true);				
				break;
			case 3:
				tb5.setChecked(true);				
				break;
			case 4:
				tb4.setChecked(true);				
				break;
			case 5:
				tb3.setChecked(true);				
				break;
			case 6:
				tb2.setChecked(true);				
				break;
			case 7:
				tb1.setChecked(true);				
				break;
			default:
				break;
			}
		}else{
			switch (i) {
			case 0:
				tb8.setChecked(false);				
				break;
			case 1:
				tb7.setChecked(false);				
				break;
			case 2:
				tb6.setChecked(false);				
				break;
			case 3:
				tb5.setChecked(false);				
				break;
			case 4:
				tb4.setChecked(false);				
				break;
			case 5:
				tb3.setChecked(false);				
				break;
			case 6:
				tb2.setChecked(false);				
				break;
			case 7:
				tb1.setChecked(false);				
				break;
			default:
				break;
			}
		}
	}
	//	初始化按钮文字
	private Handler uiHandler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==1){	
				Log.i("获取数据", ""+msg.arg1);
				computed(msg.arg1);
			}
		}
	};
    
public void openThread(){
			if(!flag){
				MyThread thread=new MyThread();
				this.flag=true;
				thread.start();
			}		
	}
	//	读取字符线程
	class MyThread extends Thread{
		public void run(){
			int num = 0;				
			while(flag){
				try {				
					Message msgMessage=new Message();
					int value=DipClass.ReadValue();
					msgMessage.what=1;
					msgMessage.arg1=value;
					uiHandler.sendMessage(msgMessage);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}		
}
