import java.util.ArrayList;

public class CustomerHandler {

    private static final CustomerSQLDatabase database = new CustomerSQLDatabase();

    public CustomerHandler() {}

    /*
     * SAVES CUSTOMER THE DATABASE
     */
    public void saveCustomer(Customer customer) {
        database.saveCustomer(customer);
    }

    public Customer getCustomerByID(String customerID) {
        return customerBuilder(database.getCustomerByID(customerID));
    }
    public Customer getCustomerByNameAndAddress(String name, String address) {
        return customerBuilder(database.getCustomerByNameAndAddress(name, address));
    }

    public Customer[] getCustomersByPhone(String phone) {
        Customer[] customers = database.getCustomersByPhone(phone);
        for(int i = 0; i < customers.length; i++) {
            customers[i] = customerBuilder(customers[i]);
        }
        return customers;
    }

    private Customer customerBuilder(Customer customer) {
        ArrayList<Computer> computers = RepairIT.getComputerHandler().getComputersByCustomerID(customer.getCustomerID());
        ArrayList<RepairTicket> repairTickets = RepairIT.getRepairticketHandler().getRepairTicketsOnCustomer(customer.getCustomerID());
        customer.setComputers(computers);
        customer.setRepairTickets(repairTickets);
        return customer;
    }

}
