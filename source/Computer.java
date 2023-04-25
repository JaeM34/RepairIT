public class Computer {
    private String computerID;
    private String customerID;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private int year;

    // Computer constructor
    public Computer(String computerID, String customerID, String manufacturer, String model, String serialNumber, int year) {
        this.computerID = computerID;
        this.customerID = customerID;
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
        this.year = year;
    }

    public String getComputerID() {return computerID; }
    public String getCustomerID() {return customerID; }
    public String getManufacturer() {return manufacturer; }
    public String getModel() {return model; }
    public String getSerialNumber() {return serialNumber; }
    public int getYear() {return year; }
}
