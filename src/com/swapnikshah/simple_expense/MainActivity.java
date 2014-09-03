package com.swapnikshah.simple_expense;

import java.util.List;

import com.swapnikshah.simple_expense.interfaces.Communicator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity implements Communicator{

	public static SharedPreferences prefs;
	public Intent loginIntent = new Intent(this, null);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        prefs = getSharedPreferences("SimpleExpensePrefs", Context.MODE_PRIVATE);
    }
    
    @Override
	public void logIn() {
		Log.d("EXP", "User Logged in, Go to another Activity");
	}

	@Override
	public void respond(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putErrors(List<String> errors) {
		String text = "";
		for(String error : errors){
			text.concat(error+"\n");
		}
		Toast t = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
		t.setGravity(Gravity.TOP, 0, 0);
		t.show();
	}

}
