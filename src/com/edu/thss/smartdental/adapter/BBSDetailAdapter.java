package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.R;
import com.edu.thss.smartdental.model.BBSDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class BBSDetailAdapter extends BaseAdapter implements Filterable{
	private class buttonViewHolder{
		
		Button delete; //ɾ��
	}
	private ArrayList<BBSDetail> list;
	private Context context;
	private BBSFilter filter;
	private buttonViewHolder holder;
	
	public BBSDetailAdapter(ArrayList<BBSDetail> list, Context context){
		this.list = list;
		this.context = context;
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
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.bbs_detail_item, null);
		}
		BBSDetail post = list.get(position);
		//add!!!!
		TextView title =(TextView)convertView.findViewById(R.id.bbs_detail_item_title);
		TextView content = (TextView)convertView.findViewById(R.id.bbs_detail_item_content);
		TextView time = (TextView)convertView.findViewById(R.id.bbs_detail_item_time);
		TextView author = (TextView)convertView.findViewById(R.id.bbs_detail_item_author);
		
		title.setText(post.title);
		content.setText(post.content);
		time.setText(post.time);
		author.setText(post.author);
		
		holder = new buttonViewHolder();
		//holder.read = (Button)convertView.findViewById(R.id.bbs_list_item_read);
		holder.delete = (Button)convertView.findViewById(R.id.bbs_detail_item_delete);
		//holder.hide = (Button)convertView.findViewById(R.id.bbs_list_item_hide);
		
		//holder.hide.setText(post.isHidden?"������":"����");
		//holder.read.setText(post.isRead?"�Ѷ�":"δ��");
		
		//holder.read.setOnClickListener(new ButtonListner(position));
		holder.delete.setOnClickListener(new ButtonListner(position));
		//holder.hide.setOnClickListener(new ButtonListner(position));
		return convertView;
	}

	@Override
	public Filter getFilter() {
		if(filter == null){
			filter = new BBSFilter(list);
		}
		return filter;
	}
    public class BBSFilter extends Filter{
    	private ArrayList<BBSDetail> original;
    	public BBSFilter(ArrayList<BBSDetail> list){
    		this.original = list;
    	}

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			
			FilterResults results = new FilterResults();
			if(constraint == null || constraint.length()==0){ //û�й�������
				results.values = this.original;
				results.count = this.original.size();
				
			}
			else{
				
				ArrayList<BBSDetail> mList = new ArrayList<BBSDetail>();
				for(BBSDetail image: original){
					if(image.title.toUpperCase().contains(constraint.toString().toUpperCase())
					   ||image.content.toUpperCase().contains(constraint.toString().toUpperCase())
					   ||image.time.contains(constraint)){
						
						mList.add(image);
					}
				}
				results.values = mList;
				results.count = mList.size();
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			list = (ArrayList<BBSDetail>)results.values;
			notifyDataSetChanged();
			
		}
    }
    class ButtonListner implements OnClickListener{
		private int itemPosition;
		public ButtonListner(int pos){
			this.itemPosition = pos;
		}

		@Override
		public void onClick(View v) {
			int vid = v.getId();
			if(vid == holder.delete.getId()){
			 //ɾ��
				list.remove(itemPosition);
				notifyDataSetChanged();
			}
			
		}
    }

}
