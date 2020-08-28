package com.hanheng.horserace;

import com.hanheng.a53.led.LedClass;
import com.hanheng.horserace1.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements View.OnClickListener{
	private ImageView light1;
	private ImageView light2;
	private ImageView light3;
	private ImageView light4;
	private ToggleButton btn1_toggle;
	private ToggleButton btn2_toggle;
	private ToggleButton btn3_toggle;
	private ToggleButton btn4_toggle;
	private Button openAllButton;
	private Button offAllButton;
	private Button 	horLightOpen;
	private Button 	horLightOff;
	private boolean flag;
	public int[] array={R.drawable.deng_on,R.drawable.deng_off};
	private boolean[] arryState={false,false,false,false};
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		this.btn1_toggle.setOnClickListener(this);
		this.btn2_toggle.setOnClickListener(this);
		this.btn3_toggle.setOnClickListener(this);
		this.btn4_toggle.setOnClickListener(this);
		this.openAllButton.setOnClickListener(this);
		this.offAllButton.setOnClickListener(this);
		this.horLightOpen.setOnClickListener(this);
		this.horLightOff.setOnClickListener(this);
	}

	//	初始化视图控件
	private void initViews(){
//		通过第一个include id找到子元素toggleButton
		this.btn1_toggle=(ToggleButton)findViewById(R.id.display1).findViewById(R.id.togglebtn1);
//		给btn1_toggle设置标志位1;
		this.btn1_toggle.setTag(1);
//		通过第二个include id找到子元素toggleButton
		this.btn2_toggle=(ToggleButton)findViewById(R.id.display2).findViewById(R.id.togglebtn1);
//		给btn2_toggle设置标志位2;	
		this.btn2_toggle.setTag(2);
//		通过第三个include id找到子元素toggleButton		
		this.btn3_toggle=(ToggleButton)findViewById(R.id.display3).findViewById(R.id.togglebtn1);
//		给btn3_toggle设置标志位3;
		this.btn3_toggle.setTag(3);
		this.btn4_toggle=(ToggleButton)findViewById(R.id.display4).findViewById(R.id.togglebtn1);
		this.btn4_toggle.setTag(4);
		this.light1=(ImageView)findViewById(R.id.display1).findViewById(R.id.imgv1);
		this.light2=(ImageView)findViewById(R.id.display2).findViewById(R.id.imgv1);
		this.light3=(ImageView)findViewById(R.id.display3).findViewById(R.id.imgv1);
		this.light4=(ImageView)findViewById(R.id.display4).findViewById(R.id.imgv1);
		this.openAllButton=(Button)findViewById(R.id.all_open);
		this.openAllButton.setTag(5);
		this.offAllButton=(Button)findViewById(R.id.all_off);
		this.offAllButton.setTag(6);
		this.horLightOpen=(Button)findViewById(R.id.horse_open);
		this.horLightOpen.setTag(7);
		this.horLightOff=(Button)findViewById(R.id.horse_off);
		this.horLightOff.setTag(8);
	
		text1=(TextView)findViewById(R.id.display1).findViewById(R.id.title);
		text1.setText("灯1");
		text2=(TextView)findViewById(R.id.display2).findViewById(R.id.title);
		text2.setText("灯2");
		text3=(TextView)findViewById(R.id.display3).findViewById(R.id.title);
		text3.setText("灯3");
		text4=(TextView)findViewById(R.id.display4).findViewById(R.id.title);
		text4.setText("灯4");
		LedClass.Init();
		DipClass.Init();
	}

	//	灯光开关切换
	public void dengToggle(int led_num,ToggleButton btn,ImageView light){
		if(btn.isChecked()){
			light.setImageResource(array[0]);
			LedClass.IoctlLed(led_num, 1);
		}else{
			light.setImageResource(array[1]);
			LedClass.IoctlLed(led_num, 0);
		}
	}	
	
	public void dengControl(int led_num,ToggleButton btn,ImageView light,boolean state){
		if(state){
			light.setImageResource(array[0]);
			LedClass.IoctlLed(led_num, 1);
			btn.setChecked(true);
		}else{
			light.setImageResource(array[1]);
			LedClass.IoctlLed(led_num, 0);
			btn.setChecked(false);
		}
	}
	
	//	灯光全开
	private void allLightOpen(){
		dengControl(0,btn1_toggle,light1,true);
		dengControl(1,btn2_toggle,light2,true);
		dengControl(2,btn3_toggle,light3,true);
		dengControl(3,btn4_toggle,light4,true);
	}	
	//	灯光全关
	private void allLightOff(){
		dengControl(0,btn1_toggle,light1,false);
		dengControl(1,btn2_toggle,light2,false);
		dengControl(2,btn3_toggle,light3,false);
		dengControl(3,btn4_toggle,light4,false);
	}
	private void imageClose(){
		light1.setImageResource(this.array[1]);
		light2.setImageResource(this.array[1]);
		light3.setImageResource(this.array[1]);
		light4.setImageResource(this.array[1]);
	}
	private void imageOpen(){
		light1.setImageResource(this.array[0]);
		light2.setImageResource(this.array[0]);
		light3.setImageResource(this.array[0]);
		light4.setImageResource(this.array[0]);
	}
	
	private void toggleOpen(){
		btn1_toggle.setChecked(true);
		btn2_toggle.setChecked(true);
		btn3_toggle.setChecked(true);
		btn4_toggle.setChecked(true);
	}
	private void toggleOff(){
		btn1_toggle.setChecked(false);
		btn2_toggle.setChecked(false);
		btn3_toggle.setChecked(false);
		btn4_toggle.setChecked(false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//	点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=(Integer) v.getTag();		
		switch(id){
		case 1:
			dengToggle(0,btn1_toggle,light1);
			break;
		case 2:
			dengToggle(1,btn2_toggle,light2);
			break;
		case 3:
			dengToggle(2,btn3_toggle,light3);
			break;
		case 4:
			dengToggle(3,btn4_toggle,light4);
			break;
		case 5:
			allLightOpen();
			break;
		case 6:
			allLightOff();
			break;
		case 7:	
			openHorseLight();
			break;
		case 8:
			offHorseLight();
			break;
		default:
			break;			
		}


	}
	//开启跑马灯
	private void openHorseLight(){
		if(!flag){
			allLightOff();
			MyThread thread=new MyThread();
			this.flag=true;
			thread.start();
		}
	}
//	关闭跑马灯	
	public void offHorseLight(){
		allLightOff();
		arryState=new boolean[]{false,false,false,false};
		this.flag=false;
	}
	public void check(int i,ToggleButton btn,ImageView img){
		if(!arryState[i]){
			arryState[i]=true;
			btn.setChecked(arryState[i]);
			img.setImageResource(array[0]);
			LedClass.IoctlLed(i, 1);
		}else{
			arryState[i]=false;
			btn.setChecked(arryState[i]);
			img.setImageResource(array[1]);
			LedClass.IoctlLed(i, 0);
		}				
	}		

	//wsk 2019.5.17
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
//	改变拨码开关的状态
	public void changeState(int i,int tag){
		if(i>4)
		{
			return ;
		}
		else
		{
			if(tag==0)
			{
				LedClass.IoctlLed(i, 0);
				
			}else
			{
				LedClass.IoctlLed(i, 1);
				
			}
		}
	}
	// 定义handler接收线程回传内容
	private Handler uiHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
//				if(msg.arg1==1){
//					check(msg.arg1-1, btn1_toggle, light1);
//				}else if(msg.arg1==2){
//					check(msg.arg1-1, btn2_toggle, light2);
//				}else if(msg.arg1==3){
//					check(msg.arg1-1, btn3_toggle, light3);
//				}else if(msg.arg1==4){
//					check(msg.arg1-1, btn4_toggle, light4);
//				}
//				System.out.println("msg.arg1:" + msg.arg1);	
				computed(msg.arg1);
				break;
			}
		}

	};

	// 定义内部线程类
	class MyThread extends Thread {
		public void run() {
			int num = 1;
			while (flag) {
				try {							
					if(num==5){
						num=1;
					}
					Message msg = new Message();
					int value=DipClass.ReadValue();
					msg.what = 1;
					msg.arg1 = value;
					uiHandler.sendMessage(msg);
					num++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
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
				LedClass.Exit();
				flag=false;
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
