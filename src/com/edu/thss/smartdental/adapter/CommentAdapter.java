/*
 * 作者：王宇炜
 * 日期：2014年12月11日
 */
package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.model.CommentElement;
import com.edu.thss.smartdental.R;

import android.content.Context;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
	private ArrayList<CommentElement> list;
	private Context context;
	private buttonViewHolder holder;
	private String username;
	private class buttonViewHolder {
		Button delete;
	}
	public CommentAdapter(ArrayList<CommentElement> list, Context context, String username) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
		this.username = username;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int postion) {
		// TODO Auto-generated method stub
		return postion;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.comment_detail_item, null);
		}
		CommentElement comment = list.get(position);
		
		TextView content = (TextView)convertView.findViewById(R.id.comment_item_content);
		TextView time = (TextView)convertView.findViewById(R.id.comment_item_time);
		TextView author = (TextView)convertView.findViewById(R.id.comment_item_author);
		
		content.setText(comment.content);
		time.setText(comment.time);
		author.setText(comment.author);
		
		holder = new buttonViewHolder();
		holder.delete = (Button)convertView.findViewById(R.id.comment_item_delete);
		if (author.equals(this.username)) {
			holder.delete.setVisibility(View.VISIBLE);
		}
		else {
			holder.delete.setVisibility(View.INVISIBLE);
		}
		holder.delete.setOnClickListener(new ButtonListener(position));
		return convertView;
	}

	class ButtonListener implements OnClickListener {
		private int itemPosition;

		public ButtonListener(int position) {
			this.itemPosition = position;
		}
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			int viewId = view.getId();
			if (viewId == holder.delete.getId()) {
				list.remove(itemPosition);
				notifyDataSetChanged();
			}
		}
		
	}
}
