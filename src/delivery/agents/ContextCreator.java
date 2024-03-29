package delivery.agents;

import java.awt.Color;

import org.geotools.coverage.Category;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.util.NumberRange;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.context.space.gis.GeographyFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.GeographyParameters;
import repast.simphony.space.gis.RepastCoverageFactory;
import repast.simphony.space.gis.WritableGridCoverage2D;
import repast.simphony.space.graph.Network;

/**
 * 
 * @author Anjolaoluwa Adeuyi Joshua
 *
 */
public class ContextCreator implements ContextBuilder {

	public Context build(Context context) {

		GeographyParameters geoParams = new GeographyParameters();
		Geography geography = GeographyFactoryFinder.createGeographyFactory(null).createGeography("Geography", context,
				geoParams);

		GeometryFactory fac = new GeometryFactory();

		NetworkBuilder<?> netBuilder = new NetworkBuilder<Object>("Network", context, true);
		Network net = netBuilder.buildNetwork();

		DeliveryAgent agent = new DeliveryAgent("Traveler");
		context.add(agent);

		Point geom = fac.createPoint(new Coordinate(-88.8570, 41.7432));
		geography.move(agent, geom);

		ReferencedEnvelope env = new ReferencedEnvelope(-89.4, -87.7236, 41.50, 42.1681, DefaultGeographicCRS.WGS84);

		int maxColorIndex = 10;
		Color[] whiteRedColorScale = new Color[maxColorIndex];

		for (int i = 0; i < whiteRedColorScale.length; i++) {
			int blueGreen = (255 / maxColorIndex * (maxColorIndex - i));
			whiteRedColorScale[i] = new Color(255, blueGreen, blueGreen);
		}

		Category[] categories = new Category[] { new Category("No data", new Color(0, 0, 0, 0), 0),
				new Category("Level", whiteRedColorScale, NumberRange.create(1, maxColorIndex)) };

		WritableGridCoverage2D coverage2 = RepastCoverageFactory.createWritableByteIndexedCoverage("My data indexed",
				20, 20, env, categories, null, 0);

		geography.addCoverage("My indexed coverage", coverage2);

		return context;
	}
}
