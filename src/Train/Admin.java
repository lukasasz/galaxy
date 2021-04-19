package Train;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin {
	/*
	Admin class
	*/

	//password for login
	private final static String PASSWORD = "password";
	
	//allowed attempts for logging in
	protected final static int MAX_LOGIN_ATTEMPTS = 5;

	//status to keep track of if the train is online and the user is authenticated
	protected static Status train_status;
	
	//constructor to set train_status to offline
	Admin() {
		train_status = Status.OFFLINE;
	}
	
	//checks to see if the inputed password is correct
	protected static Boolean verifyPassword(String input) {
		return input.equals(PASSWORD);
	}
	
	//adds to the current session's log file to keep track of attemtped logins and train status
	public static void log(long session_id, String msg, Status new_status) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());

		try {
			String file_name = session_id + ".txt";
			File log = new File(file_name);
			if (!log.exists()) {
				log.createNewFile();	
			}
			FileWriter f = new FileWriter(file_name);
			f.write(formatter.format(date));
			f.write(Long.toString(session_id));
			f.write(msg);
			f.close();
			train_status = new_status;
		}
		catch (IOException e) {
			System.err.println("An error occured writing log file.");
			e.printStackTrace();
		}
	}
	
	//returns the status of the train
	protected Status getStatus() {
		return train_status;
	}
	
	//switches the train status from disabled to offline
	private void enable() {
		train_status = Status.OFFLINE;
	}
	
	//creates the admin object and 
	public static void main (String[] args) {
		Admin admin = new Admin();
		if (train_status == Status.DISABLED) {
			// Admin must investigate
			admin.enable();
		}
	}

}
