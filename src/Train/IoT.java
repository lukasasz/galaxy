package Train;

public class IoT {
	
	private Analytics_Engine Analytics;
	protected long session_id;
	private static double MAX_SPEED;
	private static double CURR_SPEED;
	private Boolean is_hazard_rain, is_hazard_rpm, is_hazard_camera;
	private String alert;

	public IoT(Analytics_Engine Analytics) {
		session_id = Analytics.session_id;
		MAX_SPEED = Analytics.MAX_SPEED;
		is_hazard_rain = Analytics.is_hazard_rain;
		is_hazard_rpm = Analytics.is_hazard_rpm;
		is_hazard_camera = Analytics.is_hazard_camera;
		alert = Analytics.alert;
		if (is_hazard_rain || is_hazard_rpm || is_hazard_camera) {
			displayAlert();
		}
		CURR_SPEED = MAX_SPEED;
	}
	
	public boolean is_hazard_Rain() {
		return is_hazard_rain;
	}
	
	public boolean is_hazard_RPM() {
		return is_hazard_rpm;
	}
	
	public boolean is_hazard_Camera() {
		return is_hazard_camera;
	}
	
	public double readMaxSpeed() {
		return MAX_SPEED;
	}
	
	protected void setSpeed(double speed) {
		if (speed <= MAX_SPEED) {
			log("Set train speed to " + Double.toString(speed) + ".\n", Status.ONLINE);
			CURR_SPEED = speed;
		}
		else {
			System.err.println("Speed is too high. Please check max speed.");
		}
	}
	
	public void renderRain() {
		int rain_data = Analytics.processRainData();
		System.out.println(rain_data);
	}
	
	public void renderRPM() {
		int rpm_data = Analytics.processRPMData();
		System.out.println(rpm_data);
	}
	
	public void renderCamera() {
		int camera_data = Analytics.processCameraData();
		System.out.println(camera_data);
	}
	
	public void renderSpeed() {
		System.out.println("Current speed is " + Double.toString(CURR_SPEED));
		System.out.println("Maximum speed is " + Double.toString(MAX_SPEED));
	}
	
	public void renderAuthentication() {
		System.out.println("IoT authenticated under session ID " + Long.toString(session_id));
	}
	
	public void displayAlert() {
		System.out.println(alert);
	}
	
	protected void log(String msg, Status new_status) {
		Admin.log(session_id, msg, new_status);
	}
	

}
