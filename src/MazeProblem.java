

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Takes input as string array . Each string represents one line of maze.
 * # - blocked cell
 * '' - empty cell means flow can move through it.
 * s- start point
 * d - destination
 * @author pravin kumar
 *
 *output -
 * 1) distance from source to destination
 * 2) path (coordinates ) from source to destination.
 * 3) resultant 2-d char array where cells in shortest path
 *   represented by G and other cells as R
 */
public class MazeProblem {

	public static String[] input1 = { "#######s############",
		                              "#       # #",
			                          "# #######  ###  ## #",
			                          "# ## ####  ###  ## #",
			                          "# ## ####  ###  ## #",
			                          "#            #",
			                          "###### ##### ## ####",
			                          "#     #### # ",
			                          "###### #####       #",
			                          "##################d#" };
	

	public static String[] input3 = { "#s######",
		                              "# ######",
			                          "#  #####",
			                          "##   d##" };

	public MazeProblem() {
	}

	public static void main(String[] args) {
		MazeProblem mp = new MazeProblem();
		mazecell destination = mp.findShortestPath(input1);
		if (destination == null) {
			System.out.println("No route found");
		} else {
			System.out.println("\n Destination is found at  "
					+ destination.tostring() + " distance : "
					+ destination.getDistance());
			System.out.println("\n");
			mp.traceRoutefromSourceToDestination(destination,input1);
		}
	}


