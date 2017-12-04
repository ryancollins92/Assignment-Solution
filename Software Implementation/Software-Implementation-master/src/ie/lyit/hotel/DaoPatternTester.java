package ie.lyit.hotel;

public class DaoPatternTester {
	   public static void main(String[] args) {
	      CustomerDao CustomerDao = new CustomerDaoImpl();

	      //print all Customers
	      for (Customer customer : CustomerDao.getAllCustomers()) {
	         System.out.println("Customer: [Number : " + customer.getNumber() + ", Email : " + customer.getEmailAddress() + " ]");
	      }


	      //update Customer
	      Customer customer = CustomerDao.getAllCustomers().get(0);
	      customer.setEmailAddress("Mike911@hotmail.com");
	      CustomerDao.updatecustomer(customer);

	      //get the Customer
	      CustomerDao.getcustomer(0);
	      System.out.println("Customer: [Number : " + customer.getNumber() + ", Email : " + customer.getEmailAddress() + " ]");		
	   }
	}
