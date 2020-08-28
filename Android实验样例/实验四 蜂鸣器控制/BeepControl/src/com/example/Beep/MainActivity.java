package com.example.Beep;

import com.hanheng.a53.beep.BeepClass;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	public static final int BEEP_ON = 0;
    public static final int BEEP_OFF = 1;
    //BEEPCLASS mbeepClass;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        /*初始化*/
        //mbeepClass = new BEEPCLASS();//声明类
        //mbeepClass.Init();
        BeepClass.Init();
        
       final ImageView image=(ImageView)findViewById(R.id.image_buzzer_f);
       image.setImageResource(R.drawable.buzzer_f);
       
        //打开按钮事件处理
        Button Btn_on = (Button)findViewById(R.id.beep_on);
        Btn_on.setOnClickListener
        (
        	new View.OnClickListener(){
        		public void onClick(View v){
        			//mbeepClass.IOCTLBEEP(BEEP_ON);	//调用JNI的IOCTLBEEP函数
        			BeepClass.IoctlRelay(BEEP_ON);	//调用JNI的IOCTLBEEP函数
        			image.setImageResource(R.drawable.buzzer_t);
        		}
        	}
        );
        
        //关闭按钮事件处理
        Button Btn_off = (Button)findViewById(R.id.beep_off);
        Btn_off.setOnClickListener
        (
        	new View.OnClickListener(){
				public void onClick(View v){
        			//mbeepClass.IOCTLBEEP(BEEP_OFF);
					BeepClass.IoctlRelay(BEEP_OFF);//调用JNI的IOCTLBEEP函数
					
        			image.setImageResource(R.drawable.buzzer_f);
        		}
        	}
        );
        
        //退出按钮事件处理
        Button Btn_exit = (Button)findViewById(R.id.exit);
        Btn_exit.setOnClickListener
        (
        	new View.OnClickListener(){
        		public void onClick(View v){
        			MainActivity.this.exitDialog();  //调用退出的方法
        		}
        	}
        		
        );
    }
	
	//退出方法函数
	 private void exitDialog(){  //退出程序的方法
		 Dialog dialog = new AlertDialog.Builder(MainActivity.this)
		 .setTitle("程序退出？")  // 创建标题
		 .setMessage("您确定要退出吗？")    //表示对话框的内容
		 .setIcon(R.drawable.ic_launcher) //设置LOGO
		 .setNegativeButton("取消", new DialogInterface.OnClickListener(){
	           public void onClick(DialogInterface dialog, int which){
	        	   }
	           }
		 )
		 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int which) {
	        	   //mbeepClass.Exit();
	        	   BeepClass.Exit(); //退出
	        	   MainActivity.this.finish(); //操作结束
	        	   //System.exit(0);
	        	   }
	           }
		 )
		 .create();  //创建对话框
		 dialog.show();  //显示对话框
	  }

	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
