package keyvaluestore;


public class UpdateCommand implements Commands {
	String name;
	String field;
	int value;

	public UpdateCommand(String name, String field, int value) {
		super();
		this.name = name;
		this.field = field;
		this.value = value;
	}

	@Override
	public void executeQuery() {
		if (KeyValueStore.inMemory.containsKey(field)) {
			KeyValueStore.inMemory.put(field, value);
			System.out.println(" update called");
		} else {
			System.out.println(" no field to update");
		}
	}

	@Override
	public String getName() {
		return name;
	}

}
