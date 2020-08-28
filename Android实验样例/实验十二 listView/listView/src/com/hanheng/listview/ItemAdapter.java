package com.hanheng.listview;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item>{
	private int resourceId;
	
	public ItemAdapter(Context context, int textViewResourceId,List<Item> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId=textViewResourceId;
	}
//	视图适配
	public View getView(int position,View convertView,ViewGroup parent){
//		获得指定的对象
		Item item=getItem(position);
		View view;
//		定义类
		ViewHolder viewHolder;
		if(convertView ==null){
//			初始化view
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
//			初始化标签类
			viewHolder=new ViewHolder();
//			初始化标签类的元素
			viewHolder.ItemImageView=(ImageView)view.findViewById(R.id.item_image);
			viewHolder.ItemName=(TextView)view.findViewById(R.id.item_text);
			//将viewHolder存储在View中
			view.setTag(viewHolder);

		}else{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
//		给ImageView添加图片，TextView添加文本
		viewHolder.ItemImageView.setImageResource(item.getImageId());
		viewHolder.ItemName.setText(item.getNameString());
		return view;
	}
//	定义视图中所要适配的标签类
	class ViewHolder {
		ImageView ItemImageView;
		TextView ItemName;
	}

}
