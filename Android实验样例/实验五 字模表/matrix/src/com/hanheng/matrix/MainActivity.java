package com.hanheng.matrix;

import java.util.ArrayList;
import java.util.List;

import com.hanheng.a53.dotarray.DotArrayClass;
import com.hanheng.a53.dotarray.FontClass;
import com.ourselec.matrix.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends Activity implements OnClickListener{
	private GridView gView;
	private List<FillContent> data_List=new ArrayList<FillContent>();
	private EditText text;
	private Button submitButton;
	private Button testButton;
	private int[] icon={R.drawable.blue,R.drawable.orange};
	private MyAdapter adapter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initData(icon);
//		初始化适配器
		adapter=new MyAdapter(MainActivity.this, R.layout.item,data_List);
		gView.setAdapter(adapter);
		submitButton.setOnClickListener(this);
		testButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	初始化标签组件
	private void init(){
		this.text=(EditText)findViewById(R.id.editText1);
		gView=(GridView)findViewById(R.id.gview1);
		submitButton=(Button)findViewById(R.id.button1);
		testButton=(Button)findViewById(R.id.button2);
		FontClass.getInstance();
	}
	
//	初始化数据
	public void initData(int[] icon){
		for(int i=0;i<icon.length;i++){
			FillContent con=new FillContent(icon[i]);
			data_List.add(con);
		}
	}	
	@Override
	public void onClick(View arg0) {
		int key=arg0.getId();
		switch (key) {
		case R.id.button1:			
			String str =text.getText().toString();
			if(str.length()!=0){
				byte[][] data = FontClass.getInstance().setContent(str,this.getAssets());
				icon = getIcon(data[0]);
//				清空数据
				adapter.clear();
//				初始化数据
				initData(icon);
//				通知数据改变
				adapter.notifyDataSetChanged();
			}
			break;
		case R.id.button2:			
			FontClass.getInstance().startTest(this.getAssets());
			break;
		default:
			break;
		}		
	}

//	获得解析后的数组
	public int[] getIcon(byte[] data) {
		int[] arr = new int[256];
		int n = 0;
		for(int k=0; k<16; k++){
	        for(int j=0; j<2; j++){
	        	char[] cs = Integer.toBinaryString(data[k*2+j]&0xFF).toCharArray();
	            for(int i=0; i<8; i++){
	            	int len = cs.length;
	            	if(len+i<8){
	            		 arr[n] = R.drawable.blue;   	           		
	            	}else{
	            		if(cs[len+i-8]=='1'){
	            			arr[n] = R.drawable.orange;                        			
	            		}else{
	            			arr[n] = R.drawable.blue;  	            			
	            		}	            		
	            	}
	            	n++;
	            }	
	        }        
	    }
		return arr;
	}
	
	@SuppressWarnings("deprecation")
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event){  
		if (keyCode == KeyEvent.KEYCODE_BACK ){  
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
				DotArrayClass.Exit();
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
