package com.hanheng.httpdemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.R.string;

public class HttpUtil {
	public static String sendHttpRequest(String address){
		HttpURLConnection connection=null;		
		try{
			HttpClient httpClient=new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(address);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode()==200){
//				请求相应成功
				HttpEntity entity=httpResponse.getEntity();
				String responseString=EntityUtils.toString(entity,"utf-8");
				return responseString;
			}else{
				return "";	
			}				
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}finally{
			if(connection!=null){
				connection.disconnect();
			}
		}
	}

}
