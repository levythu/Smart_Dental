/*
 * author: Chen Minghai
 */
package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.readystatesoftware.viewbadger.BadgeView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.app.FragmentManager;

import com.edu.thss.smartdental.RemoteDB.NewsDBUtil;
import com.edu.thss.smartdental.RemoteDB.UserDBUtil;
import com.edu.thss.smartdental.model.NewsElement;

public class BBSInFragment extends Fragment {
	
	private RadioGroup radioGroup;
	private FragmentManager fragmentManager;
	private int UserId;
	private String userName;
	private BadgeView badge;
	private ArrayList<NewsElement> newsList;
	UserDBUtil db = new UserDBUtil();
	
	public BBSInFragment(int id) {
		UserId = id;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in, container,false);
		
		fragmentManager = getFragmentManager();
		radioGroup = (RadioGroup)rootView.findViewById(R.id.bbs_in_tab);
		View button = rootView.findViewById(R.id.remind_button);
		View invisible_button = rootView.findViewById(R.id.manage_invisible_button);
		this.userName = getActivity().getSharedPreferences("setting", Activity.MODE_PRIVATE).getString("username", "");
		List<HashMap<String, String>> listFromDB = getNewsList();
		initNewsListToShow(listFromDB);
		
		setBadgeView(button, this.newsList.size());
		radioGroup.check(R.id.bbs_in_tab_view);
		changeFragment(0);
		RadioButton manageTag = (RadioButton)rootView.findViewById(R.id.bbs_in_tab_manage);
		if(!db.getuseridentity(this.userName).equals("doctor")){
			manageTag.setVisibility(View.GONE);
			invisible_button.setVisibility(View.GONE);
		}
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch(checkedId){
				case R.id.bbs_in_tab_view: 
					changeFragment(0); 
					break;
				case R.id.bbs_in_tab_group:
					changeFragment(1);
					break;
				case R.id.bbs_in_tab_news:
					changeFragment(2);
					break;
				case R.id.bbs_in_tab_post:
					changeFragment(3);
					break;
				case R.id.bbs_in_tab_manage:
					changeFragment(4);
					break;
				//TODO ADD OTHER FRAGMENT
				}
               
			}} );
		
		return rootView;
	}
	
	private void setBadgeView(View view, int num) {
		badge = new BadgeView(getActivity(), view);
		badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		badge.setTextColor(Color.WHITE);
		badge.setBadgeBackgroundColor(Color.RED);
		badge.setTextSize(12);
		badge.setBadgeMargin(5);
		if (num > 0) {
			if (num > 99) {
				badge.setText("...");
			} else {
				badge.setText(Integer.valueOf(num));
			}
		} else {
			badge.hide();
		}
	}
	
	private List<HashMap<String, String>> getNewsList() {
		NewsDBUtil newsDB = new NewsDBUtil();
		List<HashMap<String, String>> newsListFromDB = newsDB.selectAllUnreadNewsByUsername(this.userName);
		return newsListFromDB;
	}
	
	private void initNewsListToShow(List<HashMap<String, String>> listFromDB) {
		this.newsList = new ArrayList<NewsElement>();
		Iterator<HashMap<String, String>> iteratorOfDB = listFromDB.iterator();
		Iterator<NewsElement> iteratorOfLocal;
		boolean is_existed;
		NewsElement element;
		HashMap<String, String> elementFromDB;
		
		iteratorOfDB.next();
		while (iteratorOfDB.hasNext()) {
			elementFromDB = iteratorOfDB.next();
			is_existed = false;
			iteratorOfLocal = this.newsList.iterator();
			while (iteratorOfLocal.hasNext()) {
				element = iteratorOfLocal.next();
				if (element.getPostID().compareTo(elementFromDB.get("postID")) == 0) {
					is_existed = true;
					element.addUserToList(elementFromDB.get("username"));
					element.setTime(elementFromDB.get("time"));
					break;
				}
			}
			if (!is_existed) {
				element = new NewsElement();
				element.addUserToList(elementFromDB.get("username"));
				element.setPostID(elementFromDB.get("postID"));
				element.setPostTitle(elementFromDB.get("postname"));
				element.setTime(elementFromDB.get("time"));
				element.setType(elementFromDB.get("newstype"));
				this.newsList.add(element);
			}
		}
		
	}
	
	private void changeFragment(int index){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment tempfragment = null;
		switch(index){
		case 0:
			tempfragment = new BBSInTabViewFragment(this.UserId);
			break;
		case 1:
			tempfragment = new BBSFragment();
			break;
		case 2:
			badge.hide();
			tempfragment = new BBSInTabNewsFragment(newsList);
			break;
		case 3:
			//tempfragment = new BBSInTabPostFragment();
			Intent intent = new Intent();
			intent.setClass(getActivity().getApplicationContext(), PostBBSActivity.class);
//			intent.putExtra("postId", 11);
//			intent.putExtra("username", "yy");
			startActivity(intent);
			radioGroup.check(R.id.bbs_in_tab_view);
			break;
		case 4:
			tempfragment = new BBSInTabManageFragment();
			break;
		}
		if (tempfragment != null && index != 4) {
			transaction.replace(R.id.bbs_in_tab_content, tempfragment);
			transaction.commit();
        }
	}

}