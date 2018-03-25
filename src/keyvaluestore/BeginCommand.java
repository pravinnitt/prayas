package keyvaluestore;

public class BeginCommand  implements Commands {

	String name;
	public BeginCommand(String name) {
		super();
		this.name = name;
	}

	@Override
	public void executeQuery() {
		System.out.println("Transation Started");
	}

	@Override
	public String getName() {
		return name;
	}
}
