import java.lang.reflect.Array;
import java.util.ArrayList;

public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private ArrayList<Computer> computers;
    private ArrayList<RepairTicket> repairTickets;


    //create customer constructor initialized by passing arguments through it
    public Customer(String name, String address, String customerID, String phone, String email, ArrayList<Computer> computers, ArrayList<RepairTicket> repairTickets ) {
        this.name = name;
        this.address = address;
        this.customerID = customerID;
        this.phone = phone;
        this.email = email;
        this.computers = computers;
        this.repairTickets = repairTickets;
    }
    /*
    public Customer getCustomer(String name, String address) {
        Customer customer = CustomerHandler.database.getCustomerByNameAndAddress(name, address);
        customer.addComputers(RepairIT.getComputerHandler().getComputersByCustomerID(customer.getCustomerID());
        customer.addRepairTickets(RepairIT.getComputerHandler().getRepairTicketsByCustomerID(customer.getCustomerID())));
        return customer;
    }

     */

    private void addRepairTickets(Object repairTicketsByCustomerID) {
        this.repairTickets.addAll(repairTickets);
    }

    private void addComputers(Object computersByCustomerID) {
        this.computers.addAll(computers);
    }

    public Customer(String name, int customerID, String address) {
        this.name = name;
        this.customerID = String.valueOf(customerID);
        this.address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public ArrayList<Computer> getComputers() {
        return computers;
    }
    public ArrayList<RepairTicket> getRepairTickets() {
        return repairTickets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComputers(ArrayList<Computer> computers) {
        this.computers = computers;
    }

    public void setRepairTickets(ArrayList<RepairTicket> repairTickets) {
        this.repairTickets = repairTickets;
    }
}
