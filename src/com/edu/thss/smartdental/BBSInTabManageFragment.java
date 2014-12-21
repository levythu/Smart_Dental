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

public class BBSInTabManageFragment extends Fragment {
	
	Button confirm_btn;
	EditText circle_password_edit;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in_manage, container, false);
		circle_password_edit = (EditText)rootView.findViewById(R.id.circle_password_edit);
		confirm_btn = (Button)rootView.findViewById(R.id.confirm_btn);
		confirm_btn.setOnClickListener(confirmlistener);
		return rootView;
	}

	private OnClickListener confirmlistener = new OnClickListener() {
		public void onClick(View v) {
			
		}
	};
}
