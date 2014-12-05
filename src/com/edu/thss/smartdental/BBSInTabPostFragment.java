package com.edu.thss.smartdental;

import java.util.Date;

import com.edu.thss.smartdental.model.BBSElement;
import com.edu.thss.smartdental.model.PostElement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BBSInTabPostFragment extends Fragment {
	private Button post_bbs_btn;
	private EditText edit_bbs_content;
	private EditText edit_bbs_title;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in_post, container, false);
		edit_bbs_title = (EditText)rootView.findViewById(R.id.edit_bbs_title);
		edit_bbs_content = (EditText)rootView.findViewById(R.id.edit_bbs_content);
		post_bbs_btn = (Button)rootView.findViewById(R.id.post_bbs_btn);
		post_bbs_btn.setOnClickListener(postBBSListener);
		return rootView;
	}
	
	private OnClickListener postBBSListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String title = edit_bbs_title.getText().toString();
			String content = edit_bbs_content.getText().toString();
			PostElement postElement = new PostElement(title, content, "岳一飞", new Date(), false);
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("发布成功")
				   .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						/*
						FragmentTransaction ft = getFragmentManager().beginTransaction();
						ft.replace(R.id.bbs_in_tab_content, new BBSInTabViewFragment());
						ft.commit();*/
					}
				});
			builder.show();
		}
	};
}
