package com.edu.thss.smartdental;

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

import javax.xml.soap.Detail;

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
	private Button post_reply_button;
	private String post_id;
	private SharedPreferences preferences = null;

	public static final int RESULT_CODE = 1; // ·µ»ØÂë

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bbs_detail); // ºÍXMLÎÄ¼þÓ³Éä
		// test = (TextView)findViewById(R.id.textView1);
		// //idºÅºÍXMLÖÐ¶¨ÒåµÄID¶ÔÓ¦
		// String data =
		// "À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²À²íÁË";
		// String data = getIntent().getExtras().getString("data");
		// test.setText(data);

		preferences = this.getSharedPreferences("setting",
				Activity.MODE_PRIVATE);

		list2 = (ListView) findViewById(R.id.listView2);
		initPosts();
		post_reply_button = (Button) findViewById(R.id.post_reply_button);
		post_reply_button.setOnClickListener(postReplyListener);
		context = this.getApplicationContext();
		bbsAdapter = new BBSDetailAdapter(posts, context);
		list2.setAdapter(bbsAdapter);

		list1 = (ListView) findViewById(R.id.listView1);
		initPosts1();
		commentAdapter = new CommentAdapter(posts1, context,
				preferences.getString("username", ""));
		list1.setAdapter(commentAdapter);
	}

	private void initPosts() {
		posts = new ArrayList<BBSDetail>();
		String author = getIntent().getExtras().getString("author");
		String time = getIntent().getExtras().getString("time");
		String content = getIntent().getExtras().getString("content");
		String title = getIntent().getExtras().getString("title");
		post_id = getIntent().getExtras().getString("postId");

		BBSDetail i = new BBSDetail(title, content, time, author);
		posts.add(i);
	}

	private void initPosts1() {
		posts1 = new ArrayList<CommentElement>();
		String author = getIntent().getExtras().getString("author");
		String time = getIntent().getExtras().getString("time");
		String content = getIntent().getExtras().getString("content");

		CommentElement i = new CommentElement(content, time, author);
		posts1.add(i);
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

}
