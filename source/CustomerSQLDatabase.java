import java.sql.*;
public class CustomerSQLDatabase {

    private Connection c = null;

    public CustomerSQLDatabase() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:customer.db");
        } catch (Exception e) {
            System.err.println("Could not establish connection to Customer database");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Established connection to Customer database");
        createTableSet();
        initialize();
    }

    private void initialize() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Customer WHERE ID = ?");
            ResultSet rs = ps.executeQuery();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error while initializing database");
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }

    private void createTableSet() {
        try {
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS Customer (" +
                    " ID VARCHAR(16)," +
                    " name VARCHAR(100)," +
                    " phone_number INT(10)," +
                    " address VARCHAR(100)," +
                    " email VARCHAR (100)" +
                    ")" );
            ps.executeUpdate();
            ps.close();
            System.out.println("Created Customer table");
        } catch (SQLException e) {
            System.err.println("Error while creating customer table");
            System.err.println((e.getClass().getName() + ": " + e.getMessage()));
            System.exit(0);
        }
    }

    public void saveCustomer (Customer customer) {
        try {
            PreparedStatement ps;
            if(customerExist(customer)) {
                ps = c.prepareStatement("UPDATE Customer SET " +
                        " name =?," +
                        " phone_number =?," +
                        " address =?," +
                        " email =?" +
                        " WHERE ID=?");
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getPhone());
                ps.setString(3, customer.getAddress());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getCustomerID());
            } else {
                ps = c.prepareStatement("INSERT INTO Customer " +
                        " (ID, name, phone_number, address, email)" +
                        " VALUES(?,?,?,?,?)");
                ps.setString(1, customer.getCustomerID());
                ps.setString(2, customer.getName());
                ps.setString(3, customer.getPhone());
                ps.setString(4, customer.getAddress());
                ps.setString(5, customer.getEmail());
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error while saving customer");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private boolean customerExist(Customer customer) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Customer WHERE ID=?");
            ps.setString(1, customer.getCustomerID());
            ResultSet rs = ps.executeQuery();
            boolean b = rs.next();
            rs.close();
            ps.close();
            return b;
        } catch (SQLException e) {
            System.err.println("Error while verifying if customer exists");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    /*
     * Searches for a single customer by given the ID
     *
     * If customer is found, returns customer, else returns null
     */
    public Customer getCustomerByID(String ID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Customer WHERE ID=?");
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer(rs.getString(2), rs.getString(4), rs.getString(1), rs.getString(3), rs.getString(5), null, null);
            rs.close();
            ps.close();
            return customer;
        } catch (SQLException e) {
            System.err.println("Error while grabbing customer");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Customer[] getCustomersByPhone(String phone) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Customer GROUP BY ID HAVING phone_number=?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            Customer[] customers = new Customer[rs.getFetchSize()];
            int c = 0;
            while(rs.next()) {
                customers[0] = new Customer(rs.getString(2), rs.getString(4), rs.getString(1), rs.getString(3), rs.getString(5), null, null);
                c++;
            }
            rs.close();
            ps.close();
            return customers;
        } catch (SQLException e) {
            System.err.println("Error while grabbing customer");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Customer getCustomerByNameAndAddress(String name, String address) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Customer WHERE name=? AND address=? VALUES " +
                    "(?, ?)");
            ps.setString(1, name);
            ps.setString(2, address);
            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer(rs.getString(2), rs.getString(4), rs.getString(1), rs.getString(3), rs.getString(5), null, null);
            rs.close();
            ps.close();
            return customer;
        } catch (SQLException e) {
            System.err.println("Error while grabbing customer");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }
}
