import java.sql.*;
public class CustomerHandler {

    private static final CustomerSQLDatabase database = new CustomerSQLDatabase();

    public CustomerHandler() {
    }

    public void addCustomer(Customer customer) {

    }

    //todo public Customer getCustomer(int ID) {}
    public Customer getCustomerByID(int customerID) {
        return null;
    }
    //todo public Customer getCustomer(String address) {}
    public Customer getCustomerByNameAndAddress(String name, String address) {
        return null;
    }
    //todo public void saveCustomer(Customer customer) {}
    public void saveCustomer(Customer customer) {

    }

}
