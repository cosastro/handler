package com.example.handler;

import com.example.handler.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.TextView;

public class Handler03 extends Activity {

	private TextView tv = null;
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0){
				Bundle bundle = msg.getData();
				String value = (String) bundle.get("title");
				
				tv.setText(value);
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler03);
		
		tv = (TextView)findViewById(R.id.tv_title);
		
		Thread t = new Thread(r);
		t.start();
		
		System.out.println(Thread.currentThread().getName());
	}
	
	Runnable r = new Runnable(){

		@Override
		public void run() {
			System.out.println("runnable " + Thread.currentThread().getName());
			try {
				Thread.sleep(3000);
				
				Message msg = new Message();
				msg.what = 0;
				Bundle bundle = new Bundle();
				bundle.putString("title", "ttttt");
				msg.setData(bundle);
				handler.sendMessage(msg);
				//Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	};
}
