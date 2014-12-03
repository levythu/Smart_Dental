package com.edu.thss.smartdental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

	Button login_btn, register_btn;
	
	protected void onCreate(Bundle savedInstanceState) {
		if(true){

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
            .detectDiskReads()  
            .detectDiskWrites()  
            .detectNetwork()  
            .penaltyLog()  
            .build());
			}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		login_btn = (Button)findViewById(R.id.login_btn);
		login_btn.setOnClickListener(loginListener);
		register_btn = (Button)findViewById(R.id.register_btn);
		register_btn.setOnClickListener(registerListener);
	}
	
	private OnClickListener loginListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};
	private OnClickListener registerListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
		}
	};
}