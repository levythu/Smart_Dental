package com.edu.thss.smartdental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.edu.thss.smartdental.RemoteDB.DBUtil;

public class LoginActivity extends Activity {

	Button login_btn, register_btn;
	EditText username, password;
	DBUtil db = new DBUtil();
	
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
		username = (EditText)findViewById(R.id.username_edit);
		password = (EditText)findViewById(R.id.password_edit);
		login_btn = (Button)findViewById(R.id.login_btn);
		login_btn.setOnClickListener(loginListener);
		register_btn = (Button)findViewById(R.id.register_btn);
		register_btn.setOnClickListener(registerListener);
	}
	
	private OnClickListener loginListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			if (username.getText().toString().equals(getString(R.string.admin_username)))
				if (password.getText().toString().equals(getString(R.string.admin_password)))
					intent.setClass(LoginActivity.this, AdminActivity.class);
				else {
					Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_LONG).show();
					return;
				}
			else
				if (db.login(username.getText().toString(), password.getText().toString()).equals("true"))
					intent.setClass(LoginActivity.this, MainActivity.class);
				else {
					Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
					intent.setClass(LoginActivity.this, MainActivity.class);
					//return;
				}
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