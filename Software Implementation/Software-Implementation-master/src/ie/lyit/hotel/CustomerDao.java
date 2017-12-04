package ie.lyit.hotel;

import java.util.List;

public interface CustomerDao {
   public List<Customer> getAllCustomers();
   public Customer getcustomer(int rollNo);
   public void updatecustomer(Customer customer);
   public void deletecustomer(Customer customer);
}
