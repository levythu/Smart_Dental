package com.edu.thss.smartdental;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;
import com.edu.thss.smartdental.RemoteDB.UserDBUtil;

public class AllPatientActivity extends Activity {

	ListView allpatient = null;
	UserDBUtil db = new UserDBUtil();
	SharedPreferences preferences = null;
	ActionBar actionBar;
	List<HashMap<String, String>> list;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_patient);
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		preferences = getSharedPreferences("setting", android.content.Context.MODE_PRIVATE);
		list = db.getAllpatients(preferences.getString("username", ""));
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 1; i < list.size(); i++) {
			data.add(list.get(i).get("username"));
		}
		allpatient = (ListView)findViewById(R.id.all_patient);
		allpatient.setAdapter(new ArrayAdapter<String>(AllPatientActivity.this, android.R.layout.simple_list_item_1, data));
		allpatient.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?>adapterView, View view, int position, long id) {
				Toast.makeText(AllPatientActivity.this,"您选择了" + position, Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}
}
