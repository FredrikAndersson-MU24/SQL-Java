import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailDAO {

    public void addOrderDetail(int orderId, int productId, int quantity) {
        String query = "INSERT INTO order_details(order_id, product_id, quantity) VALUES (?, ?, ?);";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to create order details!");
            e.printStackTrace();
        }
    }

    public OrderDetail getOrderDetailByOrderId(int orderId) {
        String query = "SELECT * FROM order_details WHERE order_id = ?;";
        OrderDetail orderDetail = null;
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                orderDetail = new OrderDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get order details!");
            e.printStackTrace();
        }
        return orderDetail;
    }


}
