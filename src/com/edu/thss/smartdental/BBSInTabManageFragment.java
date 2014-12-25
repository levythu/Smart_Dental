package com.edu.thss.smartdental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.edu.thss.smartdental.RemoteDB.UserDBUtil;
import android.content.SharedPreferences;

public class BBSInTabManageFragment extends Fragment {
	
	Button confirm_btn;
	EditText circle_password_edit;
	UserDBUtil db = new UserDBUtil();
	SharedPreferences preferences = null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in_manage, container, false);
		preferences = getActivity().getSharedPreferences("setting", android.content.Context.MODE_PRIVATE);
		circle_password_edit = (EditText)rootView.findViewById(R.id.circle_password_edit);
		circle_password_edit.setText(db.getCirclePassword(preferences.getString("username", "")));
		confirm_btn = (Button)rootView.findViewById(R.id.confirm_btn);
		confirm_btn.setOnClickListener(confirmlistener);
		return rootView;
	}

	private OnClickListener confirmlistener = new OnClickListener() {
		public void onClick(View v) {
			String t = db.setcirclePassword(circle_password_edit.getText().toString(), preferences.getString("username", ""));
			if (t.equals("true"))
				Toast.makeText(getActivity(), "密码修改成功", Toast.LENGTH_LONG).show();
			else
				if (t.equals("fail to connect to Database"))
					Toast.makeText(getActivity(), "连不上服务器", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getActivity(), "未知错误", Toast.LENGTH_LONG).show();
		}
	};
}
