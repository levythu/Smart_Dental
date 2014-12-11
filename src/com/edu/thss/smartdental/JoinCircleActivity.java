/**
 * Author: FU CHIN SENG
 */
package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import com.edu.thss.smartdental.adapter.JoinCircleListAdapter;
import com.edu.thss.smartdental.model.CircleElement;
import com.edu.thss.smartdental.ui.dialog.JoinCircleDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class JoinCircleActivity extends FragmentActivity implements JoinCircleDialog.JoinCircleDialogListener{
	
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
		getMenuInflater().inflate(R.menu.join_circle, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.join_circle_action_search).getActionView();
		searchView.setIconifiedByDefault(false);
		searchView.setQueryHint(Html.fromHtml("<font color=#D0D0D0>" + getResources().getString(R.string.join_circle_search_hint) + "</font>"));
		searchView.setOnQueryTextListener(filterQueryTextListener);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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

	@SuppressLint("CommitPrefEdits")
	@Override
	public void onDialogPositiveClick(JoinCircleDialog dialog) {
		DBUtil db = new DBUtil();
		EditText text = (EditText) dialog.getDialogView().findViewById(R.id.circle_password);
		String password = text.getText().toString();
		String username = getSharedPreferences("setting", Activity.MODE_PRIVATE).getString("username", "");
		String joinResult = db.joinCircle(username, password, dialog.getDocName());
		String message = new String();
		switch (joinResult) {
		case "doctor does not exist":
			message = getResources().getString(R.string.doctor_does_not_exist);
			break;
		case "wrong password":
			message = getResources().getString(R.string.wrong_password);
			break;
		case "user does not exist":
			message = getResources().getString(R.string.user_does_not_exist);
			break;
		case "already in circle":
			message = getResources().getString(R.string.already_in_circle);
			break;
		case "true":
			message = getResources().getString(R.string.join_successfully);
			break;
		case "System busy,please wait":
			message = getResources().getString(R.string.system_busy);
		}
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
		if (joinResult.compareTo("true") == 0) {
			Editor editor = getSharedPreferences("settings", Activity.MODE_PRIVATE).edit();
			editor.putString("current_circle", dialog.getDocName());
		}
	}

	@Override
	public void onDialogNegativeClick(JoinCircleDialog dialog) {
	}
}
