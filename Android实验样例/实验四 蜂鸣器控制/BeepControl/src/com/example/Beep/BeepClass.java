package com.example.Beep;

public class BeepClass{
	static {
		System.loadLibrary("beep-jni");//加载JNI库
	}
	//声明函数
	public static native String stringFromJNI();//输出字符串
	//对应于JNI里的jjstring Java_com_auly_control_beepClass_stringFromJNI(JNIEnv* env,jobject thiz)
	public static native int Init();
	//初始化函数，对应JNI里的jint Java_com_auly_control_beepClass_Init(JNIEnv* env)
	public static native int IoctlBeep(int controlcode);
	// io ctrl 	接口对应JNI里的jint Java_com_auly_control_beepClass_IoctlBeep(JNIEnv* env, jobject thiz, jint controlcode)
	public static native int Exit();
}