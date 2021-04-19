package Train;

public class Operator extends IoT {
	
	public String name;

	public Operator(String name, Analytics_Engine Analytics) {
		super(Analytics);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	protected void log(String msg, Status new_status) {
		Admin.log(session_id, "Operator: " + msg, new_status);
	}

}
