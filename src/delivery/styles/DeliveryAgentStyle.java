package delivery.styles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

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

	public DeliveryAgentStyle() {

		textureMap = new HashMap<String, WWTexture>();

		BufferedImage image = PatternFactory.createPattern(PatternFactory.PATTERN_CIRCLE, new Dimension(50, 50), 0.7f,
				Color.BLUE);

		textureMap.put("blue circle", new BasicWWTexture(image));

		image = PatternFactory.createPattern(PatternFactory.PATTERN_CIRCLE, new Dimension(50, 50), 0.7f, Color.YELLOW);

		textureMap.put("yellow circle", new BasicWWTexture(image));
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
		if (agent.isAutonomous) {
			return textureMap.get("yellow circle");
		} else {
			return textureMap.get("yellow circle");
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