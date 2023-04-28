import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ComputerSQLDatabase {

    public ComputerSQLDatabase() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:computer.db");
        } catch (Exception e) {
            System.err.println("Could not establish connection to Computer database");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Established connection to Computer database");
    }

    /*
     * Given a customerID, return all computers associated with customer
     */
    public ArrayList<Computer> getComputersByCustomerID(String customerID) {
        return null;
    }

    /*
     * Given a computerID, return computer associated with computerID
     */
    public Computer getComputerByComputerID(String computerID) {
        return null;
    }

    /*
     * Given a serial number, return computer associated with serial number
     */
    public Computer getComputerBySerialNumber(String serialNumber) {
        return null;
    }
}
