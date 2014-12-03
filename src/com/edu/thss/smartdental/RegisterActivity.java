package com.edu.thss.smartdental;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import com.edu.thss.smartdental.RemoteDB.DBUtil;

public class RegisterActivity extends Activity {

	Button register_btn, login_btn;
	EditText username_edit, password_edit, repeat_password_edit;
	DBUtil db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		username_edit = (EditText)findViewById(R.id.username_edit);
		password_edit = (EditText)findViewById(R.id.password_edit);
		repeat_password_edit = (EditText)findViewById(R.id.repeat_password_edit);
		register_btn = (Button)findViewById(R.id.register_btn);
		register_btn.setOnClickListener(registerListener);
		login_btn = (Button)findViewById(R.id.login_btn);
		login_btn.setOnClickListener(loginListener);
	}
	
	private OnClickListener registerListener = new OnClickListener() {
		public void onClick(View v) {
			String username = username_edit.getText().toString(), password = password_edit.getText().toString(), repeat_password = repeat_password_edit.getText().toString();
			if (username.equals("")) {
				Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			if (password.equals("")) {
				Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			if (!repeat_password.equals(password)) {
				Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
				return;
			}
			db.insertUser(username, password, "patient", "");
			Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_LONG).show();
			finish();
		}
	};
	
	private OnClickListener loginListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}
