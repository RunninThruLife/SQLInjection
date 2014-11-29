package com.example.sqlinjection;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MethodSelection extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_method_selection);

		}
	
	public void RadioSelect_Click(View v){
		RadioButton rdoParam = (RadioButton) findViewById(R.id.rdoParameterization);
		RadioButton rdoConcat = (RadioButton) findViewById(R.id.rdoStringConcat);
		
		if (rdoParam.isChecked()){
			rdoConcat.setChecked(false);
		}
		
		if (rdoConcat.isChecked()){
			rdoParam.setChecked(false);
		}
	}
	
	public void MethodSelect_Click(View v){
		RadioButton rdoParam = (RadioButton) findViewById(R.id.rdoParameterization);
		RadioButton rdoConcat = (RadioButton) findViewById(R.id.rdoStringConcat);
		if (rdoParam.isChecked()){
			MainActivity.sMethodToUse = "Param";
		}
		else{
			MainActivity.sMethodToUse = "Concat";
		}
		
		if (rdoParam.isChecked() == false && rdoConcat.isChecked() == false){
			AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        	dlgAlert.setTitle("Nothing Selected!");
        	dlgAlert.setMessage("A button must be selected before pressing the 'Select' button.  Please try again.");
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
		else{
			finish();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.method_selection, menu);
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
