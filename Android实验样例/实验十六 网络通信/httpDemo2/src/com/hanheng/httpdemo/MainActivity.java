package com.hanheng.httpdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.R.integer;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Button sendRequest;
	private TextView responseText;
	private String getHttpData;
	String address="http://www.baidu.com";
	public static final int SHOW_RESPONSE = 0;
	private Handler handler =new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case SHOW_RESPONSE:
//				获得消息字符串
				String msgString=(String) msg.obj;
//				给responseText赋值
				responseText.setText(msgString);
				break;
			default:
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		初始化控件
		sendRequest = (Button) findViewById(R.id.send_request);
		responseText = (TextView) findViewById(R.id.response);
		sendRequest.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int key=arg0.getId();
		switch (key) {
		case R.id.send_request:
			//发送请求
			sendRequestWithHttpURLConnection();		
			break;
		default:
			break;
		}		
	}	
	private void sendRequestWithHttpURLConnection() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//发送请求
				getHttpData=new HttpUtil().sendHttpRequest(address);
//				创建消息对象
				Message msgMessage=new Message();
//				绑定消息的key值
				msgMessage.what=SHOW_RESPONSE ;
//				获取消息内容
				msgMessage.obj=getHttpData;
//				通过handler将消息发送出去
				handler.sendMessage(msgMessage);
			}
		}).start();
	}

}
