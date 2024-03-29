package delivery.styles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import delivery.agents.Destination;
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
public class DestinationStyle implements MarkStyle<Destination> {

	private Map<String, WWTexture> textureMap;

	public DestinationStyle() {

		textureMap = new HashMap<String, WWTexture>();

		BufferedImage image = PatternFactory.createPattern(PatternFactory.PATTERN_CIRCLE, new Dimension(50, 50), 0.7f,
				Color.BLUE);

		textureMap.put("blue circle", new BasicWWTexture(image));

		image = PatternFactory.createPattern(PatternFactory.PATTERN_CIRCLE, new Dimension(50, 50), 0.7f, Color.YELLOW);

		textureMap.put("yellow circle", new BasicWWTexture(image));
	}

	@Override
	public PlaceMark getPlaceMark(Destination agent, PlaceMark mark) {

		if (mark == null)
			mark = new PlaceMark();

		mark.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
		mark.setLineEnabled(false);

		return mark;
	}

	@Override
	public double getElevation(Destination agent) {
		return 0;
	}

	@Override
	public WWTexture getTexture(Destination agent, WWTexture texture) {

		return textureMap.get("blue circle");
	}

	@Override
	public double getScale(Destination agent) {
		return 0.2;
	}

	@Override
	public double getHeading(Destination agent) {
		return 0;
	}

	@Override
	public String getLabel(Destination agent) {
		return null;
	}

	@Override
	public Color getLabelColor(Destination agent) {
		return null;
	}

	@Override
	public Offset getLabelOffset(Destination agent) {
		return null;
	}

	@Override
	public Font getLabelFont(Destination obj) {
		return null;
	}

	@Override
	public double getLineWidth(Destination agent) {
		return 0;
	}

	@Override
	public Material getLineMaterial(Destination obj, Material lineMaterial) {
		return null;
	}

	@Override
	public Offset getIconOffset(Destination obj) {
		return Offset.CENTER;
	}
}