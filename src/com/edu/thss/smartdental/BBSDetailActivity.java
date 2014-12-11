package com.edu.thss.smartdental;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.BBSDetailAdapter;
import com.edu.thss.smartdental.model.BBSElement;
import com.edu.thss.smartdental.model.BBSDetail;

public class BBSDetailActivity extends Activity {
	TextView test;
	private ListView list;
	private ArrayList<BBSDetail> posts;
	private BBSDetailAdapter bbsAdapter;
	private Context context;

	 public static final int RESULT_CODE = 1; //·µ»ØÂë
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_bbs_detail);  //ºÍXMLÎÄ¼þÓ³Éä
	  //test = (TextView)findViewById(R.id.textView1); //idºÅºÍXMLÖÐ¶¨ÒåµÄID¶ÔÓ¦
	  //String data = "À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²íÁË";
	  //String data = getIntent().getExtras().getString("data");
	  //test.setText(data);
	  

		list = (ListView)findViewById(R.id.listView2);
		initPosts();
		context = this.getApplicationContext();
		bbsAdapter = new BBSDetailAdapter(posts,this.getApplicationContext());
		list.setAdapter(bbsAdapter);
	 }
	 

		private void initPosts(){
			posts = new ArrayList<BBSDetail>();
			String author = getIntent().getExtras().getString("author");
			String time = getIntent().getExtras().getString("time");
			String content = getIntent().getExtras().getString("content");
			String title = getIntent().getExtras().getString("title");
			BBSDetail i = new BBSDetail(title,content,time,author);
			posts.add(i);
		}

}
