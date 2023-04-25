import java.sql.Connection;
import java.sql.DriverManager;
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
}
