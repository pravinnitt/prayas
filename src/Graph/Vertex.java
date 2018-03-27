package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String name;
	private List<Edge> edges;
	private List<Vertex> connection;
	private List<Vertex> comingFrom;
	private int distance ;
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public List<Vertex> getComingFrom() {
		return comingFrom;
	}
	public void addIncomingFlow(Vertex node) {
		this.comingFrom.add(node);
	}
	public boolean visited;

	public List<Vertex> getConnection() {
		return connection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	public void addConnection(Vertex node) {
		this.connection.add(node);
	}
	public Vertex(String param) {
		this.name = param;
		edges = new ArrayList<Edge>();
		connection = new ArrayList<Vertex>();
		comingFrom = new ArrayList<Vertex>();
		visited=false;
		distance =-1;
	}
//	@Override
//	public int hashCode()
//	{
//	    return Arrays.hashCode(new Object[]{new Character(row), new Character(col)});
//	}
}
