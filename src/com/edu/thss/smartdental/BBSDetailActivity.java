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

	 public static final int RESULT_CODE = 1; //返回码
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_bbs_detail);  //和XML文件映射
	  test = (TextView)findViewById(R.id.textView1); //id号和XML中定义的ID对应
	  String data = "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦來了";
	  //String data = getIntent().getExtras().getString("data");
	  test.setText(data);
	  

		list = (ListView)findViewById(R.id.listView1);
		initPosts();
		context = this.getApplicationContext();
		bbsAdapter = new BBSDetailAdapter(posts,this.getApplicationContext());
		list.setAdapter(bbsAdapter);
	 }
	 

		private void initPosts(){
			posts = new ArrayList<BBSDetail>();
			BBSDetail i = new BBSDetail("天气不错","帖子詳情今天风速74000km/h，吹得我牙痛，眼睫毛疼，鼻子疼， 嘴巴疼。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。","2011-1-15","张三");
			posts.add(i);
			i = new BBSDetail("啦啦啦","沙发沙发沙发沙发沙发沙发沙发沙发沙发沙发","2011-1-15","李四");
			posts.add(i);
		}

}
