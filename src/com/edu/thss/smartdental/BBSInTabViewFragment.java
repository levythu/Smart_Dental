package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import com.edu.thss.smartdental.adapter.BBSListAdapter;
import com.edu.thss.smartdental.adapter.ImgListAdapter;
import com.edu.thss.smartdental.model.BBSElement;
import com.edu.thss.smartdental.model.ImageElement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class BBSInTabViewFragment extends Fragment {
	private BBSListAdapter bbsAdapter;
	private EditText editText;
	private ListView list;
	private ArrayList<BBSElement> posts=new ArrayList<BBSElement>();
	private Context context;
	private int UserId;
	
	private Spinner view_spinner;
	private ArrayAdapter tagAdapter; 
	
	public BBSInTabViewFragment(int id) {
		UserId = id;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in_view, container, false);
		
		
		view_spinner = (Spinner)rootView.findViewById(R.id.bbs_view_spinner);
		tagAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.view_tag_names, android.R.layout.simple_spinner_item);
		tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		view_spinner.setAdapter(tagAdapter);
		view_spinner.setVisibility(View.VISIBLE);
		view_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view,
		            int position, long id) {
		        String str=parent.getItemAtPosition(position).toString();
		        initPosts(str);
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // TODO Auto-generated method stub
		    }
		});
		
		
		editText = (EditText)rootView.findViewById(R.id.bbs_searchbox);
		editText.addTextChangedListener(filterTextWatcher);
		list = (ListView)rootView.findViewById(R.id.bbs_list);
		//initPosts("全部");
		context = this.getActivity().getApplicationContext();
		bbsAdapter = new BBSListAdapter(posts,this.getActivity().getApplicationContext());
		list.setAdapter(bbsAdapter);
		
		      
		list.setOnItemClickListener(new OnPostItemClickListener(context));
				

	
		view_spinner.setSelection(5);
		return rootView;
	}
	
	private class OnPostItemClickListener implements OnItemClickListener{
		Context context;
		public OnPostItemClickListener(Context c){
			context = c;
		}
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                 long arg3) {
            // if(list.get(position).equals("LinearLayout"))
                 Intent intent = new Intent();
                 intent.setClass(context,BBSDetailActivity.class);
                 startActivity(intent);       
                 
         }
	};
	
	private TextWatcher filterTextWatcher = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			bbsAdapter.getFilter().filter(s);	
		}
	};

	private void initPosts(String tag){
		DBUtil db = new DBUtil();
		List<HashMap<String, String>> PostList  = db.getAllPostInfo(UserId);
		posts.clear();
		BBSElement post;
		for (int i = 1; i < PostList.size(); i++){
			if (tag == "全部" || tag.equals(PostList.get(i).get("tag"))){
				String s1=PostList.get(i).get("postname");
				String s2=PostList.get(i).get("postcontent");
				String s3 = PostList.get(i).get("time");
				post = new BBSElement(s1,s2,s3,"zhangsan",true,true);
				posts.add(post);
			}
		}
		/*
		i = new BBSElement("天气不错","今天风速74km/h，吹得我牙痛","2011-1-15","张三",true,true);
		posts.add(i);
		i = new BBSElement("啦啦啦","我喜欢这个医生","2011-1-15","李四",true,false);
		posts.add(i);
		i = new BBSElement("花花花","拔牙愉快啦啦","2012-1-25","二娃",false,true);
		posts.add(i);
		i = new BBSElement("呵呵呵","我牙齿很白","2013-1-25","奥千",false,false);
		posts.add(i);
		i = new BBSElement("呵呵呵","我牙齿很白","2013-1-25","奥千",false,false);
		posts.add(i);
		i = new BBSElement("呵呵呵","我牙齿很白","2013-1-25","奥千",false,false);
		posts.add(i);
		*/
	}
	
}
