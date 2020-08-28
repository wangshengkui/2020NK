package com.hanheng.a53.dip;

public class DipClass {
	static {
		System.loadLibrary("dip-jni");
	}	
	public static native String stringFromJNI();
	public static native int Init();
	public static native int ReadValue();
	public static native int Exit();
}
