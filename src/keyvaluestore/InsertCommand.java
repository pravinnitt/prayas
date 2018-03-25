package keyvaluestore;

public class InsertCommand implements Commands {

	String name;
	String field;
	int value;

	public InsertCommand(String name, String field, int value) {
		super();
		this.name = name;
		this.field = field;
		this.value = value;
	}

	@Override
	public void executeQuery() {
		KeyValueStore.inMemory.put(field, value);
		System.out.println(" insert called");
	}

	@Override
	public String getName() {
		return name;
	}

}
