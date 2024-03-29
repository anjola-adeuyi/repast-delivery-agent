package delivery.agents;

import org.apache.log4j.Logger;
import org.geotools.geometry.DirectPosition2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.geometry.DirectPosition;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.WritableGridCoverage2D;
import repast.simphony.space.graph.Network;
import repast.simphony.util.ContextUtils;

/**
 * 
 * @author Anjolaoluwa Adeuyi Joshua
 *
 */
public class DeliveryAgent {

	private String name;
	private int destinationCount = 1;
	private Destination lastDestination = null;
	public boolean isAutonomous;

	private static final Logger logger = Logger.getLogger(DeliveryAgent.class.getName());

	public DeliveryAgent(String name, boolean isAutonomous) {
		this.name = name;
		this.isAutonomous = isAutonomous;
	}

	@ScheduledMethod(start = 1, interval = 1, priority = ScheduleParameters.FIRST_PRIORITY)
	public void step() {
		if (isAutonomous) {
//			autonomousMove();
			for (int i = 0; i < 2; i++) { // Autonomous vehicles move twice in each time step
				autonomousMove();
			}
		} else {
			humanOperatedMove();
		}

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void autonomousMove() {
		randomMove();
		trackInCoverage();
		dropDestination();
		logger.info(name);
		logger.info(destinationCount);
	}

	private void humanOperatedMove() {
		randomMove();
		trackInCoverage();
		dropDestination();
		logger.info(name);
		logger.info(destinationCount);
	}

	public void dropDestination() {
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography) context.getProjection("Geography");
		Network net = (Network) context.getProjection("Network");

		Coordinate loc = geography.getGeometry(this).getCoordinate();

		Destination destination = new Destination(destinationCount, isAutonomous);
		destinationCount++;

		context.add(destination);
		geography.move(destination, fac.createPoint(new Coordinate(loc)));

		if (lastDestination != null)
			net.addEdge(lastDestination, destination);

		lastDestination = destination;
	}

	private GeometryFactory fac = new GeometryFactory();

	public void trackInCoverage() {
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography) context.getProjection("Geography");

		WritableGridCoverage2D coverage = (WritableGridCoverage2D) geography.getCoverage("My indexed coverage");

		if (coverage == null)
			return;

		Coordinate loc = geography.getGeometry(this).getCoordinate();

		double val = 0;

		DirectPosition pos = new DirectPosition2D(loc.x, loc.y);

		// Nothing to do if agent position not over coverage, and would throw a
		// PointOutsideCoverageException if accessed
		if (!coverage.getEnvelope2D().contains(pos))
			return;

		double[] gridVal = null;
		gridVal = coverage.evaluate(pos, gridVal);

		int max = 6;

		if (gridVal != null) {
			val = gridVal[0] + 1;
		}
		if (val > max)
			val = max;

		coverage.setValue(pos, val);
	}

	private void randomMove() {
		Context context = ContextUtils.getContext(this);
		Geography<DeliveryAgent> geography = (Geography) context.getProjection("Geography");

		geography.moveByDisplacement(this, RandomHelper.nextDoubleFromTo(-0.05, 0.05),
				RandomHelper.nextDoubleFromTo(-0.05, 0.05));
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}