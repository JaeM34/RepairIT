import java.sql.*;
public class CustomerHandler {

    private static final CustomerSQLDatabase database = new CustomerSQLDatabase();

    public CustomerHandler() {
    }

    public void addCustomer(Customer customer) {
        String name = customer.getName();
        int customerID = Integer.parseInt(customer.getCustomerID());
        String address = customer.getAddress();
        String query = "INSERT INTO customers (name, id, address) VALUES ('" + name + "', " + customerID + ", '" + address + "')";
        database.executeQuery(query);
    }
    //todo public Customer getCustomer(String name) {}
    public Customer getCustomer(String name) throws SQLException {
        String query = "SELECT * FROM customers WHERE name = '" + name + "'";
        ResultSet resultSet = database.executeQuery(query);
        if (resultSet.next()) {
            int customerID = resultSet.getInt("customerID");
            String address = resultSet.getString("address");
            return new Customer(name,customerID, address);
        } else {
            return null;
        }
    }

    //todo public Customer getCustomer(int ID) {}
    public Customer getCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM customers WHERE id = " + customerID;
        ResultSet resultSet = database.executeQuery(query);
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            return new Customer(name, customerID, address);
        } else {
            return null;
        }
    }
    //todo public Customer getCustomer(String address) {}
    public Customer getCustomer(String address) throws SQLException {
        String query = "SELECT * FROM customers WHERE address = '" + address + "'";
        ResultSet resultSet = database.executeQuery(query);
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int id = 0;
            try {
                id = resultSet.getInt("id");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return new Customer(name, id, address);
        } else {
            return null;
        }
    }
    //todo public void saveCustomer(Customer customer) {}
    public void saveCustomer(Customer customer) {
        int id = Integer.parseInt(customer.getCustomerID());
        String name = customer.getName();
        String address = customer.getAddress();

        String query = "UPDATE customers SET name = '" + name + "', address = '" + address + "' WHERE id = " + id;
        database.executeQuery(query);
    }

}
