import java.sql.*;
import java.util.ArrayList;

public class ComputerSQLDatabase {

    private Connection c = null;

    public ComputerSQLDatabase() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:computer.db");
        } catch (Exception e) {
            System.err.println("Could not establish connection to Computer database");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        createTable();
        initialize();
        System.out.println("Established connection to Computer database");
    }

    private void initialize() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Computer WHERE ID = ?");
            ResultSet rs = ps.executeQuery();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error while initializing database");
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }

    private void createTable() {
        try {
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS Computer (" +
                    " ID VARCHAR (16)," +
                    " MANUFACTURER VARCHAR (100)," +
                    " MODEL VARCHAR (100)," +
                    " SERIAL VARCHAR (100)," +
                    " YEAR INT (4)," +
                    " CUSTOMER VARCHAR (100))");
            ps.executeUpdate();
            ps.close();
            System.out.println("Created Computer table");
        } catch (SQLException e) {
            System.err.println("Error while creating table for computers");
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }

    /*
    private void createTicketTable(String ID) {
        try {
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXIST ? " +
                    " REPAIR_TICKETS (VARCHAR 16)");
            ps.setString(1, ID);
            ps.executeUpdate();
            ps.close();
            System.out.println("Created table set for repair tickets for the computer " + ID);
        } catch (SQLException e) {
            System.err.println("Error while creating table for " + ID);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }
     */

    /*
     * Given a customerID, return all computers associated with customer
     */
    public ArrayList<Computer> getComputersByCustomerID(String customerID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Computer GROUP BY ID HAVING CUSTOMER=?");
            ps.setString(1, customerID);

            ResultSet rs = ps.executeQuery();
            ArrayList<Computer> computers = new ArrayList<Computer>();
            while(rs.next()) {
                Computer computer = new Computer(rs.getString(1), rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                computers.add(computer);
            }
            rs.close();
            ps.close();
            return computers;
        } catch (SQLException e) {
            System.err.println("Error while grabbing computers for customer " + customerID);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
        return null;
    }

    /*
     * Given a computerID, return computer associated with computerID
     */
    public Computer getComputerByComputerID(String computerID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Computer WHERE ID = ?");
            ps.setString(1, computerID);
            ResultSet rs = ps.getResultSet();
            ps.executeQuery();
            Computer computer = new Computer(rs.getString(1), rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            rs.close();
            ps.close();
            return computer;
        } catch (SQLException e) {
            System.err.println("Error while grabbing computer " + computerID);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
        return null;
    }

    /*
     * Given a serial number, return computer associated with serial number
     */
    public Computer getComputerBySerialNumber(String serialNumber) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Computer WHERE SERIAL = ?");
            ps.setString(1, serialNumber);
            ResultSet rs = ps.getResultSet();
            ps.executeQuery();
            Computer computer = new Computer(rs.getString(1), rs.getString(6), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            rs.close();
            ps.close();
            return computer;
        } catch (SQLException e) {
            System.err.println("Error while grabbing computer " + serialNumber);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
        return null;
    }

    public void saveComputer(Computer computer) {
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE Computer SET " +
                    " MANUFACTURER = ?," +
                    " MODEL = ?," +
                    " SERIAL = ?," +
                    " YEAR = ?," +
                    " CUSTOMER = ?");
            ps.setString(1, computer.getManufacturer());
            ps.setString(2, computer.getModel());
            ps.setInt(3, computer.getYear());
            ps.setString(4, computer.getComputerID());
            ps.executeUpdate();

            /*
            ArrayList<RepairTicket> tickets = RepairIT.getRepairticketHandler().getRepairTicketOnComputer(computer.getComputerID());
            for(RepairTicket repairTicket : tickets) {
                ps = c.prepareStatement("INSERT OR IGNORE INTO ? VALUES " +
                        " (?)");
                ps.setString(1, computer.getComputerID());
                ps.setString(2, repairTicket.getId());
                ps.executeUpdate();
            }
             */
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error while saving computer " + computer.getComputerID());
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }
}
