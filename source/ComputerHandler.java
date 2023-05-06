import java.util.ArrayList;

public class ComputerHandler {

    private static final ComputerSQLDatabase database = new ComputerSQLDatabase();

    public ComputerHandler() {}

    public void saveComputer(Computer computer) {
        database.saveComputer(computer);
    }

    public Computer getComputerByID(String computerID) {
        return database.getComputerByComputerID(computerID);
    }

    public Computer getComputerBySerialNumber(String serial) {
        return database.getComputerBySerialNumber(serial);
    }

    public ArrayList<Computer> getComputersByCustomerID(String customerID) {
        return database.getComputersByCustomerID(customerID);
    }
}
