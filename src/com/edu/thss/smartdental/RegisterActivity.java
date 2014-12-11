/*
 * Author: Zhang Kai
*/

package com.edu.thss.smartdental;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	DBUtil db = new DBUtil();
	SharedPreferences preferences = null;
	Editor editor = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		preferences = getSharedPreferences("setting", MODE_PRIVATE);
		editor = preferences.edit();
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
			if (username.equals(getString(R.string.admin_username))) {
				Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
				return;
			}
			String t = db.insertUser(username, password, "patient"); 
			if (t.equals("true")) {
				t = db.login(username, password);
				editor.putInt("userid", Integer.parseInt(t));
				editor.putString("username", username);
				editor.putString("password", password);
				editor.commit();
				Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
			}
			else {
				if (t.equals("username exists"))
					Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
				else
					if (t.equals("fail to connect to Database"))
						Toast.makeText(RegisterActivity.this, "连不上服务器", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_LONG).show();
				return;
			}
			Intent intent = new Intent();
			intent.setClass(RegisterActivity.this, JoinCircleActivity.class);
			startActivity(intent);
			finish();
		}
	};
	
	private OnClickListener loginListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(RegisterActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
	};
}
