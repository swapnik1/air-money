package com.swapnikshah.simple_expense.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	public String email;
	public String password;
	protected List<String> errors;
	
	public User(){
		this.setId(0);
		this.errors = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	protected void setId(int id) {
		this.id = id;
	}
	
	public boolean create(){
		// Create an entry for the user in db
		return true;
	}
	
	public boolean update(){
		// Update the user
		return true;
	}
	
	public boolean save(){
		if(this.getId() == 0){
			return this.create();
		}
		return this.update();
	}
	
	public boolean isValid(){
		// Perform Error Checking and save to db
		List<String> error_fields = new ArrayList<>();
		if(this.email.isEmpty()){
			error_fields.add("email");
		}
		if(this.password.isEmpty()){
			error_fields.add("password");
		}
		
		if(error_fields.size()==0){
			return true;
		}
		this.errors = error_fields;
		return false;
	}
	
	public static List<User> find(){
		List<User> users = new ArrayList<>();
		// Get all users from the db
		return users;
	}
	
	public static User find(int id){
		// get the user from db
		return null;
	}
	
	public static User findByEmail(String email){
		// get user from db
		return null;
	}
	
}
