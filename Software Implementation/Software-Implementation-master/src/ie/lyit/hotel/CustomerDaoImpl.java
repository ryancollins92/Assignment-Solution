package ie.lyit.hotel;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
	
   //list is working as a database
   List<Customer> customers;

   public CustomerDaoImpl(){
      customers = new ArrayList<Customer>();
      Customer customer1 = new Customer("Mr","Mike","prog","Derry","123456","mike@hotmail.com");
      Customer customer2 = new Customer("Mr","Jimmy","java","Letterkenny","0987645","jimmy@hotmail.com");
      customers.add(customer1);
      customers.add(customer2);		
   }
   @Override
   public void deletecustomer(Customer customer) {
      customers.remove(customer.getNumber());
      System.out.println("customer: Number " + customer.getNumber() + ", deleted from database");
   }

   //retrive list of customers from the database
   @Override
   public List<Customer> getAllCustomers() {
      return customers;
   }

   @Override
   public Customer getcustomer(int Number) {
      return customers.get(Number);
   }

   @Override
   public void updatecustomer(Customer customer) {
      customers.get(customer.getNumber()).setName(customer.getName());
      System.out.println("customer: Number " + customer.getNumber() + ", updated in the database");
   }
}
