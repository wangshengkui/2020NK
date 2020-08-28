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
      
        /*��ʼ��*/
        //mbeepClass = new BEEPCLASS();//������
        //mbeepClass.Init();
        BeepClass.Init();
        
       final ImageView image=(ImageView)findViewById(R.id.image_buzzer_f);
       image.setImageResource(R.drawable.buzzer_f);
       
        //�򿪰�ť�¼�����
        Button Btn_on = (Button)findViewById(R.id.beep_on);
        Btn_on.setOnClickListener
        (
        	new View.OnClickListener(){
        		public void onClick(View v){
        			//mbeepClass.IOCTLBEEP(BEEP_ON);	//����JNI��IOCTLBEEP����
        			BeepClass.IoctlRelay(BEEP_ON);	//����JNI��IOCTLBEEP����
        			image.setImageResource(R.drawable.buzzer_t);
        		}
        	}
        );
        
        //�رհ�ť�¼�����
        Button Btn_off = (Button)findViewById(R.id.beep_off);
        Btn_off.setOnClickListener
        (
        	new View.OnClickListener(){
				public void onClick(View v){
        			//mbeepClass.IOCTLBEEP(BEEP_OFF);
					BeepClass.IoctlRelay(BEEP_OFF);//����JNI��IOCTLBEEP����
					
        			image.setImageResource(R.drawable.buzzer_f);
        		}
        	}
        );
        
        //�˳���ť�¼�����
        Button Btn_exit = (Button)findViewById(R.id.exit);
        Btn_exit.setOnClickListener
        (
        	new View.OnClickListener(){
        		public void onClick(View v){
        			MainActivity.this.exitDialog();  //�����˳��ķ���
        		}
        	}
        		
        );
    }
	
	//�˳���������
	 private void exitDialog(){  //�˳�����ķ���
		 Dialog dialog = new AlertDialog.Builder(MainActivity.this)
		 .setTitle("�����˳���")  // ��������
		 .setMessage("��ȷ��Ҫ�˳���")    //��ʾ�Ի��������
		 .setIcon(R.drawable.ic_launcher) //����LOGO
		 .setNegativeButton("ȡ��", new DialogInterface.OnClickListener(){
	           public void onClick(DialogInterface dialog, int which){
	        	   }
	           }
		 )
		 .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int which) {
	        	   //mbeepClass.Exit();
	        	   BeepClass.Exit(); //�˳�
	        	   MainActivity.this.finish(); //��������
	        	   //System.exit(0);
	        	   }
	           }
		 )
		 .create();  //�����Ի���
		 dialog.show();  //��ʾ�Ի���
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
