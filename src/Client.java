
public class Client {
	static int nextID = 1;
	int ID;
	String name;
	
	public Client(String name)
	{
		this.ID = nextID;
		nextID++;
		this.name = name;
	}
	
	public Client(int ID, String name)
	{
		this.ID = ID;
		nextID = ID + 1;
		this.name = name;
	}
}
