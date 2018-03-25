package keyvaluestore;

public interface Commands {

	public static String InsertCommand = "Insert";
	public static String DeleteCommand = "Delete";
	public static String ReadCommand = "Read";
	public static String UpdateComand = "Update";
	public static String BeginComand = "BeginTransaction";

	void executeQuery();

	String getName();
}
