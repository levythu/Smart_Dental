package com.edu.thss.smartdental;

import com.edu.thss.smartdental.RemoteDB.DBUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity {

	Button confirm_btn, cancel_btn;
	EditText old_password_edit, new_password_edit, repeat_new_password_edit;
	DBUtil db = new DBUtil();
	SharedPreferences preferences = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		preferences = getSharedPreferences("setting", MODE_PRIVATE);
		old_password_edit = (EditText)findViewById(R.id.old_password_edit);
		new_password_edit = (EditText)findViewById(R.id.new_password_edit);
		repeat_new_password_edit = (EditText)findViewById(R.id.repeat_new_password_edit);
		confirm_btn = (Button)findViewById(R.id.confirm_btn);
		confirm_btn.setOnClickListener(confirmListener);
		cancel_btn = (Button)findViewById(R.id.cancel_btn);
		cancel_btn.setOnClickListener(cancelListener);
	}
	
	private OnClickListener confirmListener = new OnClickListener() {
		public void onClick(View v) {
			String old_password = old_password_edit.getText().toString(), new_password = new_password_edit.getText().toString(), repeat_new_password = repeat_new_password_edit.getText().toString();
			if (!new_password.equals(repeat_new_password)) {
				Toast.makeText(ChangePasswordActivity.this, "两次新密码输入不一致", Toast.LENGTH_LONG).show();
				return;
			}
			String t = db.setuserPassword(old_password, new_password, preferences.getString("username", ""));
			Toast.makeText(ChangePasswordActivity.this, t, Toast.LENGTH_LONG).show();
		}
	};
	
	private OnClickListener cancelListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}
