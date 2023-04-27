public class Customer {
    private String customerID;
    private String name;
    private int phone;
    private String email;
    private String address;
    private String computers;
    private String repairAllTickets;


    //create customer constructer initialized by passing arguments through it
    public Customer(String name, String address, String customerID, int phone,String email, String computers, String repairAllTickets ) {
        this.name = name;
        this.address = address;
        this.customerID = customerID;
        this.phone = phone;
        this.email = email;
        this.computers = computers;
        this.repairAllTickets = repairAllTickets;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getComputers() {
        return computers;
    }
    public String getRepairAllTickets() {
        return repairAllTickets;
    }
}
