package com.edu.thss.smartdental.db;

import android.content.Context;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper{
      private static final String DATABASE_NAME = "smartdental.db";
      private static final int DATABASE_VERSION = 1;
      private static final String PACKAGE_NAME="com.edu.thss.smartdental";
      public static final String DB_PATH="/data"+Environment.getDataDirectory().getAbsolutePath()+"/"+PACKAGE_NAME;
      
      public DBHelper(Context context){
    	  super(context,DATABASE_NAME,null,DATABASE_VERSION);
      }

	@Override
	public void onCreate(SQLiteDatabase db) {
		//数据项还需要修改
		String sql="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20),age integer,sex varchar(2))";
        db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE patient ADD COLUMN ohter STRING");
	}
}
