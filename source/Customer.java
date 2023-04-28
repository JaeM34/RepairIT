public class Customer {
    private String customerID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Computer[] computers;
    private RepairTicket[] repairTickets;


    //create customer constructer initialized by passing arguments through it
    public Customer(String name, String address, String customerID, String phone, String email, Computer[] computers, RepairTicket[] repairTickets ) {
        this.name = name;
        this.address = address;
        this.customerID = customerID;
        this.phone = phone;
        this.email = email;
        this.computers = computers;
        this.repairTickets = repairTickets;
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
    public Computer[] getComputers() {
        return computers;
    }
    public RepairTicket[] getRepairTickets() {
        return repairTickets;
    }
}
