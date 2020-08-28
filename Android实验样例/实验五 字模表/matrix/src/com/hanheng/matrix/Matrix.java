package com.hanheng.matrix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import android.content.res.AssetManager;
//字模类
public class Matrix {
	private static Matrix matrix = new Matrix();
	private Matrix(){};
	public static Matrix getInstance(){
		if(matrix==null){
			matrix = new Matrix();
		}
		return matrix;
	}	
//	获取文字的字节数组
	public byte[] getByteArray(String word,AssetManager am){
		try {		
			InputStream fis = am.open("hzk16s");
			byte[] wb = word.getBytes("GB2312");
			int offset = (94*(wb[0]+256-161)+wb[1]+256-161)*32;
			fis.read(new byte[offset]);
			byte[] buffer = new byte[32];
			fis.read(buffer);
			fis.close();			
			for(int k=0; k<16; k++){
		        for(int j=0; j<2; j++){
		        	char[] cs = Integer.toBinaryString(buffer[k*2+j]&0xFF).toCharArray();
		            for(int i=0; i<8; i++){
		            	int len = cs.length;
		            	if(len+i<8){
		            		System.out.print("○ ");
		            	}else{
		            		System.out.print(cs[len+i-8]=='1'?"● ":"○ ");
		            	}
		            }	
		        }
		        System.out.println();		      
		    }
			return buffer;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
