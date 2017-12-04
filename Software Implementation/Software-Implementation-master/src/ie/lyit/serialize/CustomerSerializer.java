package ie.lyit.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ie.lyit.hotel.Customer;

public class CustomerSerializer {

	
	//File in which serialization is stored
	public final File file = new File("customers.obj");
	private ArrayList<Customer> customers;

	public CustomerSerializer() {
		customers = new ArrayList<Customer>();
	}

	//Add customer to arraylist if customer doesn't already exist
	public void add(Customer customer) {
		if (!customers.contains(customer))
			customers.add(customer);
	}

	//Remove customer from arraylist if customer exists
	public void delete(Customer customer) {
		if (customers.contains(customer))
			customers.remove(customer);
	}

	//Iterates through customers arraylist using java 8 lambda expression and prints their details
	public void view() {
		customers.forEach(c -> System.out.println(c));
	}

	public ArrayList<Customer> list() {
		return customers;
	}

	//Serializes arraylist and stores data in File
	public void serialize() {
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) { // flow of data outwards 
			oos.writeObject(customers);
			System.out.println("Serialized " + customers.size() + " customers");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Deserializes arraylist and loads data from file to memory
	@SuppressWarnings("unchecked") // Supress warning removes warning at compiletime
	public void deserialize() {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) { // flow of data inwards
			ArrayList<Customer> list =  (ArrayList<Customer>) ois.readObject();
			customers = list == null ? new ArrayList<Customer>() : list;
			System.out.println("Deserialized " + list.size() + " customers");
		} catch (IOException | ClassNotFoundException e) {
			//e.printStackTrace();
		}
	}
}
