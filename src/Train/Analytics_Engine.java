package Train;

import java.util.Random;

public class Analytics_Engine {

	//initialize hazards and other analytics values to defaults
	TSNR tsnr;
	long session_id;
	double MAX_SPEED = 200.0;
	Boolean is_hazard_rain = false;
	Boolean is_hazard_rpm = false;
	Boolean is_hazard_camera = false;
	String alert;
	

	//constructs Analytics_Engine given the tsnr
	//sets the session id
	public Analytics_Engine(TSNR tsnr) {
		this.tsnr = tsnr;
		this.session_id = tsnr.session_id;
	}
	
	//processes the latest rain data
	protected int processRainData() {
		return tsnr.latest_rainData;
	}
	
	//processes the latest RPM data
	protected int processRPMData() {
		return tsnr.latest_rpmData;
	}
	
	//processes the latest camera data
	protected int processCameraData() {
		return tsnr.latest_cameraData;
	}
	
	//sets the max speed to the new_max_speed
	private void setMaxSpeed(double new_max_speed) {
		MAX_SPEED = new_max_speed;
	}
 	
	
	public void sendAnalyticsToDashboard() {
		//variable to check if there are hazards
		int check = processRainData();

		if (check > 8) {
			//if the rain_data value is more than 8 then there is a rain hazard so we set the alert to the appropriate hazard message and 
			//suggests the new max speed
			is_hazard_rain = true;
			double calculated_max_speed = new Random().nextDouble() % 200.0;
			String ALERT = "Hazardous rain conditions detected in Analytics Engine: " + 
					Integer.toString(check) + "\nNew max speed: " + calculated_max_speed;
			alert = ALERT;
			log(ALERT, Status.ONLINE);
			setMaxSpeed(calculated_max_speed);
		} else {
			//if there is no rain hazard we set is_rain_hazard to false and reset the max speed to 200 if there are currently no other hazards detected
			is_hazard_rain = false;
			if (!is_hazard_rpm && !is_hazard_camera) setMaxSpeed(200.0);
		}
		
		check = processRPMData();
		if (check > 30) {
			//if the rpm_data value is more than 30 then there is a RPM hazard so we set the alert to the appropriate hazard message and 
			//suggests the new max speed
			is_hazard_rpm = true;
			double calculated_max_speed = new Random().nextDouble() % 200.0;
			String ALERT = "Hazardous RPM conditions detected in Analytics Engine: " + 
					Integer.toString(check) + "\nNew max speed: " + calculated_max_speed;
			alert = ALERT;
			log(ALERT, Status.ONLINE);
			setMaxSpeed(calculated_max_speed);
		} else {
			//if there is no rpm hazard we set is_rpm_hazard to false and reset the max speed to 200 if there are currently no other hazards detected

			is_hazard_rpm = false;
			if (!is_hazard_camera && !is_hazard_rain) setMaxSpeed(200.0);
		}
		
		check = processCameraData();
		if (check < 10) {
			//if the camera_data value is less than 10 then there is a camera hazard so we set the alert to the appropriate hazard message and 
			//suggests the new max speed
			is_hazard_camera = true;
			double calculated_max_speed = new Random().nextDouble() % 200.0;
			String ALERT = "Hazardous camera conditions detected in Analytics Engine: " + 
					Integer.toString(check) + "\nNew max speed: " + calculated_max_speed;
			alert = ALERT;
			log(ALERT, Status.ONLINE);
			setMaxSpeed(calculated_max_speed);
		} else {
			//if there is no camera hazard we set is_camera_hazard to false and reset the max speed to 200 if there are currently no other hazards detected
			is_hazard_camera = false;
			if (!is_hazard_rpm && !is_hazard_rain) setMaxSpeed(200.0);
		}
	}
	
	private void log(String msg, Status new_status) {
		Technician.log(session_id, msg, new_status);
	}
	

}
