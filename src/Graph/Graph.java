package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	List<Vertex> nodes;
	List<Edge> edges;

	public Graph() {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}

	public static void main(String[] args) {
		Graph gp = new Graph();

	}

	public void printSortedEdges() {
		System.out.println("\n after sorting");
		for (Edge edg : edges) {
			System.out.println(edg.toString());
		}
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

	private static void createWeightedGraphBig(Graph gp) {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		Vertex v5 = new Vertex("E");
		Vertex v6 = new Vertex("F");
		Vertex v7 = new Vertex("G");
		Vertex v8 = new Vertex("H");
		Vertex v9 = new Vertex("I");
		gp.nodes.add(v1);
		gp.nodes.add(v2);
		gp.nodes.add(v3);
		gp.nodes.add(v4);
		gp.nodes.add(v5);
		gp.nodes.add(v6);
		gp.nodes.add(v7);
		gp.nodes.add(v8);
		gp.nodes.add(v9);
		Edge e1 = new Edge(v1, v2, 4);
		Edge e2 = new Edge(v1, v8, 8);
		Edge e3 = new Edge(v2, v3, 8);
		Edge e4 = new Edge(v2, v8, 11);
		Edge e5 = new Edge(v3, v4, 7);
		Edge e6 = new Edge(v3, v6, 4);
		Edge e7 = new Edge(v3, v9, 2);
		Edge e8 = new Edge(v4, v5, 9);
		Edge e9 = new Edge(v4, v6, 14);
		Edge e10 = new Edge(v5, v6, 10);
		Edge e11 = new Edge(v6, v7, 2);
		Edge e12 = new Edge(v7, v8, 1);
		Edge e13 = new Edge(v7, v9, 6);
		Edge e14 = new Edge(v8, v9, 7);

		gp.edges.add(e1);
		gp.edges.add(e2);
		gp.edges.add(e3);
		gp.edges.add(e4);
		gp.edges.add(e5);
		gp.edges.add(e6);
		gp.edges.add(e7);
		gp.edges.add(e8);
		gp.edges.add(e9);
		gp.edges.add(e10);
		gp.edges.add(e11);
		gp.edges.add(e12);
		gp.edges.add(e13);
		gp.edges.add(e14);
	}

	private static void callNormalFunctions(Graph gp) {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		Vertex v5 = new Vertex("E");
		Edge e1 = new Edge(v1, v2);
		Edge e2 = new Edge(v2, v4);
		Edge e3 = new Edge(v4, v3);
		Edge e4 = new Edge(v1, v5);
		gp.edges.add(e1);
		gp.edges.add(e2);
		gp.edges.add(e3);
		gp.edges.add(e4);
		gp.nodes.add(v1);
		gp.nodes.add(v2);
		gp.nodes.add(v3);
		gp.nodes.add(v4);
		gp.nodes.add(v5);
		gp.printAllVertex();
		gp.topologicalSort();
		gp.clearGraph();
		gp.topologicalSortAnother();
	}

	public void topologicalSortAnother() {
		// Create In Degree list
		HashMap<Vertex, Integer> inDegree = new HashMap<>();
		for (Vertex node : nodes) {
			inDegree.put(node, node.getComingFrom().size());
		}

		System.out.println("\n Topological order = ");
		Queue<Vertex> q1 = new LinkedList();
		for (Vertex node : nodes) {
			if (inDegree.get(node) == 0 && !node.visited) {
				System.out.print(node.getName() + " ");
				node.visited = true;
				q1.add(node);
			}
			while (!q1.isEmpty()) {
				Vertex root = q1.poll();
				for (Vertex con : root.getConnection()) {
					inDegree.put(con, inDegree.get(con) - 1);
					if (inDegree.get(con) == 0 && !con.visited) {
						System.out.print(con.getName() + " ");
						con.visited = true;
						q1.add(con);
					}
				}
			}
		}
	}

	public void topologicalSort() {
		Stack<Vertex> st = new Stack<>();
		int size = this.nodes.size();
		for (Vertex node : nodes) {
			if (!node.visited) {
				topologicalSort(node, st);
			}
		}
		System.out.println("\n Topological order = ");
		while (st.isEmpty() == false) {
			System.out.print(st.pop().getName() + " ");
		}
	}

	private void topologicalSort(Vertex node, Stack<Vertex> st) {
		node.visited = true;
		for (Vertex con : node.getConnection()) {
			if (!con.visited) {
				topologicalSort(con, st);
			}
		}
		st.push(node);
	}

	public void findAllNodeBetween(Vertex v1, Vertex v2) {

	}

	public void printAllVertex() {
		for (Vertex node : nodes) {
			System.out.print("\n  Node : " + node.getName() + " :- ");
			for (Vertex root : node.getConnection()) {
				System.out.print(" " + node.getName() + root.getName());
			}
		}
	}

	public void clearGraph() {
		for (Vertex node : nodes) {
			node.visited = false;
		}
	}

	public void bfsRecursive(Vertex v) {
		Queue<Vertex> q1 = new LinkedList();
		q1.add(v);
		v.visited = true;
		bfsRecursiveImpl(q1);
	}

	public void bfsRecursiveImpl(Queue<Vertex> q) {
		if (q.isEmpty()) {
			return;
		}
		Vertex root = q.poll();
		System.out.print(" " + root.getName());
		for (Vertex neighbour : root.getConnection()) {
			if (!neighbour.visited) {
				q.add(neighbour);
				neighbour.visited = true;
			}
		}
		bfsRecursiveImpl(q);
	}

	public void bfs(Vertex v) {
		Queue<Vertex> q1 = new LinkedList();
		v.visited = true;
		q1.add(v);
		while (!q1.isEmpty()) {
			Vertex root = q1.poll();
			System.out.print(" " + root.getName());
			for (Vertex neighbour : root.getConnection()) {
				if (!neighbour.visited) {
					q1.add(neighbour);
					neighbour.visited = true;
				}
			}
		}

	}

	public void dfs(Vertex v) {
		Stack<Vertex> st = new Stack<>();
		st.push(v);
		v.visited = true;
		while (!st.isEmpty()) {
			Vertex root = st.pop();
			if (!root.visited) {
				System.out.print(" " + root.getName());
				for (Vertex neighbour : root.getConnection()) {
					st.push(neighbour);
					neighbour.visited = true;
				}
			}
		}
	}

	public void dfsRecursive(Vertex v) {
		System.out.print(" " + v.getName());
		v.visited = true;
		for (Vertex neighbour : v.getConnection()) {
			if (!neighbour.visited) {
				dfsRecursive(neighbour);
			}
		}
	}
}
