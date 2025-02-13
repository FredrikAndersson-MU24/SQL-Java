import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(String name, String email) {
        String query = "INSERT INTO customers (name, email)  VALUES (?, ?)"; // ? placeholder för att motverka injection
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name); // Byt ut första frågetecken
            stmt.setString(2, email); // Byt ut andra frågetecken
            stmt.executeUpdate(); //Skicka query till databas
        } catch (SQLException e) {
            System.out.println("Failed to add customer.");
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get customers from database!");
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        String query = "SELECT * FROM customers WHERE id = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get customer!");
            e.printStackTrace();
        }
        return customer;
    }

    public List<CustomerSpending> getCustomerSpending() {
        List<CustomerSpending> customerSpending = new ArrayList<>();
        String query = "SELECT customers.id, customers.name, SUM(products.price * order_details.quantity) AS total_spent FROM customers " +
                "JOIN orders ON customers.id = orders.customer_id " +
                "JOIN order_details ON orders.id = order_details.order_id " +
                "JOIN products ON order_details.product_id = products.id " +
                "GROUP BY customers.id, customers.name " +
                "ORDER BY total_spent DESC;";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                customerSpending.add(new CustomerSpending(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get customer!");
            e.printStackTrace();
        }
        return customerSpending;
    }
}
