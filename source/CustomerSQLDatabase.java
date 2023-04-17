import java.sql.*;
public class CustomerSQLDatabase {

    public CustomerSQLDatabase() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:customer.db");
        } catch (Exception e) {
            System.err.println("Could not establish connection to Customer database");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Established connection to customer database");
    }
}
