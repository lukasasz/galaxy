package Train;

import java.util.Random;

public interface Sensor {
	
	public int readData();
	public void sendDataToTSNR();
	public String generate_log_msg();
	
}

class WaterSensor implements Sensor {
	
	private int water_data;
	
	public int readData() {
		return water_data;
	}
	
	public void sendDataToTSNR() {
		water_data = new Random().nextInt(); // Sensors arbitrarily collect data
	}
	
	public String generate_log_msg() {
		String log_msg = "Current water sensor data: " +
						 Integer.toString(water_data);
		return log_msg;
	}
}

class RPMSensor implements Sensor {
	
	private int rpm_data;
	
	public int readData() {
		return rpm_data;
	}
	
	public void sendDataToTSNR() {
		rpm_data = new Random().nextInt();
	}
	
	public String generate_log_msg() {
		String log_msg = "Current RPM sensor data: " +
						 Integer.toString(rpm_data);
		return log_msg;
	}
}

class CameraSensor implements Sensor {
	
	private int camera_data;
	
	public int readData() {
		return camera_data;
	}
	
	public void sendDataToTSNR() {
		camera_data = new Random().nextInt();
	}
	
	public String generate_log_msg() {
		String log_msg = "Current camera sensor data: " +
						 Integer.toString(camera_data);
		return log_msg;
	}
}