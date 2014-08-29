/**
 * 
 */
package com.swapnikshah.simple_expense.fragments;

import com.swapnikshah.simple_expense.R;
import com.swapnikshah.simple_expense.models.User;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
	
	public EditText email;
	public EditText password;
	public Button login_btn;
	public Button register_btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.user_form_fragment, container);
		this.email = (EditText) view.findViewById(R.id.email);
		this.password = (EditText) view.findViewById(R.id.password);
		
		this.login_btn = (Button) view.findViewById(R.id.Login_btn);
		this.register_btn = (Button) view.findViewById(R.id.Register_btn);
		
		this.email.addTextChangedListener(new TextWatcher() {
			
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
		if(v.getId()==this.login_btn.getId()){
			// Authenticate the user
		}
		if(v.getId()==this.register_btn.getId()){
			// Register the user
			User user = new User();
			user.email =this.email.toString();
			user.password = this.password.toString();
			if(!user.isValid()){
				// Invalid user
				return ;
			}
			if(user.save()){
				// redirect to expense activity
			}
		}
	}
}
