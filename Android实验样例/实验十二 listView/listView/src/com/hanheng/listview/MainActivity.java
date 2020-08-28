package com.hanheng.listview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private List<Item> itemList=new ArrayList<Item>();
	private String[] textName={"美食","酒店","生活服务","礼品","电影","关注","摄影"};
	private int[] imageId={R.drawable.ic_food,R.drawable.ic_hotel,R.drawable.ic_life,R.drawable.ic_luck,
			R.drawable.ic_movie,R.drawable.ic_newest,R.drawable.ic_photo};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		初始化itemList
		initItems();
//		初始化适配器
		ItemAdapter adapter=new ItemAdapter(MainActivity.this, R.layout.item, itemList);
//		初始化ListView
		ListView listView=(ListView) findViewById(R.id.list_view);
//		添加适配
		listView.setAdapter(adapter);
//listView添加点击事件
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Item item=itemList.get(position);
				Toast.makeText(MainActivity.this, item.getNameString(),Toast.LENGTH_LONG).show();
			}
		});    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	初始化itemList方法
	public void initItems(){
		for(int i=0;i<textName.length;i++){
			Item item=new Item(textName[i],imageId[i]);
			this.itemList.add(item);
		}
	}

}
