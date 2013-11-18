package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class Handler02 extends Activity{

	private ProgressBar bar = null;
	private Button btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler02);
		
		System.out.println("Main-->" + Thread.currentThread().getId());
		
		bar = (ProgressBar)findViewById(R.id.pb);
		btn = (Button)findViewById(R.id.btn);
		
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				bar.setVisibility(View.VISIBLE);
				handler.post(updateThread);
			}
			
		});
	}
	
	Handler handler = new Handler(){
		
		@Override
		public void handleMessage(Message msg){
			bar.setProgress(msg.arg1);
			handler.post(updateThread);
		}
	};
	
	//线程类，采用匿名内部类的方式，run方法并没有开启一个新线程，只有调用线程的start方法才行
	Runnable updateThread = new Runnable(){
		int i = 0;
		
		@Override
		public void run() {
			System.out.println("begin to update..");
			System.out.println("Handler-->" + Thread.currentThread().getId());
			
			i += 10;
			Message msg = handler.obtainMessage();
			msg.arg1 = i;
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			handler.sendMessage(msg);
			if(i == 100){
				handler.removeCallbacks(updateThread);
			}
		}
		
	};
}
