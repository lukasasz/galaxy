package Train;

import java.util.Random;
import java.util.Scanner;


public class Authentication extends Technician {

//	private 
	protected long session_id;
	protected WaterSensor water_sensor;
	protected RPMSensor rpm_sensor;
	protected CameraSensor camera_sensor;
	
	//authentication constructor calls the super constructor for technician to set the train to OFFLINE status
	//generates a session id for the train
	//initializes each of the sensors
	public Authentication() {
		super();
		session_id = new Random().nextLong();
		init();
		water_sensor = new WaterSensor();
		rpm_sensor = new RPMSensor();
		camera_sensor = new CameraSensor();
	}
	
	//attempts to sign in and calls itself until the train status is ONLINE
	private void init() {
		signIn();
		while (train_status == Status.DISABLED) {
			// Await technician to approve status to change.
			continue;
		}
		if (train_status == Status.OFFLINE) {
			init();
		}
	}
	
	//takes in a password input for the system and allows the user to attempt signing in until they reach the max login attempts
	//actively logs successful logins and failures as well.
	protected void signIn() {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < MAX_LOGIN_ATTEMPTS; i++) {
			System.out.println("Enter IoT password to start system: ");
			String attempt = scanner.next();
			if (verifyPassword(attempt)) {
				log(session_id, "Successful login to IoT made.\n", Status.ONLINE);
				scanner.close();
				break;
			}
		}
		scanner.close();
		String error_str = "Too many invalid password atempts.\n" +
				"Train status has been disabled.\n";
		log(session_id, error_str, Status.DISABLED);
	}
	
	//signs the user out and logs the sign out
	protected void signOut() {
		log(session_id, "Signing out.\n", Status.OFFLINE);
	}
	
}
