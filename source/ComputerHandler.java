public class ComputerHandler {

    private static final ComputerSQLDatabase database = new ComputerSQLDatabase();

    public ComputerHandler() {}

    public void addComputer(Computer computer){
        String ID = computer.getComputerID();
        String customerID = computer.getCustomerID();
        String manufacturer = computer.getManufacturer();
        String serialNumber = computer.getSerialNumber();
        int year = computer.getYear();
    }

    // todo: SearchByID()

    // todo: searchBySerialNumber()

    // todo: getComputersByOwner()

    // todo: editComputer()
    public Object getRepairTicketsByCustomerID(String customerID) {
        return null;
    }

    public Object getComputersByCustomerID(String customerID) {
        return null;
    }
}
