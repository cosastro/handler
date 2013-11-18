/*
 * 
 * handler不在主线程中运行
 */

package com.example.handler;

import com.example.handler.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class Handler04 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler04);
		
		System.out.println("Main-->" + Thread.currentThread().getId());
		System.out.println("onCreate");
		
		HandlerThread thread = new HandlerThread("handler_thread");
		thread.start();
		MyHandler myHandler = new MyHandler(thread.getLooper()); //handler在一个新的线程中处理消息而不再主线程

		//另一种发送消息的方法
		Message msg = myHandler.obtainMessage();
		Bundle bundle = new Bundle();
		bundle.putInt("age", 26);
		bundle.putString("name", "gcloo");
		msg.setData(bundle);
		msg.obj = "abc";
		msg.sendToTarget();
	}
	
	@SuppressLint("HandlerLeak")
	class MyHandler extends Handler{
		public MyHandler(){
			
		}
		
		@SuppressLint("HandlerLeak")
		public MyHandler(Looper looper){
			super(looper);
		}
		
		@Override
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			int age = bundle.getInt("age");
			String name = bundle.getString("name");
			String s = (String)msg.obj;
			System.out.println("Handler-->" + s + " " + name + " " + age);
			System.out.println("Handler-->" + Thread.currentThread().getId());
			System.out.println("Handler-->" + "handleMessage");
		}
		
	} 

}
