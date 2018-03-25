package keyvaluestore;

public class DeleteCommand implements Commands {

	String name;
	String field;

	public DeleteCommand(String name, String field) {
		super();
		this.name = name;
		this.field = field;
	}

	@Override
	public void executeQuery() {
		if (KeyValueStore.inMemory.containsKey(field)) {
			KeyValueStore.inMemory.remove(field);
			System.out.println(" remove called");
		} else {
			System.out.println(" no field to remove");
		}
	}

	@Override
	public String getName() {
		return name;
	}
}
