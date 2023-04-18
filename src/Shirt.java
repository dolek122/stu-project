
public class Shirt extends Product {
	String color;
	
	public Shirt(double weight, double price, String color) {
		super(weight, price);
		this.color = color;
	}
	
	public Shirt(int ID, double weight, double price, String color) {
		super(weight, price);
		this.ID = ID;
		nextID = ID + 1;
		this.color = color;
	}
	
	public String toString() {
		return "ID: " + ID + " | Koszula | " + this.weight + " kg | " + this.price + " PLN | " + this.color;
	}
}
