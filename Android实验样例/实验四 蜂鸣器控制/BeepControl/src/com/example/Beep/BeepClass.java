package com.example.Beep;

public class BeepClass{
	static {
		System.loadLibrary("beep-jni");//����JNI��
	}
	//��������
	public static native String stringFromJNI();//����ַ���
	//��Ӧ��JNI���jjstring Java_com_auly_control_beepClass_stringFromJNI(JNIEnv* env,jobject thiz)
	public static native int Init();
	//��ʼ����������ӦJNI���jint Java_com_auly_control_beepClass_Init(JNIEnv* env)
	public static native int IoctlBeep(int controlcode);
	// io ctrl 	�ӿڶ�ӦJNI���jint Java_com_auly_control_beepClass_IoctlBeep(JNIEnv* env, jobject thiz, jint controlcode)
	public static native int Exit();
}