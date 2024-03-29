package delivery.styles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import delivery.agents.DeliveryAgent;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.render.BasicWWTexture;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PatternFactory;
import gov.nasa.worldwind.render.WWTexture;
import repast.simphony.visualization.gis3D.PlaceMark;
import repast.simphony.visualization.gis3D.style.MarkStyle;

/**
 * 
 * @author Anjolaoluwa Adeuyi Joshua
 *
 */
public class DeliveryAgentStyle implements MarkStyle<DeliveryAgent> {

	private Offset labelOffset;

	private Map<String, WWTexture> textureMap;

	private static final Logger logger = Logger.getLogger(DeliveryAgentStyle.class.getName());

	public DeliveryAgentStyle() {
		textureMap = new HashMap<String, WWTexture>();

		BufferedImage image = PatternFactory.createPattern(PatternFactory.PATTERN_CIRCLE, new Dimension(50, 50), 0.7f,
				Color.BLUE);

		String autonomousFileName = "icons/smart-car.png";
		String humanOperatedFileName = "icons/delivery-man.png";

		URL localAutonomousUrl = WorldWind.getDataFileStore().requestFile(autonomousFileName);
		logger.info(localAutonomousUrl);
		if (localAutonomousUrl != null) {
			textureMap.put("autonomous", new BasicWWTexture(localAutonomousUrl, false));
		} else {
			textureMap.put("autonomous", new BasicWWTexture(image));

			System.err.println("Error loading texture for autonomous" + ": File not found - " + localAutonomousUrl);
		}

		URL localHumanOperatedUrl = WorldWind.getDataFileStore().requestFile(humanOperatedFileName);
		logger.info(localHumanOperatedUrl);
		if (localHumanOperatedUrl != null) {
			textureMap.put("human-operated", new BasicWWTexture(localHumanOperatedUrl, false));
		} else {
			image = PatternFactory.createPattern(PatternFactory.PATTERN_CIRCLE, new Dimension(50, 50), 0.7f,
					Color.YELLOW);
			textureMap.put("human-operated", new BasicWWTexture(image));

			System.err.println(
					"Error loading texture for human-operated" + ": File not found - " + localHumanOperatedUrl);
		}
	}

	@Override
	public PlaceMark getPlaceMark(DeliveryAgent agent, PlaceMark mark) {

		// PlaceMark is null on first call.
		if (mark == null)
			mark = new PlaceMark();

		mark.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
		mark.setLineEnabled(false);

		return mark;
	}

	@Override
	public double getElevation(DeliveryAgent agent) {
		return 0;
	}

	@Override
	public WWTexture getTexture(DeliveryAgent agent, WWTexture texture) {
//		return textureMap.get("autonomous");
		if (agent.isAutonomous) {
			return textureMap.get("autonomous");
		} else {
			return textureMap.get("human-operated");
		}
	}

	@Override
	public double getScale(DeliveryAgent agent) {
		return 0.5;
	}

	@Override
	public double getHeading(DeliveryAgent agent) {
		return 0;
	}

	@Override
	public String getLabel(DeliveryAgent agent) {
		return null;
	}

	@Override
	public Color getLabelColor(DeliveryAgent agent) {
		return null;
	}

	@Override
	public Offset getLabelOffset(DeliveryAgent agent) {
		return null;
	}

	@Override
	public Font getLabelFont(DeliveryAgent obj) {
		return null;
	}

	@Override
	public double getLineWidth(DeliveryAgent agent) {
		return 0;
	}

	@Override
	public Material getLineMaterial(DeliveryAgent obj, Material lineMaterial) {
		return null;
	}

	@Override
	public Offset getIconOffset(DeliveryAgent obj) {
		return Offset.CENTER;
	}
}