package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Handler01 extends Activity {
	
	private Button btn_start = null;
	private Button btn_end = null;
	Handler handler = new Handler();
	Runnable updateThread = new Runnable(){

		@Override
		public void run() {
			//运行在主线程中
			System.out.println(Thread.currentThread().getName());
			handler.postDelayed(updateThread, 3000);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler01);
		
		btn_start = (Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				System.out.println(Thread.currentThread().getName());
				handler.post(updateThread);
			}
			
		});
		btn_end = (Button)findViewById(R.id.btn_end);
		btn_end.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				handler.removeCallbacks(updateThread);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handler, menu);
		return true;
	}

}
