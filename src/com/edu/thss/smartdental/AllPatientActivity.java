package com.edu.thss.smartdental;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import com.edu.thss.smartdental.RemoteDB.UserDBUtil;


public class AllPatientActivity extends Activity {

	ListView allpatient = null;
	UserDBUtil db = new UserDBUtil();
	SharedPreferences preferences = null;
	ActionBar actionBar;
	List<HashMap<String, String>> list;
	int tPosition;
	
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
		allpatient.setOnItemLongClickListener(patientListener);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}
	
	OnItemLongClickListener patientListener = new OnItemLongClickListener() {
		public boolean onItemLongClick(AdapterView<?>adapterView, View view, int position, long id) {
			tPosition = position;
			AlertDialog.Builder builder = new AlertDialog.Builder(AllPatientActivity.this);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("确认");
			builder.setMessage("您真的要将" + list.get(position + 1).get("username") + "踢出圈子吗？");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(AllPatientActivity.this, db.kickout(list.get(tPosition + 1).get("username"), preferences.getString("username", "")), Toast.LENGTH_LONG).show();
					list = db.getAllpatients(preferences.getString("username", ""));
					ArrayList<String> data = new ArrayList<String>();
					for (int i = 1; i < list.size(); i++) {
						data.add(list.get(i).get("username"));
					}
					allpatient.setAdapter(new ArrayAdapter<String>(AllPatientActivity.this, android.R.layout.simple_list_item_1, data));
				}
			});
			builder.setNegativeButton("取消", null);
			builder.show();
			return true;
		}
	};
}
