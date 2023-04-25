import java.sql.Connection;
import java.sql.DriverManager;

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
}
