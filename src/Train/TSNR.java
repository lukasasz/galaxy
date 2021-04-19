package Train;

public class TSNR {

	protected int latest_rainData, latest_rpmData, latest_cameraData;
	private WaterSensor ws;
	private RPMSensor rs;
	private CameraSensor cs;
	protected long session_id;
	
	public TSNR(WaterSensor water, RPMSensor rpm, CameraSensor camera, long session_id) {
		ws = water;
		rs = rpm;
		cs = camera;
		this.session_id = session_id;
	}
	
	public void sendDataToAnalytics() {
		latest_rainData = ws.readData();
		Admin.log(session_id, ws.generate_log_msg(), Status.ONLINE);
		latest_rpmData = rs.readData();
		Admin.log(session_id, rs.generate_log_msg(), Status.ONLINE);
		latest_cameraData = cs.readData();
		Admin.log(session_id, cs.generate_log_msg(), Status.ONLINE);
	}

}
