import java.sql.*;
import java.util.ArrayList;

public class RepairTicketSQLDatabase {

    Connection c = null;

    public RepairTicketSQLDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:repair-ticket.db");
        } catch (Exception e) {
            System.err.println("Could not establish connection to Repair-Ticket database");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Established connection to Repair-Ticket database");
        createTable();
        initialize();
    }

    private void initialize() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RepairTicket WHERE ID=?");
            ps.executeQuery();
            ps.close();
        } catch (SQLException e ) {
            System.err.println("Error while initializing repair ticket database");
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }

    private void createTable() {
        try {
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS RepairTicket (" +
                    " ID VARCHAR (100)," +
                    " CustomerID VARCHAR (100)," +
                    " ComputerID VARCHAR (100)," +
                    " ISSUE TEXT," +
                    " STATUS VARCHAR (100))");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error while creating table for repair tickets");
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }

    /*
     * Given a customerID, return all RepairTickets associated with the customerID
     */
    public ArrayList<RepairTicket> getRepairTicketByCustomerID(String customerID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RepairTicket GROUP BY ID HAVING CustomerID=?");
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();
            ArrayList<RepairTicket> repairTickets = new ArrayList<RepairTicket>();
            while(rs.next()) {
                RepairTicket repairTicket = new RepairTicket(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                repairTickets.add(repairTicket);
            }
            rs.close();
            ps.close();
            return repairTickets;
        } catch (SQLException e) {
            System.err.println("Error while grabbing computers for customer " + customerID);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
        return null;
    }

    /*
     * Given a ticketID, return RepairTicket associated with the ID
     */
    public RepairTicket getRepairTicketByTicketID(String ticketID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RepairTicket WHERE ID=?");
            ps.setString(1, ticketID);
            ResultSet rs = ps.executeQuery();
            RepairTicket repairTicket = new RepairTicket(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            rs.close();
            ps.close();
            return repairTicket;
        } catch (SQLException e) {
            System.err.println("Error while grabbing repair ticket " + ticketID);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
        return null;
    }

    /*
     * Given a computer ID, return all RepairTickets related to the computer
     */
    public ArrayList<RepairTicket> getRepairTicketByComputerID(String computerID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RepairTicket GROUP BY ID HAVING computerID=?");
            ps.setString(1, computerID);
            ResultSet rs = ps.executeQuery();
            ArrayList<RepairTicket> repairTickets = new ArrayList<RepairTicket>();
            while(rs.next()) {
                RepairTicket repairTicket = new RepairTicket(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                repairTickets.add(repairTicket);
            }
            rs.close();
            ps.close();
            return repairTickets;
        } catch (SQLException e) {
            System.err.println("Error while grabbing computers for customer " + computerID);
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
        return null;
    }
}
