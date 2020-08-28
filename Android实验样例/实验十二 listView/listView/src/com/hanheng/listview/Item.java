package com.hanheng.listview;

public class Item {
	private String nameString;
	private int imageId;
	public Item(String nameString,int imageId){
		this.nameString=nameString;
		this.imageId=imageId;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}	
}
