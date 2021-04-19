package Train;

public class Operator extends IoT {

	/*
	Operator class that extends IoT class, mainly used to keep track of who is using the IoT
	*/	
	public String name;

	//constructor to set the name and initialize the IoT with the given Analytics_Engine
	public Operator(String name, Analytics_Engine Analytics) {
		super(Analytics);
		this.name = name;
	}
	
	//returns the name of the current user in the session
	public String getName() {
		return name;
	}
	
	//overrides log to print operator along with the message
	@Override
	protected void log(String msg, Status new_status) {
		Technician.log(session_id, "Operator: " + msg, new_status);
	}

}
