package keyvaluestore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * implement a key value transaction store which supports transactions. A set of transaction
 * to be read and implemented.
 *     insert(A,B)
 *     put(A,B)
 *     Update(A,B)
 *     Delete(A)
 *     read(A)
 *     get
 *     BeginTransaction
 *     Commit / RollBack
 * 
 * It should support nested Transaction 
 * @author i079250
 *
 */
public class KeyValueStore {

	public static String path1 = System.getProperty("user.dir")+"/src/keyvaluestore/transaction.txt";
	private static int runningTransaction = 0;
	private static LinkedList<Commands> transactions = new LinkedList<>();
	public static HashMap<String, Integer> inMemory = new HashMap<>();
	public KeyValueStore() {
	}

	public static void main(String[] args) {
		try {
			KeyValueStore kvl = new KeyValueStore();
			kvl.parseInstructions();
			System.out.println("After Execution local db is = " + inMemory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parse the transaction input one by one.
	 * 
	 * @throws IOException
	 */
	private void parseInstructions() throws IOException {
		Stream<String> stream = Files.lines(Paths.get(path1));
		stream.filter(transac -> !transac.isEmpty()).forEach(
				transac -> constructTransaction(transac));
		stream.close();
	}

	/**
	 * Construct transaction object for each line / instruction
	 * 
	 * @param transac
	 */
	public void constructTransaction(String transac) {
		Commands cmd;
		System.out.println("transaction = " + transac);
		if (transac.startsWith("Begin")) {
			cmd = new BeginCommand(Commands.BeginComand);
			transactions.add(cmd);
			runningTransaction++;
			return;
		}
		if (runningTransaction < 1) {
			return;
		}
		String[] tokens = transac.split(" ");
		switch (tokens[0]) {
		case "Insert":
			cmd = new InsertCommand(Commands.InsertCommand, tokens[1],
					Integer.parseInt(tokens[2]));
			transactions.add(cmd);
			break;
		case "Update":
			cmd = new UpdateCommand(Commands.InsertCommand, tokens[1],
					Integer.parseInt(tokens[2]));
			transactions.add(cmd);
			break;
		case "Delete":
			cmd = new DeleteCommand(Commands.InsertCommand, tokens[1]);
			transactions.add(cmd);
			break;
		case "Read":
			cmd = new ReadCommand(Commands.InsertCommand, tokens[1]);
			transactions.add(cmd);
			break;
		case "Commit":
			handleCommit();
			runningTransaction--;
			break;
		case "RollBack":
			handleRollBack();
			runningTransaction--;
			break;
		}
	}

	private void handleCommit() {
		Stack<Commands> stk = new Stack<>();
		System.out.println(transactions);
		Commands temp = transactions.removeLast();
		while (!temp.getName().equals(Commands.BeginComand)) {
			stk.push(temp);
			temp = transactions.removeLast();
		}
		stk.push(temp);
		System.out.println("Stack - "+stk.size());
		while(!stk.empty()){
			Commands cur = stk.pop();
			System.out.println("cur ---- "+ cur);
			cur.executeQuery();
		}
		System.out.println("Transaction Commited");
	}
	private void handleRollBack(){
		while (!transactions.getLast().getName().equals(Commands.BeginComand)) {
			transactions.removeLast();
		}
		transactions.removeLast();
		System.out.println("Transaction rollbacked");
	}
}
