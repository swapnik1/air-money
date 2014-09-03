package com.swapnikshah.simple_expense.helpers;

import com.swapnikshah.simple_expense.MainActivity;
import com.swapnikshah.simple_expense.models.User;

public class Auth {
	
	private static boolean isLoggedIn = false;
	private static User user;
	
	public static boolean attempt(String email, String password){
		// Try to login the user
		User auth_user = User.findByEmail(email);
		if(auth_user==null){
			return false;
		}
		if(!auth_user.comparePassword(password)){
			return false;
		}
		isLoggedIn = true;
		storeUser();
		return true;
	}
	
	public static boolean check(){
		return isLoggedIn;
	}
	
	public static boolean loginWithId(int id){
		user = User.find(id);
		if(user==null){
			return false;
		}
		storeUser();
		return true;
	}
	
	public static User getUser(){
		if(!isLoggedIn){
			return null;
		}
		return user;
	}
	
	public static void logOut(){
		user = null;
		isLoggedIn = false;
		deleteUser();
	}
	
	private static void deleteUser() {
		if(MainActivity.prefs.contains("user")){
			MainActivity.prefs.edit()
					.remove("user")
					.remove("password")
					.apply();
		}
	}

	private static void storeUser(){
		if(!MainActivity.prefs.contains("user")){
			MainActivity.prefs.edit()
					.putString("user", user.email)
					.putString("password", user.password)
					.apply();
		}
	}

}
