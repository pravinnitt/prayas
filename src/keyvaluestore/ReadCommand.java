package keyvaluestore;

public class ReadCommand implements Commands {

	String name;
	String field;

	public ReadCommand(String name, String field) {
		super();
		this.name = name;
		this.field = field;
	}

	@Override
	public void executeQuery() {
		if (KeyValueStore.inMemory.containsKey(field)) {
			System.out.println(" read called"
					+ KeyValueStore.inMemory.get(field));
		} else {
			System.out.println(" no field to read");
		}
	}

	@Override
	public String getName() {
		return name;
	}
}