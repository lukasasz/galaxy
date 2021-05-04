package Train;

import java.util.Random;

//interface for each sensor to implement
public interface Sensor {
	
	public int readData();
	public void sendDataToTSNR();
	public String generate_log_msg();
	
}


class WaterSensor implements Sensor {
	//data field that WaterSensor keeps track of	
	private int water_data;
	
	//returns the water_data
	public int readData() {
		return water_data;
	}
	
	//collects next data point
	public void sendDataToTSNR() {
		water_data = new Random().nextInt(); // Sensors arbitrarily collect data
	}
	
	//logs the current water_data value
	public String generate_log_msg() {
		String log_msg = "Current water sensor data: " +
						 Integer.toString(water_data);
		return log_msg;
	}
}

class RPMSensor implements Sensor {
	//data field that RPMSensor keeps track of
	private int rpm_data;
	
	//returns the rpm_data
	public int readData() {
		return rpm_data;
	}

	//collects next data point
	public void sendDataToTSNR() {
		rpm_data = new Random().nextInt();
	}
	
	//logs the current rpm_data value
	public String generate_log_msg() {
		String log_msg = "Current RPM sensor data: " +
						 Integer.toString(rpm_data);
		return log_msg;
	}
}

enum CameraStatus {
	NO_CAMERA,
	GATE_1_MILE,
	GATE_CLOSE,
	GATE_AND_HAZARD,
	CAMERA_HAZARD
}

class CameraSensor implements Sensor {
	
	//data field that CameraSensor keeps track of
	private int camera_data;
	
	//returns the camera_data
	public int readData() {
		return camera_data;
	}
	
	public CameraStatus translate_data() {
		if (camera_data == 1) {
			return CameraStatus.GATE_1_MILE;
		}
		else if (camera_data == 2) {
			return CameraStatus.GATE_CLOSE;
		}
		else if (camera_data == 3) {
			return CameraStatus.GATE_AND_HAZARD;
		}
		else if (camera_data == 4) {
			return CameraStatus.CAMERA_HAZARD;
		}
		return CameraStatus.NO_CAMERA;
	}
	
	//collects next data point
	public void sendDataToTSNR() {
		camera_data = new Random().nextInt();
	}
	
	//logs the current camera_data value
	public String generate_log_msg() {
		String log_msg = "Current camera sensor data: " +
						 Integer.toString(camera_data);
		return log_msg;
	}
}

class TimeOfFlight implements Sensor {
	
	//data field that TimeOfFlight keeps track of
	private int time_of_flight_data;
	
	//returns the time_of_flight_data
	public int readData() {
		return time_of_flight_data;
	}
	
	//collects next data point
	public void sendDataToTSNR() {
		time_of_flight_data = new Random().nextInt();
	}
	
	//logs the current time_of_flight_data value
	public String generate_log_msg() {
		String log_msg = "Current time of flight sensor data: " +
						 Integer.toString(time_of_flight_data);
		return log_msg;
	}
}