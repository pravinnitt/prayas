package Graph;

public class Edge implements Comparable<Edge>{

	private Vertex start;
	private Vertex end;
	private int weight;

	public Edge(Vertex a, Vertex b) {
		this.start = a;
		this.end = b;
		a.addConnection(b);
		// b.addConnection(a);
		a.addEdge(this);
		b.addEdge(this);
		b.addIncomingFlow(a);
	}

	public Edge(Vertex a, Vertex b, int wt) {
		this.start = a;
		this.end = b;
		this.weight = wt;
		a.addConnection(b);
		b.addIncomingFlow(a);
		a.addEdge(this);
		b.addEdge(this);
	}

	public Vertex getEnd() {
		return end;
	}

	public void setEnd(Vertex end) {
		this.end = end;
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
	@Override
	public String toString(){
		StringBuilder temp = new StringBuilder(start.getName());
		temp.append(end.getName());
//		temp.append(end.getName()).append(" - ").append(weight);
//		System.out.println("Here= "+temp.toString());
		return  temp.toString();
	}

}
