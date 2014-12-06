package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.JoinCircleListAdapter;
import com.edu.thss.smartdental.model.CircleElement;
import com.edu.thss.smartdental.ui.dialog.JoinCircleDialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class JoinCircleActivity extends FragmentActivity {
	
	private ListView list;
	private ArrayList<CircleElement> circles;
	private JoinCircleListAdapter listAdapter;
	private EditText editText;
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_circle);
		fragmentManager = getSupportFragmentManager();
		
		editText = (EditText) findViewById(R.id.circle_searchbox);
		editText.addTextChangedListener(filterTextWatcher);
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
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_search) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initCircles() {
		circles = new ArrayList<CircleElement>();
		/*DBUtil db = new DBUtil();
		List<HashMap<String, String>> docList = db.getAllDoctors();
		for (HashMap<String, String> element: docList) {
			CircleElement circleElement = new CircleElement(element.get("CName"), Integer.parseInt(element.get("CNo")));
			circles.add(circleElement);
		}*/
		CircleElement circleElement = new CircleElement("张三医生", 1);
		circles.add(circleElement);
	}
	
	private TextWatcher filterTextWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int star, int count,
				int after) {
			listAdapter.getFilter().filter(s);	
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
