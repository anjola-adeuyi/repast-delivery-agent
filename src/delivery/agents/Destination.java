package delivery.agents;

public class Destination {

	int id;
	public boolean isAutonomous;

	public Destination(int id, boolean isAutonomous) {
		this.id = id;
		this.isAutonomous = isAutonomous;
	}

	public int getId() {
		return id;
	}
}
