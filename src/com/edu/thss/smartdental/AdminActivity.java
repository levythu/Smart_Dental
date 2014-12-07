package com.edu.thss.smartdental;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.util.Log;
import android.database.Cursor;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.edu.thss.smartdental.RemoteDB.DBUtil;

public class AdminActivity extends FragmentActivity {

	RadioGroup radioGroup;
	DBUtil db = new DBUtil();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		radioGroup = (RadioGroup)findViewById(R.id.admin_tab);
		radioGroup.check(R.id.add_single);
		changeFragment(0);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.add_single: 
					changeFragment(0); 
					break;
				case R.id.add_many:
					changeFragment(1);
					break;
				}
			}} );
	}
	
	private void changeFragment(int position){
		Fragment fragment  = null;
		switch (position){
		case 0: 
			fragment = new AddOneFragment();
			break;
		case 1: 
			fragment = new AddManyFragment();
			break;
		default:
			break;
		}
		if(fragment != null){
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.doctor_frame, fragment).commit();
		}
		else{
			Log.e("AdminActivity", "Error in creating fragment");
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
		switch (resultCode) {
		case RESULT_OK:      
			Uri uri = data.getData();
			String path = getPath(this, uri);
			try {
				FileReader fileReader = new FileReader(path);
				BufferedReader reader = new BufferedReader(fileReader);
				String buf;
				int sum = 0, error = 0;
				while (true) {
					try {
						buf = reader.readLine();
						if (buf == null)
							break;
						sum++;
						String[] doctor = buf.split(",");
						if (!db.insertUser(doctor[0], doctor[1], "doctor").equals("true"))
							error++;
					} catch (IOException e) {
						Toast.makeText(this, "打开文件失败", Toast.LENGTH_LONG);
						break;
					}
				}
				Toast.makeText(this, "导入了" + String.valueOf(sum) + "条记录，" + String.valueOf(error) + "个错误", Toast.LENGTH_LONG);
			} catch (FileNotFoundException e) {
				Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG);
			}
			break;
	    }
		super.onActivityResult(requestCode, resultCode, data);
	}

	private static String getPath(Context context, Uri uri) {		 
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = { "_data" };
			Cursor cursor = null;
			cursor = context.getContentResolver().query(uri, projection,null, null, null);
			int column_index = cursor.getColumnIndexOrThrow("_data");
			if (cursor.moveToFirst())
				return cursor.getString(column_index);
		}
		else
			if ("file".equalsIgnoreCase(uri.getScheme()))
				return uri.getPath();
		return null;
	    }
}
