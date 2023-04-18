
public class Shoes extends Product {
	int size;
	
	public Shoes(double weight, double price, int size) {
		super(weight, price);
		this.size = size;
	}
	
	public Shoes(int ID, double weight, double price, int size) {
		super(weight, price);
		this.ID = ID;
		nextID = ID + 1;
		this.size = size;
	}
	
	public String toString() {
		return "ID: " + ID + " | Buty | " + this.weight + " kg | " + this.price + " PLN | " + this.size;
	}
}
