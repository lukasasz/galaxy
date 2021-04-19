package Train;

import java.util.Random;

public class Analytics_Engine {

	TSNR tsnr;
	long session_id;
	double MAX_SPEED = 200.0;
	Boolean is_hazard_rain = false;
	Boolean is_hazard_rpm = false;
	Boolean is_hazard_camera = false;
	String alert;
	
	public Analytics_Engine(TSNR tsnr) {
		this.tsnr = tsnr;
		this.session_id = tsnr.session_id;
	}
	
	protected int processRainData() {
		return tsnr.latest_rainData;
	}
	
	protected int processRPMData() {
		return tsnr.latest_rpmData;
	}
	
	protected int processCameraData() {
		return tsnr.latest_cameraData;
	}
	
	private void setMaxSpeed(double new_max_speed) {
		MAX_SPEED = new_max_speed;
	}
 	
	public void sendAnalyticsToDashboard() {
		int check = processRainData();
		if (check > 8) {
			is_hazard_rain = true;
			double calculated_max_speed = new Random().nextDouble() % 200.0;
			String ALERT = "Hazardous rain conditions detected in Analytics Engine: " + 
					Integer.toString(check) + "\nNew max speed: " + calculated_max_speed;
			alert = ALERT;
			log(ALERT, Status.ONLINE);
			setMaxSpeed(calculated_max_speed);
		} else {
			is_hazard_rain = false;
			if (!is_hazard_rpm && !is_hazard_camera) setMaxSpeed(200.0);
		}
		
		check = processRPMData();
		if (check > 30) {
			is_hazard_rpm = true;
			double calculated_max_speed = new Random().nextDouble() % 200.0;
			String ALERT = "Hazardous RPM conditions detected in Analytics Engine: " + 
					Integer.toString(check) + "\nNew max speed: " + calculated_max_speed;
			alert = ALERT;
			log(ALERT, Status.ONLINE);
			setMaxSpeed(calculated_max_speed);
		} else {
			is_hazard_rpm = false;
			if (!is_hazard_camera && !is_hazard_rain) setMaxSpeed(200.0);
		}
		
		check = processCameraData();
		if (check < 10) {
			is_hazard_camera = true;
			double calculated_max_speed = new Random().nextDouble() % 200.0;
			String ALERT = "Hazardous camera conditions detected in Analytics Engine: " + 
					Integer.toString(check) + "\nNew max speed: " + calculated_max_speed;
			alert = ALERT;
			log(ALERT, Status.ONLINE);
			setMaxSpeed(calculated_max_speed);
		} else {
			is_hazard_camera = false;
			if (!is_hazard_rpm && !is_hazard_rain) setMaxSpeed(200.0);
		}
	}
	
	private void log(String msg, Status new_status) {
		Admin.log(session_id, msg, new_status);
	}
	

}