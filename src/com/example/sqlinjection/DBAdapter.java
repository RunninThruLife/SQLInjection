package com.example.sqlinjection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBAdapter {

	private static final int iDATABASE_VERSION = 2;
		
	private SQLiteDatabase m_DB;
	private DatabaseHelper m_DbHelper;
	
	public DBAdapter(Context lv_Context){
		m_DbHelper = new DatabaseHelper(lv_Context, DatabaseHelper.TB_NAME,null,iDATABASE_VERSION);
		
	}
	
	// Close the database object
	public void close(){
		m_DB.close();
	}
	
	// Open the database... If a database is not found, one is created.
	// throws: 'SQLiteException'
	public SQLiteDatabase open() throws SQLiteException {
		try{
			m_DB = m_DbHelper.getWritableDatabase();
			m_DbHelper.onCreate(m_DB);
			return m_DB;
		}
		catch (SQLiteException ex){
			
		}
		return m_DB;
	}
}
