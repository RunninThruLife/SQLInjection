package com.example.sqlinjection;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
	
	public static String sMethodToUse = "";
	DBAdapter m_DA;
	SQLiteDatabase m_DB;
	MethodSelection m_MTS;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    
    public void ChangeMethod_Click(View v){
        Intent intent = new Intent(this, MethodSelection.class);
        this.startActivity(intent);

    }
    
    public void Search_Click(View v){
    	
        m_DA = new DBAdapter(this);
        m_DB = m_DA.open();
        
    	EditText editT = (EditText) findViewById(R.id._id);
    	TextView textV = (TextView) findViewById(R.id.txtOutput); textV.setText("");
    	Editable m_id = editT.getText();
    	String s_id = m_id.toString();
    	Cursor cursor;;
    	String result = "";
    	AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
    	
    	if (MainActivity.sMethodToUse != ""){
    		if (MainActivity.sMethodToUse == "Concat"){
    			try{
    				cursor = m_DB.rawQuery("Select * From " + DatabaseHelper.TB_NAME + " WHERE _id ='" + m_id + "'",null);
    				cursor.moveToFirst();

    				while(!cursor.isAfterLast()){
    					result += "id:" + cursor.getInt(0) + "\r\n" + "user:" + cursor.getString(1) + "\r\n" + "pass:" + cursor.getString(2) + "\r\n";
    					textV.setText(result);
    					Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
    					cursor.moveToNext();
    				}

    				cursor.close();
    			}

    			catch (SQLiteException ex_SQL){
    				dlgAlert.setTitle("SQL Exception Caught!");
    				dlgAlert.setMessage(ex_SQL.getMessage());
    				dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    					@Override
    					public void onClick(DialogInterface dialog, int which) {
    						// TODO Auto-generated method stub
    						finish();
    					}
    				});
    				dlgAlert.setCancelable(true);
    				dlgAlert.create().show();
    			}
    		}
    		else{
    			try{
    				String m_argv[] = {s_id};
    				cursor = m_DB.rawQuery("Select * From " + DatabaseHelper.TB_NAME + " WHERE _id = ? ",m_argv );
    				//cursor = m_DB.rawQuery("Select * From " + DatabaseHelper.TB_NAME + " WHERE _id ='" + m_id + "'",null);
    				cursor.moveToFirst();

    				while(!cursor.isAfterLast()){
    					result += "id:" + cursor.getInt(0) + "\r\n" + "user:" + cursor.getString(1) + "\r\n" + "pass:" + cursor.getString(2) + "\r\n";
    					textV.setText(result);
    					Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
    					cursor.moveToNext();
    				}

    				cursor.close();
    			}
    			catch (SQLiteException ex_SQL){
    				dlgAlert.setTitle("SQL Exception Caught!");
    				dlgAlert.setMessage(ex_SQL.getMessage());
    				dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    					@Override
    					public void onClick(DialogInterface dialog, int which) {
    						// TODO Auto-generated method stub
    						finish();
    					}
    				});
    				dlgAlert.setCancelable(true);
    				dlgAlert.create().show();
    			}
    		}
    	}
    	else{
    		dlgAlert.setTitle("No Method Selected!");
    		dlgAlert.setMessage("No method of SQL generation has been selected.  Please use the 'Change Method' option on the main form to choose a type.");
    		dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				finish();
    			}
    		});
    		dlgAlert.setCancelable(true);
    		dlgAlert.create().show();
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


