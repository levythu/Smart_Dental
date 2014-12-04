package com.edu.thss.smartdental;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddOneFragment extends Fragment {
	
	Button add_one_btn;
	EditText username_edit, password_edit, repeat_password_edit;
	DBUtil db = new DBUtil();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_one, container, false);
		username_edit = (EditText)rootView.findViewById(R.id.username_edit);
		password_edit = (EditText)rootView.findViewById(R.id.password_edit);
		repeat_password_edit = (EditText)rootView.findViewById(R.id.repeat_password_edit);
		add_one_btn = (Button)rootView.findViewById(R.id.add_btn);
		add_one_btn.setOnClickListener(addListener);
		return rootView;
	}

	private OnClickListener addListener = new OnClickListener() {
		public void onClick(View v) {
			String username = username_edit.getText().toString(), password = password_edit.getText().toString(), repeat_password = repeat_password_edit.getText().toString();
			if (username.equals("")) {
				Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			if (password.equals("")) {
				Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			if (!repeat_password.equals(password)) {
				Toast.makeText(getActivity(), "两次密码输入不一致", Toast.LENGTH_LONG).show();
				return;
			}
			if (username.equals(getString(R.string.admin_username))) {
				Toast.makeText(getActivity(), "用户名已存在", Toast.LENGTH_LONG).show();
				return;
			}
			db.insertUser(username, password, "doctor", "");
			Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_LONG).show();
		}
	};
}
