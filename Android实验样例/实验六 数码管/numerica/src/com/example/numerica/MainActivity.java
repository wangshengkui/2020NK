package com.example.numerica;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hanheng.a53.seg7.Seg7Class;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "ShowToast", "HandlerLeak" }) 
public class MainActivity extends Activity implements OnClickListener {
	private View display;
	private EditText text;
	private TextView counterText;
	private Button count_btn;
	private Button count_submit;
	private Button count_stop;
//	定义标志位控制启动线程
	private boolean flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		初始化标签
		init();
	}

	// 初始化获取每个视图和控件,并添加按钮点击事件
	public void init() {
		this.display = (View) findViewById(R.id.display1);
		this.count_btn = (Button) display.findViewById(R.id.count);
		this.text = (EditText) display.findViewById(R.id.editText1);
		this.counterText = (TextView) display.findViewById(R.id.counterText);
		this.count_submit = (Button) display.findViewById(R.id.submit);
		this.count_stop = (Button) display.findViewById(R.id.stop);
		this.count_submit.setOnClickListener(this);
		this.count_btn.setOnClickListener(this);
		this.count_stop.setOnClickListener(this);
		int err = Seg7Class.Init();
		System.out.println("测试:"+err);
	}

	// 定义handler接收线程回传内容
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				updateText(msg.arg1);
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			String content = text.getText().toString();
			flag = false;
			if (content.length() > 4) {
				Log.i("监听", content);
				Toast.makeText(getApplication(), "请输入4位数", Toast.LENGTH_LONG);
			} else {
				this.updateText(Integer.valueOf(content));
			}
			break;
		case R.id.count:
			if (!this.flag) {
				MyThread thread = new MyThread();
				this.flag = true;
				thread.start();
			}
			break;
		case R.id.stop:
			this.flag = false;
			break;
		default:
			break;
		}
	}
	
	public void updateText(final int arg){
		String str = addZero(String.valueOf(arg));
		this.counterText.setText(str);
		/**
		 * 请在此补充硬件调用函数
		*/
		new Thread(new Runnable() {
			public void run() {
				Seg7Class.Seg7Show(arg);
			}
		}).start();
	}

	// 不足4位进行补零
	public String addZero(String content) {
		int count = 4 - content.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append("0");
		}
		StringBuffer str = sb.append(content);
		return str.toString();
	}

	// 定义内部线程类
	class MyThread extends Thread {
		public void run() {
			int num = 0;
			while (flag) {
				try {			
					num++;
//					定义消息对象
					Message msg = new Message();
					msg.what = 1;
					msg.arg1 = num;
//					通过Handler发送消息
					uiHandler.sendMessage(msg);
					
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override  
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
				Seg7Class.Exit();
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
