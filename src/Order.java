import java.util.ArrayList;

public class Order {
	Client client;
	ArrayList<Product> products;
	
	public Order(Client client, ArrayList<Product> products) {
		this.client = client;
		this.products = products;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID klienta: " + client.ID + "\n");
		sb.append("Lista produktow\n");
		
		for(Product product : products) {
			sb.append(product.toString() + "\n");
		}
		
		sb.append("\n");
		
		return sb.toString();
	}
}
