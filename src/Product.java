
abstract class Product {
	static int nextID = 1;
	int ID;
	double weight;
	double price;
	
	public Product(double weight, double price) {
		this.ID = nextID;
		nextID++;
		this.weight = weight;
		this.price = price;
	}
	
	public abstract String toString();
}
