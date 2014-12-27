package com.edu.thss.smartdental;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.soap.Detail;

import com.edu.thss.smartdental.RemoteDB.CommentDBUtil;
import com.edu.thss.smartdental.RemoteDB.PostDBUtil;
import com.edu.thss.smartdental.adapter.BBSDetailAdapter;
import com.edu.thss.smartdental.adapter.CommentAdapter;
import com.edu.thss.smartdental.model.BBSElement;
import com.edu.thss.smartdental.model.BBSDetail;
import com.edu.thss.smartdental.model.CommentElement;

public class BBSDetailActivity extends Activity {
	private ListView list2;
	private ListView list1;
	private ArrayList<BBSDetail> posts;
	private ArrayList<CommentElement> posts1;
	private BBSDetailAdapter bbsAdapter;
	private CommentAdapter commentAdapter;
	private Context context;
	private BBSDetailActivity context1;
	private Button post_reply_button;
	private String post_id;
	private SharedPreferences preferences = null;
	private String author, time, content, title, localUser;
	


	public static final int RESULT_CODE = 1; // ·µ»ØÂë

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bbs_detail); 

		preferences = this.getSharedPreferences("setting",
				Activity.MODE_PRIVATE);

		list2 = (ListView) findViewById(R.id.listView2);
		initPosts();
		post_reply_button = (Button) findViewById(R.id.post_reply_button);
		post_reply_button.setOnClickListener(postReplyListener);
		context = this.getApplicationContext();
		context1 = this;
		bbsAdapter = new BBSDetailAdapter(posts, context, context1);
		list2.setAdapter(bbsAdapter);

		list1 = (ListView) findViewById(R.id.listView1);
		initPosts1();
		commentAdapter = new CommentAdapter(posts1, context,
				preferences.getString("username", ""));
		list1.setAdapter(commentAdapter);

		SharedPreferences pre = getSharedPreferences("setting", MODE_PRIVATE);
		localUser = pre.getString("username", "");
	}

	private void initPosts() {
		posts = new ArrayList<BBSDetail>();
		author = getIntent().getExtras().getString("author");
		time = getIntent().getExtras().getString("time");
		content = getIntent().getExtras().getString("content");
		title = getIntent().getExtras().getString("title");
		post_id = getIntent().getExtras().getString("postId");

		BBSDetail i = new BBSDetail(title, content, time, author);
		posts.add(i);
	}

	private void initPosts1() {
		posts1 = new ArrayList<CommentElement>();
		//String author = getIntent().getExtras().getString("author");
		//String time = getIntent().getExtras().getString("time");
		//String content = getIntent().getExtras().getString("content");

		//CommentElement i = new CommentElement(content, time, author);
		//posts1.add(i);
		
		CommentDBUtil db = new CommentDBUtil();
		List<HashMap<String, String>> str = db.getAllComments(Integer.parseInt(post_id));
		
		for(int i = 1; i < str.size(); i++){
			String author = str.get(i).get("commentusername");
			String time = str.get(i).get("time");
			String content = str.get(i).get("commentcontent");
			String commentId = str.get(i).get("commentid");
			CommentElement element = new CommentElement(content, time, author, Integer.parseInt(commentId));
			
			posts1.add(element);
		}
	}
	
	private OnClickListener postReplyListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(context, PostReplyActivity.class);
			intent.putExtra("postId", post_id);
			intent.putExtra("username", preferences.getString("username", ""));
			startActivity(intent);
		}
	};

	public boolean isLocalUser(){
		if(author.equals(localUser))
			return true;
		else {
			return false;
		}
	}
	
	public boolean postCollected(){
		PostDBUtil dbUtil = new PostDBUtil();
		List<HashMap<String, String>> collectedList = dbUtil.selectcollectPostid(localUser);
		
		for(int i = 1; i < collectedList.size(); i++){
			if(collectedList.get(i).get("postid").equals(post_id)){
				return true;
			}
		}
		return false;
	}
	
	public String getPostId(){
		return this.post_id;
	}

	public void collectPost(){
		PostDBUtil dbUtil = new PostDBUtil();
		dbUtil.collectpost(localUser,Integer.parseInt(post_id));
		
	}
	public void cancelCollectPost(){
		PostDBUtil dbUtil = new PostDBUtil();
		dbUtil.deletecollectpost(localUser, Integer.parseInt(post_id));
	}
	
	public boolean collected(String content){
		if(content.equals(getString(R.string.collect))){
			return false;
		}
		return true;
	}
	
	public void changeState(Button button, boolean now_state) {
		if(now_state == false){
			button.setText(getString(R.string.cancellation));
			return;
		}
		button.setText(getString(R.string.collect));
	}
	
}
