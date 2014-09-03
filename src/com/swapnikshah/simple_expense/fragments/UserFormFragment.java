/**
 * 
 */
package com.swapnikshah.simple_expense.fragments;

import java.util.ArrayList;
import java.util.List;

import com.swapnikshah.simple_expense.R;
import com.swapnikshah.simple_expense.helpers.Auth;
import com.swapnikshah.simple_expense.interfaces.Communicator;
import com.swapnikshah.simple_expense.models.User;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author SWAPNIK
 *
 */
public class UserFormFragment extends Fragment implements OnClickListener{
	
	public EditText email_et;
	public EditText password_et;
	public Button login_btn;
	public Button register_btn;
	
	public String email;
	public String password;
	public Communicator comm;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.user_form_fragment, container);
		
		comm = (Communicator) getActivity();
		
		this.email_et = (EditText) view.findViewById(R.id.email);
		this.password_et = (EditText) view.findViewById(R.id.password);
		
		this.login_btn = (Button) view.findViewById(R.id.Login_btn);
		this.login_btn.setOnClickListener(this);
		this.login_btn.setClickable(false);
		
		this.register_btn = (Button) view.findViewById(R.id.Register_btn);
		this.register_btn.setOnClickListener(this);
		this.register_btn.setClickable(false);
		
		this.email_et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// Check if email exists
				String email = s.toString();
				if(User.findByEmail(email)!=null){
					login_btn.setClickable(true);
					register_btn.setClickable(false);
				}else{
					login_btn.setClickable(false);
					register_btn.setClickable(true);
				}
			}
		});
				
		return view;
	}

	@Override
	public void onClick(View v) {
		this.email = this.email_et.toString();
		this.password = this.password_et.toString();
		if(v.getId()==this.login_btn.getId()){
			// Authenticate the user
			if(!Auth.attempt(this.email, this.password)){
				// Invalid user
				List<String> errors = new ArrayList<>();
				errors.add("Email password combination you entered is incorrect!");
				Log.d("EXP", "Email password combination you entered is incorrect!");
				comm.putErrors(errors);
				return;
			}
			comm.logIn();
		}
		if(v.getId()==this.register_btn.getId()){
			// Register the user
			User user = new User();
			user.email =this.email;
			user.password = this.password;
			if(!user.isValid()){
				// Invalid user
				comm.putErrors(user.errors);
				return;
			}
			if(user.save()){
				Log.d("EXP", "User saved to db, calling login on activity now");
				comm.logIn();
				Log.d("EXP", "returned from comm.login");
				return;
			}
		}
	}
}
