package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import com.edu.thss.smartdental.adapter.JoinCircleListAdapter;
import com.edu.thss.smartdental.model.CircleElement;
import com.edu.thss.smartdental.ui.dialog.JoinCircleDialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class JoinCircleActivity extends FragmentActivity {
	
	private ListView list;
	private ArrayList<CircleElement> circles;
	private JoinCircleListAdapter listAdapter;
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_circle);
		fragmentManager = getSupportFragmentManager();
		
		list = (ListView) findViewById(R.id.join_circle_list);
		initCircles();
		listAdapter = new JoinCircleListAdapter(circles, getApplicationContext());
		list.setAdapter(listAdapter);
		list.setOnItemClickListener(new OnJoinCircleItemClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join_circle, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.join_circle_action_search).getActionView();
		searchView.setIconifiedByDefault(false);
		searchView.setQueryHint(Html.fromHtml("<font color=#D0D0D0>" + getResources().getString(R.string.join_circle_search_hint) + "</font>"));
		searchView.setOnQueryTextListener(filterQueryTextListener);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.join_circle_action_search:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initCircles() {
		circles = new ArrayList<CircleElement>();
		DBUtil db = new DBUtil();
		List<HashMap<String, String>> docList = db.getAllDoctors();
		Iterator<HashMap<String, String>> iterator = docList.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			HashMap<String, String> element = iterator.next();
			CircleElement circleElement = new CircleElement(element.get("doctorname"), element.get("doctorid"));
			circles.add(circleElement);
		}
		//CircleElement circleElement = new CircleElement("张三医生", 1);
		//circles.add(circleElement);
	}
	
	private SearchView.OnQueryTextListener filterQueryTextListener = new SearchView.OnQueryTextListener() {

		@Override
		public boolean onQueryTextChange(String s) {
			listAdapter.getFilter().filter(s);	
			return false;
		}

		@Override
		public boolean onQueryTextSubmit(String arg0) {
			return false;
		}
	};
	
	private class OnJoinCircleItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			JoinCircleDialog dialog = new JoinCircleDialog();
			TextView text = (TextView) view.findViewById(R.id.circle_list_item_title);
			dialog.setDocName(text.getText().toString());
			dialog.show(fragmentManager, "JoinCircle");
		}
		
	}
}
