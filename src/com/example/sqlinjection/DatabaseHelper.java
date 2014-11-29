package com.example.sqlinjection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TB_NAME = "tblUserName";
	public static final String ID = "_id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version){
		super(context, name, factory, version);
		
		//TODO Auto-Generated constructor stub
	}
	
	@Override
	
	public void onCreate(SQLiteDatabase db){
		//db.execSQL("DROP TABLE " + TB_NAME);
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME + "(" + ID + " INTEGER PRIMARY KEY," + USERNAME + " VARCHAR(200)," + PASSWORD + " VARCHAR(200))");
		//db.execSQL("INSERT INTO " + TB_NAME + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "(1,'admin','admin888')");
		//db.execSQL("INSERT INTO " + TB_NAME + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "(2,'wanqing','wanqing')");
		//db.execSQL("INSERT INTO " + TB_NAME + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "(3,'root','root123')");
		db.execSQL("INSERT INTO " + TB_NAME + "(" + USERNAME + "," + PASSWORD + ") VALUES" + "('admin','admin888')");
		db.execSQL("INSERT INTO " + TB_NAME + "(" + USERNAME + "," + PASSWORD + ") VALUES" + "('wanqing','wanqing')");
		db.execSQL("INSERT INTO " + TB_NAME + "(" + USERNAME + "," + PASSWORD + ") VALUES" + "('root','root123')");

	}
	
	@Override
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		 
		//Delete the existing Table
		db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.TB_NAME);
		
		//Create a new one
		onCreate(db);
	}
	
	@Override
	
	public void onOpen(SQLiteDatabase db){
		
		//TODO Auto-generated method stub
		
		super.onOpen(db);
		
	}
}
