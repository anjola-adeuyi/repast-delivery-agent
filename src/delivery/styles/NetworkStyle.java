package delivery.styles;

import java.awt.Color;

import gov.nasa.worldwind.render.SurfacePolyline;
import repast.simphony.space.graph.RepastEdge;
import repast.simphony.visualization.gis3D.style.NetworkStyleGIS;

/**
 * @author Anjolaoluwa Adeuyi Joshua
 *
 */
public class NetworkStyle implements NetworkStyleGIS {

	@Override
	public SurfacePolyline getSurfaceShape(RepastEdge edge, SurfacePolyline shape) {
		return new SurfacePolyline();
	}

	@Override
	public Color getLineColor(RepastEdge edge) {
		return Color.GREEN;
	}

	@Override
	public double getLineOpacity(RepastEdge edge) {
		return 1.0;
	}

	@Override
	public double getLineWidth(RepastEdge edge) {
		return 5.0;
	}
}