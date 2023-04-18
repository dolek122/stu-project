import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static void saveClients(ArrayList<Client> clients) {
		try {
		      FileWriter writer = new FileWriter("clients.txt");
		      
		      for(Client client : clients) {
		    	  writer.write(client.ID + "," + client.name + "\n");	
		      }
		      
		      writer.close();
		    } catch (IOException e) { }
	}
	
	private static void saveProducts(ArrayList<Product> products) {
		try {
		      FileWriter writer = new FileWriter("products.txt");
		      
		      for(Product product : products) {
		    	  if(product.getClass() == Shirt.class) {
		    		  Shirt shirt = (Shirt)product;
		    		  writer.write("S" + "," + shirt.ID + "," + shirt.weight + "," + shirt.price + "," + shirt.color + "\n");
		    	  }
		    	  else if(product.getClass() == Pants.class) {
		    		  Pants pants = (Pants)product;
		    		  writer.write("P" + "," + pants.ID + "," + pants.weight + "," + pants.price + "," + pants.fabric + "\n");
		    	  }
		    	  else if(product.getClass() == Shoes.class) {
		    		  Shoes shoes = (Shoes)product;
		    		  writer.write("H" + "," + shoes.ID + "," + shoes.weight + "," + shoes.price + "," + shoes.size + "\n");
		    	  }
		      }
		      
		      writer.close();
		    } catch (IOException e) { }
	}
	
	private static void saveOrders(ArrayList<Order> orders) {
		try {
		      FileWriter writer = new FileWriter("orders.txt");
		      
		      for(Order order : orders) {
		    	  writer.write(order.client.ID + ",");
		    	  
		    	  for(int i = 0; i < order.products.size(); i++) {
		    		  writer.write(order.products.get(i).ID + ",");
		    	  }
		    	  writer.write("\n");
		      }
		      
		      writer.close();
		    } catch (IOException e) { }
	}
	
	private static ArrayList<Client> loadClients() {
		ArrayList<Client> clients = new ArrayList<Client>();
		
		try {
		      File file = new File("clients.txt");
		      Scanner reader = new Scanner(file);
		      
		      while (reader.hasNextLine()) {
		        String data[] = reader.nextLine().split(",");
		        int ID = Integer.parseInt(data[0]);
		        String name = data[1];
		        clients.add(new Client(ID, name));
		      }
		      
		      reader.close();
		    } catch (FileNotFoundException e) { }
		
		return clients;
	}
	
	private static ArrayList<Product> loadProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
		      File file = new File("products.txt");
		      Scanner reader = new Scanner(file);
		      
		      while (reader.hasNextLine()) {
		        String data[] = reader.nextLine().split(",");
		        String type = data[0];
		        int ID = Integer.parseInt(data[1]);
		        double weight = Double.parseDouble(data[2]);
		        double price = Double.parseDouble(data[3]);
		        
		        if(type.equals("S")) {
		        	String color = data[4];
		        	products.add(new Shirt(ID, weight, price, color));
		        }
		        else if(type.equals("P")) {
		        	String fabric = data[4];
		        	products.add(new Pants(ID, weight, price, fabric));
		        }
		        else if(type.equals("H")) {
		        	int size = Integer.parseInt(data[4]);
		        	products.add(new Shoes(ID, weight, price, size));
		        }
		      }
		      
		      reader.close();
		    } catch (FileNotFoundException e) { }
		
		return products;
	}
	
	private static ArrayList<Order> loadOrders(ArrayList<Client> clients, ArrayList<Product> products) {
		ArrayList<Order> orders = new ArrayList<Order>();
		
		try {
		      File file = new File("orders.txt");
		      Scanner reader = new Scanner(file);
		      
		      while (reader.hasNextLine()) {
		    	Client orderClient = null;
			    ArrayList<Product> orderProducts = new ArrayList<Product>();
			        
		        String data[] = reader.nextLine().split(",");
		        
		        int clientID = Integer.parseInt(data[0]);
		        
		        for(Client client : clients) {
		        	if(client.ID == clientID) {
		        		orderClient = client;
		        		break;
		        	}
		        }
		        
		        for(int i = 1; i < data.length; i++) {
		        	for(Product product : products) {
		        		if(product.ID == Integer.parseInt(data[i])) {
		        			orderProducts.add(product);
		        		}
		        	}
		        }
		        
		        orders.add(new Order(orderClient, orderProducts));
		      }
		      
		      reader.close();
		    } catch (FileNotFoundException e) { }
		
		return orders;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Client> clients = loadClients();
		ArrayList<Product> products = loadProducts();
		ArrayList<Order> orders = loadOrders(clients, products);
		
		while(true) {
			System.out.println("1. Nowy klient.");
			System.out.println("2. Usun klienta.");
			System.out.println("3. Wyszukaj klienta.");
			System.out.println("4. Nowy produkt.");
			System.out.println("5. Usun produkt.");
			System.out.println("6. Wyszukaj produkt.");
			System.out.println("7. Przegladaj produkty.");
			System.out.println("8. Nowe zamowienie.");
			System.out.println("9. Przegladaj zamowienia.");
			System.out.println("10. Wyjscie.");
			
			int choice = Integer.parseInt(scan.nextLine());
			
			switch(choice) {
				case 1: { //nowy klient
					System.out.println("Dodawanie nowego klienta");
					System.out.println("Imie i nazwisko: ");
					String name = scan.nextLine();
					clients.add(new Client(name));
					System.out.println("Pomyslnie dodano nowego klienta o ID " + (Client.nextID - 1));
					scan.nextLine();
					break;
				}
				case 2: { //usuwanie klienta
					System.out.println("Usuwanie klienta");
					System.out.print("ID: ");
					int ID = Integer.parseInt(scan.nextLine());
					
					boolean clientFound = false;
					for(Client client : clients) {
						if(client.ID == ID) {
							clientFound = true;
							clients.remove(client);
							System.out.println("Pomyslnie usunieto klienta o ID " + ID);
							break;
						}
					}
					
					if(!clientFound) {
						System.out.println("Nie odnaleziono klienta o podanym ID. Aby kontynuowac nacisnij ENTER.");
						scan.nextLine();
					}
					
					break;
				}
				case 3: { //wyszukiwanie klienta
					System.out.println("Wyszukiwanie klienta");
					System.out.print("ID: ");
					int ID = Integer.parseInt(scan.nextLine());
					
					boolean clientFound = false;
					for(Client client : clients) {
						if(client.ID == ID) {
							clientFound = true;
							System.out.println("Imie i nazwisko: " + client.name);
							scan.nextLine();
							break;
						}
					}
					
					if(!clientFound) {
						System.out.println("Nie odnaleziono klienta o podanym ID. Aby kontynuowac nacisnij ENTER.");
						scan.nextLine();
					}

					break;
				}
				case 4: { //nowy produkt
					System.out.println("Dodawanie nowego produktu");
					System.out.println("1. Koszula");
					System.out.println("2. Spodnie");
					System.out.println("3. Buty");
					
					int type = Integer.parseInt(scan.nextLine());
					
					switch(type) {
						case 1: //koszula
						{
							System.out.print("Waga: ");
							double weight = Double.parseDouble(scan.nextLine());
							System.out.print("Cena: ");
							double price = Double.parseDouble(scan.nextLine());
							System.out.print("Kolor: ");
							String color = scan.next();
							
							products.add(new Shirt(weight, price, color));
							
							System.out.println("Pomyslnie dodano nowy produkt o ID " + (Product.nextID - 1) + ". Aby kontynuowac nacisnij ENTER.");
							scan.nextLine();
							break;
						}
						case 2: //spodnie
						{
							System.out.print("Waga: ");
							double weight = Double.parseDouble(scan.nextLine());
							System.out.print("Cena: ");
							double price = Double.parseDouble(scan.nextLine());
							System.out.print("Material: ");
							String fabric = scan.next();
							
							products.add(new Pants(weight, price, fabric));
							
							System.out.println("Pomyslnie dodano nowy produkt o ID " + (Product.nextID - 1) + ". Aby kontynuowac nacisnij ENTER.");
							scan.nextLine();
							break;
						}
						case 3: //buty
						{
							System.out.print("Waga: ");
							double weight = Double.parseDouble(scan.nextLine());
							System.out.print("Cena: ");
							double price = Double.parseDouble(scan.nextLine());
							System.out.print("Rozmiar: ");
							int size = Integer.parseInt(scan.nextLine());
							
							products.add(new Shoes(weight, price, size));
							
							System.out.println("Pomyslnie dodano nowy produkt o ID " + (Product.nextID - 1) + ". Aby kontynuowac nacisnij ENTER.");
							scan.nextLine();
							break;
						}
						default:
						{
							break;
						}
					}
					
					break;
				}
				case 5: { //usuwanie produktu
					System.out.println("Usuwanie produktu");
					System.out.print("ID: ");
					int ID = Integer.parseInt(scan.nextLine());
					
					for(Product product : products) {
						if(product.ID == ID) {
							products.remove(product);
							System.out.println("Pomyslnie usunieto wskazany produkt. Aby kontynuowac nacisnij ENTER.");
							scan.nextLine();
							break;
						}
					}
					
					System.out.println("Nie odnaleziono produktu o podanym ID. Aby kontynuowac nacisnij ENTER.");
					scan.nextLine();
					
					break;
				}
				case 6: { //wyszukiwanie produktu
					System.out.println("Wyszukiwanie produktu");
					System.out.print("ID: ");
					int ID = Integer.parseInt(scan.nextLine());
					
					for(Product product : products) {
						if(product.ID == ID) {
							System.out.println(product.toString());
							break;
						}
					}
					
					System.out.println("Nie odnaleziono produktu o podanym ID. Aby kontynuowac nacisnij ENTER.");
					scan.nextLine();
					
					break;
				}
				case 7: { //przegladanie produktow
					System.out.println("Przegladanie produktow");
					
					for(Product product : products) {
						System.out.println(product.toString());
					}
					scan.nextLine();
					break;
				}
				case 8: { //nowe zamowienie
					System.out.println("Dodawanie nowego zamowienia");
					System.out.print("ID klienta: ");
					int clientID = Integer.parseInt(scan.nextLine());
					Client orderClient = null;
					ArrayList<Product> orderProducts = new ArrayList<Product>();
					
					boolean clientFound = false;
					for(Client client : clients) {
						if(client.ID == clientID) {
							clientFound = true;
							orderClient = client;
							break;
						}
					}
					if(!clientFound) {
						System.out.println("Nie odnaleziono klienta o podanym ID. Aby kontynuowac nacisnij ENTER.");
						scan.nextLine();
						break;
					}
					
					int productID;
					do {
						System.out.print("ID produktu: (0, aby zakonczyc)");
						productID = Integer.parseInt(scan.nextLine());
						
						if(productID == 0) {
							break;
						}
						
						boolean productFound = false;
						for(Product product : products) {
							if(product.ID == productID) {
								productFound = true;
								orderProducts.add(product);
								break;
							}
						}
						
						if(!productFound) {
							System.out.println("Nie odnaleziono produktu o podanym ID. Mozna kontynuowac dodawanie produktow.");
							break;
						}
					} while(productID != 0);
					
					orders.add(new Order(orderClient, orderProducts));
					break;
				}
				case 9: { //przegladanie zamowien
					System.out.println("Przegladanie zamowien");
					
					for(Order order : orders) {
						System.out.println(order.toString());
					}
					scan.nextLine();
					break;
				}
				case 10: {
					saveClients(clients);
					saveProducts(products);
					saveOrders(orders);
					scan.close();
					System.exit(0);
				}
				default:
				{
					break;
				}
			}
		}
	}
}