	private char[][] traceRouteas2DArray(String[] param) {
		int rowSize = param.length;
		int colSize = param[0].length();
		char[][] finalStore = new char[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				finalStore[i][j] = 'R';
			}
		}
		return finalStore;
	}
	
	public void traceRoutefromSourceToDestination(mazecell dest,String[] param) {
		char[][] result = traceRouteas2DArray(param);
		mazecell cur = dest;
		Stack<mazecell> stk = new Stack<>();
		while (cur == null || !cur.isSource()) {
			stk.push(cur);
			result[cur.xcord][cur.ycord] = 'G';
			cur = cur.getParent();
		}
		stk.push(cur);
		result[cur.xcord][cur.ycord] = 'G';
		System.out.println("\n Path from source to destination is - ");
		while (!stk.isEmpty()) {
			mazecell tmp = stk.pop();
			if (tmp.getParent() != null && tmp.xcord != tmp.getParent().xcord) {
				System.out.println();
			}
			System.out.print(tmp.tostring());
		}
		System.out.println("\n Resultant char array is - ");
		System.out.println("\n");
		printarray(result);
	}

	public mazecell findShortestPath(String[] param) {
		int rowSize = param.length;
		int colSize = param[0].length();
		mazecell[][] store = new mazecell[rowSize][colSize];
		mazecell startCell = findEntryOfMaze(param, store);
		if (startCell != null) {
			System.out.println(" start cell is = " + startCell.tostring());
		} else {
			System.out.println("Start point is not found so cant be solved");
			return null;
		}
		Queue<mazecell> listing = new LinkedList<>();
		startCell.distance = 0;
		listing.add(startCell);
		while (!listing.isEmpty()) {
			mazecell cur = listing.poll();
			if (cur.isDestination()) {
				return cur;
			} else {
				findAllPathFromAParticularCell(rowSize, colSize, cur, listing, store);
			}
		}
		System.out.println("Destination is not found ");
		return null;
	}
	
	/**
	 * Find all neighbors of the cell which can be part of valid path 
	 * 
	 *                   ^
	 *                   |
	 *              <-  obj  ->
	 *                   |
	 *                   v
	 *                   
	 *    top   =  (x-1,y)
	 *    left  =  (x,y-1)
	 *    down  =  (x+1,y)
	 *    right =  (x,y+1)
	 *    
	 */
	
	private void findAllPathFromAParticularCell(int row, int col, mazecell cell,
			Queue<mazecell> q, mazecell[][] store) {
		int xcord = cell.xcord;
		int ycord = cell.ycord;
		int distance = cell.getDistance() + 1;
		if (xcord <= 0) {
			// Cell top is not possible
		} else {
			checkAndAddCell(q, store[xcord - 1][ycord],cell, distance);
		}
		if (ycord <= 0) {
			// Cell left is not possible
		} else {
			checkAndAddCell(q, store[xcord][ycord - 1],cell, distance);
		}
		if (xcord >= row-1) {
			// Cell down is not possible
		} else {
			checkAndAddCell(q, store[xcord + 1][ycord],cell, distance);
		}
		if (ycord >= col-1) {
			// Cell right is not possible
		} else {
			checkAndAddCell(q, store[xcord][ycord + 1],cell, distance);
		}
	}

	private void checkAndAddCell(Queue<mazecell> q, mazecell cell,mazecell parent,int distance) {
		if (!cell.visited && cell.isPath()) {
			q.add(cell);
			cell.setVisited(true);
			cell.setDistance(distance);
			cell.setParent(parent);
		}
	}
	/**
	 * Creating a two dimensional array out of input string of maze.
	 * Each string of string array represents one row of the maze.
	 * Input characters of string will be treated like -
	 *    #  ->  blocked cell     -> converted as 'B' which means blocked 
	 *    s  ->  starting cell    -> converted as 'S' which means Start point 
	 *    d  ->  destination cell -> converted as 'D' which means end point 
	 *   ' ' ->  empty cell       -> converted as 'P' which means this cell
	 *                               can be part of path 
	 *   if string length is less than length of first string remaining places
	 *   will be filled with 'B'
	 * create cells here only
	 * @param param
	 */
	private mazecell findEntryOfMaze(String[] param,mazecell[][] store) {
		int col = param[0].length();
		mazecell startCell = null;
		int count = 0;
		for (String str : param) {
			char[] temp = str.toCharArray();
			int sizeofrow = temp.length;
			for (int i = 0; i < sizeofrow; i++) {
				mazecell local = new mazecell(count, i, 0);
				if (temp[i] == '#') {
					local.setType('B');
					store[count][i] = local;

				}
				else if (temp[i] == ' ') {
					local.setType('W');
					store[count][i] = local;

				}
				else if (temp[i] == 's') {
					local.setType('S');
					store[count][i] = local;
					startCell = local;

				}
				else if (temp[i] == 'd') {
					local.setType('D');
					store[count][i] = local;
				}
			}
			for (int j = sizeofrow; j < col; j++) {
				mazecell local = new mazecell(count, j, 0);
				local.setType('B');
				store[count][j] = local;
			}
			count = count + 1;
		}
		return startCell;
	}

	private void printarray(char[][] param) {
		for (int i = 0; i < param.length; i++) {
			for (int j = 0; j < param[0].length; j++) {
				System.out.print(param[i][j] + " |");
			}
			System.out.println("\n");
		}

	}
	
	private void printmazecellarray(mazecell[][] param) {
		for (int i = 0; i < param.length; i++) {
			for (int j = 0; j < param[0].length; j++) {
				System.out.print(param[i][j].getType() + " |");
			}
			System.out.println("\n");
		}

	}
}

class mazecell {
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	mazecell parent;
	int xcord;
	int ycord;
	int distance;
	char type;
	boolean visited;


	public mazecell(int xcord, int ycord, int distance) {
		this.xcord = xcord;
		this.ycord = ycord;
		this.distance = distance;
		visited = false;
	}

	public boolean isPath() {
		return type=='W' || type=='D';
	}
	public boolean isDestination() {
		return type=='D';
	}
	public boolean isSource() {
		return type=='S';
	}
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	public mazecell getParent() {
		return parent;
	}

	public void setParent(mazecell parent) {
		this.parent = parent;
	}
	public String tostring() {
		StringBuilder val = new StringBuilder();
		val.append("( "+this.xcord+ " , "+this.ycord + ")");
		return val.toString();
	}
}
