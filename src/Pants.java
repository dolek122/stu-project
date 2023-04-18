
public class Pants extends Product {
	String fabric;
	
	public Pants(double weight, double price, String fabric) {
		super(weight, price);
		this.fabric = fabric;
	}
	
	public Pants(int ID, double weight, double price, String fabric) {
		super(weight, price);
		this.ID = ID;
		nextID = ID + 1;
		this.fabric = fabric;
	}
	
	public String toString() {
		return "ID: " + ID + " | Spodnie | " + this.weight + " kg | " + this.price + " PLN | " + this.fabric;
	}
}
