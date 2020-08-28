package com.hanheng.sqloperate;

import com.hanheng.db.MyDatabaseHelper;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private MyDatabaseHelper dbHelper;
	private Button addDataButton;
	private Button createDatabaseButton;
	private Button updataButton;
	private Button deleteButton;
	private Button queryButton;
	private SQLiteDatabase db;
	private ContentValues values;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper=new MyDatabaseHelper(this, "BookStore.db", null, 1);
	    createDatabaseButton=(Button)findViewById(R.id.create_database);	    
	    addDataButton=(Button)findViewById(R.id.add_data);
	    updataButton=(Button)findViewById(R.id.update_data);
	    deleteButton=(Button)findViewById(R.id.delete_data);
	    queryButton=(Button)findViewById(R.id.query_data);
	    
	    db=dbHelper.getWritableDatabase();
	    values=new ContentValues();
	    createDatabaseButton.setOnClickListener(this);
	    addDataButton.setOnClickListener(this);
	    addDataButton.setOnClickListener(this);
	    updataButton.setOnClickListener(this);
	    deleteButton.setOnClickListener(this);
	    queryButton.setOnClickListener(this);
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
		case R.id.create_database:
			dbHelper.getWritableDatabase();	
			break;
		case R.id.add_data:	
			//	存放数据
			values.put("name", "The Da Vinci Code");
			values.put("author", "Dan Brown");
			values.put("pages", 454);
			values.put("price", 16.96);
		//  插入第一条数据
			db.insert("Book", null, values); 
			values.clear();		
			values.put("name", "The Lost Symbol");
			values.put("author", "Dan Brown");
			values.put("pages", 510);
			values.put("price", 19.95);
			db.insert("Book", null, values); 
			Toast.makeText(this, "add succeeded", Toast.LENGTH_SHORT).show();
			break;
		case R.id.update_data:
			values.put("price", 10.88);
			//更新数据
			db.update("Book", values, "name=?", new String[]{"The Data Vinci Code"});
			Toast.makeText(this, "update succeeded", Toast.LENGTH_SHORT).show();
			break;
		case R.id.delete_data:
			//删除数据
			db.delete("Book", "pages>?", new String[]{"500"});
			Toast.makeText(this, "delete succeeded", Toast.LENGTH_SHORT).show();
			break;
		case R.id.query_data:
			
			Cursor cursor=db.query("Book", null,null,null,null,null, null);
			if(cursor.moveToFirst()){
				do{
					String nameString=cursor.getString(cursor.getColumnIndex("name"));
					String author=cursor.getString(cursor.getColumnIndex("author"));
					int pages=cursor.getInt(cursor.getColumnIndex("pages"));
					double price=cursor.getDouble(cursor.getColumnIndex("price"));
					Toast.makeText(this, "query succeeded"+"用户名："+nameString+"作者："+author+"页码："+pages, Toast.LENGTH_SHORT).show();
				}while(cursor.moveToNext());
			}
			cursor.close();
		default:
			break;
		}
	}

}
