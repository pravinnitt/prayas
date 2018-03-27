package Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra
 {

	Graph gp;

	public Dijkstra(Graph grph) {
		this.gp = grph;
	}

	public static void main(String[] args) {
		System.out.println("VVVVVV");
		Graph gp = new Graph();
		createWeightedGraph(gp);
		Dijkstra dtra = new Dijkstra(gp);
		dtra.findShortestPath(gp.nodes.get(0));
	}

	public void findShortestPath(Vertex src) {
		Comparator<Vertex> distComprtr = new VertexDistanceComparator();
		PriorityQueue<Vertex> pq = new PriorityQueue<>(gp.nodes.size(),
				distComprtr);
		HashMap<Vertex, Integer> distance = new HashMap<>();
		HashMap<Vertex, Vertex> path = new HashMap<>();

		HashMap<Vertex, String> absolutePath = new HashMap<>();
		for (Vertex node : gp.nodes) {
			distance.put(node, -1);
		}
		distance.put(src, 0);
		src.setDistance(0);
		pq.add(src);
		while (!pq.isEmpty()) {
			Vertex temp = pq.poll();

			for (Vertex con : temp.getConnection()) {
				// Find weight between temp and con vertex
				int newWt = distance.get(temp) + wtOfEdge(temp, con);
				int oldwt = distance.get(con);
				if (oldwt == -1) {
					distance.put(con, newWt);
					con.setDistance(newWt);
					pq.add(con);
				}
				if (oldwt > newWt) {
					distance.put(con, newWt);
					con.setDistance(newWt);
				}
				path.put(con, temp);
			}
		}
		absolutePath = calculatePath(src, path);
		printSourceDestination(src, distance,absolutePath);
	}

	public HashMap<Vertex, String> calculatePath(Vertex src, HashMap<Vertex, Vertex> path) {
		HashMap<Vertex, String> sequence = new HashMap<>();
		for (Vertex con : path.keySet()) {
			sequence.put(con,
					findAbsolutePath(src, con, path, new StringBuilder()));
//			System.out.println("path to " + con.getName() + " from "
//					+ findAbsolutePath(src, con, path, new StringBuilder()));
		}
		return sequence;
	}

	public String findAbsolutePath(Vertex src, Vertex node,
			HashMap<Vertex, Vertex> path, StringBuilder msg) {
		if (src.equals(node)) {
			StringBuilder last = new StringBuilder(node.getName()).append(" -> ").append(msg);
			return last.toString();
		}
		if(msg.length()==0){
			return findAbsolutePath(src, path.get(node), path, new StringBuilder(
					node.getName()));
		}
		else {
			return findAbsolutePath(src, path.get(node), path, new StringBuilder(
					node.getName()).append(" -> ").append(msg));
		}
	}

	public void printSourceDestination(Vertex src, HashMap<Vertex, Integer> dist, HashMap<Vertex, String> route) {
		for (Vertex con : dist.keySet()) {
			System.out.println("Distance from " + src.getName() + " to "
					+ con.getName() + " = " + dist.get(con) + " And Path is ---- " + route.get(con));
		}

	}

	public int wtOfEdge(Vertex v1, Vertex v2) {
		int val = 0;
		for (Edge edg : gp.edges) {
			if (edg.getStart().equals(v1) && edg.getEnd().equals(v2)) {
				return edg.getWeight();
			}
		}
		return val;
	}

	private static void createWeightedGraph(Graph gp) {
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		gp.nodes.add(a);
		gp.nodes.add(b);
		gp.nodes.add(c);
		gp.nodes.add(d);
		gp.nodes.add(e);
		gp.nodes.add(f);
		Edge ab = new Edge(a, b, 4);
		Edge ac = new Edge(a, c, 2);
		Edge bc = new Edge(b, c, 1);
		Edge bd = new Edge(b, d, 5);
		Edge cd = new Edge(c, d, 8);
		Edge ce = new Edge(c, e, 10);
		Edge de = new Edge(d, e, 2);
		Edge df = new Edge(d, f, 6);
		Edge ef = new Edge(e, f, 3);

		gp.edges.add(ab);
		gp.edges.add(ac);
		gp.edges.add(bc);
		gp.edges.add(bd);
		gp.edges.add(cd);
		gp.edges.add(ce);
		gp.edges.add(de);
		gp.edges.add(df);
		gp.edges.add(ef);
	}
}