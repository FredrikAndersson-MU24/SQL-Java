import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public void addOrder(int CustomerID) {
        String query = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?);";
        LocalDate date = LocalDate.now();
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, CustomerID);
            stmt.setDate(2, Date.valueOf(date));
            stmt.executeUpdate();
            System.out.println("Order successfully created!");
        } catch (SQLException e) {
            System.out.println("Failed to create order!");
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders;";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getDate("order_date")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get all orders!");
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrdersByCustomerID(int customer_id) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE customer_id = ?;";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customer_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getDate("order_date")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get orders!");
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrderHistory> getOrderHistory() {
        List<OrderHistory> orderHistory = new ArrayList<>();
        String query = "SELECT o.id AS order_id, c.name AS customer_name, o.order_date, p.name AS product_name, od.quantity " +
                "FROM orders o JOIN customers c ON o.customer_id = c.id        " +
                "JOIN order_details od ON o.id = od.order_id        " +
                "JOIN products p ON od.product_id = p.id ORDER BY o.order_date DESC;";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orderHistory.add(new OrderHistory(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println("FAiled to get order history!");
            e.printStackTrace();
        }
        return orderHistory;
    }


    public void insertOrder(int customerId, List<OrderDetail> orderDetailsList) {
        Connection conn = Database.getConnection();

        try {
            conn.setAutoCommit(false);

            String insertOrderQuery = "INSERT INTO orders (customer_id) VALUES (?);";
            PreparedStatement stmt = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
            ResultSet keySet = stmt.getGeneratedKeys();
            int orderId = 0;
            if (keySet.next()) {
                orderId = keySet.getInt(1);
            }

            String orderDetailQuery = "INSERT INTO order_details (order_id, product_id, quantity) VALUES (?, ?, ?);";
            for (OrderDetail od : orderDetailsList) {
                PreparedStatement detailStatement = conn.prepareStatement(orderDetailQuery);
                detailStatement.setInt(1, orderId);
                detailStatement.setInt(2, od.getProduct_id());
                detailStatement.setInt(3, od.getQuantity());
                detailStatement.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            System.out.println("FAILED");
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
