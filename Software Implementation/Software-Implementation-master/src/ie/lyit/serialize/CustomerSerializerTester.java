package ie.lyit.serialize;

import java.util.Arrays;
import java.util.Scanner;

import ie.lyit.hotel.Customer;

public class CustomerSerializerTester {

	public static void main(String[] args) {
		// Adding command list
		final String[] commands = new String[] { "view", "add", "delete", "edit", "serialize", "deserialize", "exit" };
		CustomerSerializer cs = new CustomerSerializer();
		
		//deserializes the arraylist on startup
		cs.deserialize();
		//Initiating scanner using parameter of the systems inputstream (command line)
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("List of commands you may use");
			System.out.println(Arrays.toString(commands));
			System.out.println();
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("exit")) {
				return;
			} else if (input.equalsIgnoreCase("view")) {
				cs.view();
			} else if (input.equalsIgnoreCase("serialize")) {
				cs.serialize();
			} else if (input.equalsIgnoreCase("deserialize")) {
				cs.deserialize();
			} else if (input.equalsIgnoreCase("add")) {
				
				// printing out information
				System.out.print("Customers title: ");
				String title = scanner.nextLine();
				System.out.print("Customers first name: ");
				String firstName = scanner.nextLine();
				System.out.print("Customers surname: ");
				String surname = scanner.nextLine();
				System.out.print("Customers address: ");
				String address = scanner.nextLine();
				System.out.print("Customers phone number: ");
				String phoneNumber = scanner.nextLine();
				System.out.print("Customers email: ");
				String email = scanner.nextLine();
				Customer customer = new Customer(title, firstName, surname, address, phoneNumber, email);
				cs.add(customer);
				System.out.println("Customer added: " + customer);
				
				// 
			} else if (input.equalsIgnoreCase("delete")) {
				System.out.print("Customers email: ");
				String email = scanner.nextLine();
				Customer customer = cs.list().stream().filter(c -> c.getEmailAddress().equals(email)).findFirst() //Using java 8 streams and predicates to find a customer from the arraylist via their email
						.orElse(null);
				if (customer == null) {
					System.out.println("Customer with email: " + email + " doesn't exist!");
				} else {
					System.out.println("Customer deleted: " + customer);
					cs.delete(customer);
				}
			} else if (input.equalsIgnoreCase("edit")) {
				System.out.print("Enter the customers email in which you'd like to edit: ");
				String email = scanner.nextLine();
				Customer customer = cs.list().stream().filter(c -> c.getEmailAddress().equals(email)).findFirst().orElse(null); // Using java 8 streams and predicates to find a customer from the arraylist via their email
				//Setting clone to original customer to compare details after they have been edited
				Customer clone = new Customer(customer);
				if (customer == null) {
					System.out.println("Customer with email: " + email + " doesn't exist!");
				} else {
					
					// Getting details from user
					System.out.println("If any of the inputs are null, the value will stay the same!");
					System.out.printf("[%s] Customers new title:", customer.getName().getTitle());
					String title = scanner.nextLine();
					System.out.printf("[%s] Customers new first name: ", customer.getName().getFirstName());
					String firstName = scanner.nextLine();
					System.out.printf("[%s] Customers new surname: ", customer.getName().getSurname());
					String surname = scanner.nextLine();
					System.out.printf("[%s] Customers new address: ", customer.getAddress());
					String address = scanner.nextLine();
					System.out.printf("[%s] Customers new phone number: ", customer.getPhoneNumber());
					String phoneNumber = scanner.nextLine();
					System.out.printf("[%s] Customers new email: ", customer.getEmailAddress());
					String email2 = scanner.nextLine();
					// Setting details from user
					if (!(title == null || title.isEmpty()))
						customer.getName().setTitle(title);
					if (!(firstName == null || firstName.isEmpty()))
						customer.getName().setFirstName(firstName);
					if (!(surname == null || surname.isEmpty()))
						customer.getName().setSurname(surname);
					if (!(address == null || address.isEmpty()))
						customer.setAddress(address);
					if (!(phoneNumber == null || phoneNumber.isEmpty()))
						customer.setPhoneNumber(phoneNumber);
					if (!(email2 == null || email2.isEmpty()))
						customer.setEmailAddress(email2);
					System.out.println("Old details: " + clone);
					System.out.println("New details: " + customer);
				}
			}
		} while (scanner.hasNextLine());
		//Always serialize on shutdown
		cs.serialize();
	}
}
