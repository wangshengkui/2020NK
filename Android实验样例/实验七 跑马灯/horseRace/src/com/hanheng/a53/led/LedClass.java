package com.hanheng.a53.led;

public class LedClass {
	static {
		System.loadLibrary("led-jni");
	}	
	public static native String stringFromJNI();
	public static native int Init();
	public static native int IoctlLed(int led_num, int controlcode);
	public static native int Exit();
}
