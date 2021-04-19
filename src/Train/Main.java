package Train;
import java.util.concurrent.TimeUnit;
public class Main {

	public static void main(String[] args) {
		Startup start = new Startup();
		WaterSensor water_sensor = start.water_sensor;
		RPMSensor rpm_sensor = start.rpm_sensor;
		CameraSensor camera_sensor = start.camera_sensor;
		TSNR tsnr = new TSNR(water_sensor, rpm_sensor, camera_sensor, start.session_id);
		Analytics_Engine Analytics = new Analytics_Engine(tsnr);
		IoT dashboard = new IoT(Analytics);
		Operator operator = new Operator("John Smith", Analytics);
		

		//if the status of the train is online, we update the data and send the analytics to the dashboard every second
		while (start.getStatus() == Status.ONLINE) {
			/*  These should all work so that we need minimal actions like this here
			 *    All alerts and changes to variables should be done internally.  
			 *        The main issues would be with statics or inheritance        */
			water_sensor.sendDataToTSNR();
			rpm_sensor.sendDataToTSNR();
			camera_sensor.sendDataToTSNR();
			tsnr.sendDataToAnalytics();
			Analytics.sendAnalyticsToDashboard();
			operator.setSpeed(dashboard.readMaxSpeed());
			TimeUnit.MINUTES.sleep(1);
		}

		//once the train goes online we sign out
		start.signOut();
	}

}
