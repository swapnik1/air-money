package com.swapnikshah.simple_expense.interfaces;

import java.util.List;

public interface Communicator {
	public void respond(String data);
	
	public void logIn();
	
	public void putErrors(List<String> errors);
}
