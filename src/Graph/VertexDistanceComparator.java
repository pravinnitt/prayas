package Graph;

import java.util.Comparator;


public class VertexDistanceComparator implements Comparator<Vertex>
 {

	@Override
	public int compare(Vertex x, Vertex y) {
		// TODO Auto-generated method stub
		if (x.getDistance() < y.getDistance()) {
			return -1;
		}
		if (x.getDistance() > y.getDistance()) {
			return 1;
		}
		return 0;
	}

}
