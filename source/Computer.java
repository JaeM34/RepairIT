public class Computer {
    private String computerID;
    private String customerID;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private int year;
    //create computer constructer initialized by passing arguments through it

    public Computer(String model , int year){
        this.model = model;
        this.year = year;
    }
    public String getComputerID() {return computerID; }
    public String getCustomerID() {return customerID; }
    public String getManufacturer() {return manufacturer; }
    public String getModel() {return model; }
    public String getSerialNumber() {return serialNumber; }
    public int getYear() {return year; }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setYear(int year) {
        this.year = year;
    }
    // todo: getRepairTickets
}




