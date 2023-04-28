import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class RepairTicketSQLDatabase {


    public RepairTicketSQLDatabase() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:repair-ticket.db");
        } catch (Exception e) {
            System.err.println("Could not establish connection to Repair-Ticket database");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Established connection to Repair-Ticket database");
    }

    /*
     * Given a customerID, return all RepairTickets associated with the customerID
     */
    public ArrayList<RepairTicket> getRepairTicketByCustomerID(String customerID) {
        return null;
    }

    /*
     * Given a ticketID, return RepairTicket associated with the ID
     */
    public RepairTicket getRepairTicketByTicketID(String ticketID) {
        return null;
    }

    /*
     * Given a computer ID, return all RepairTickets related to the computer
     */
    public ArrayList<RepairTicket> getRepairTicketByComputerID(String computerID) {
        return null;
    }
}
