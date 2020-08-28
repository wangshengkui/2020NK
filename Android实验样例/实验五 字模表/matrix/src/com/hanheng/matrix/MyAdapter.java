package com.hanheng.matrix;

import java.util.List;
import com.ourselec.matrix.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

//适配器
public class MyAdapter extends ArrayAdapter<FillContent>{

	private int resourceId;
//	构造方法
	public MyAdapter(Context context, int textViewResourceId,
			List<FillContent> objects) {
		super(context, textViewResourceId, objects);
		resourceId=textViewResourceId;
	}
	
//	适配方法
	public View getView(int position,View contentView,ViewGroup parent){
		
		FillContent content=getItem(position);
		View view;
		ViewHolder viewHolder;
		
		if(contentView==null){
			
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.fillImageView=(ImageView)view.findViewById(R.id.image);
			view.setTag(viewHolder);
		}else{
			view=contentView;
			viewHolder=(ViewHolder)view.getTag();
			
		}
		viewHolder.fillImageView.setImageResource(content.getImageId());
		return view;

	}
	
//	定义要适配的类存放图片元素
	class ViewHolder{
		ImageView fillImageView;
	}
}
