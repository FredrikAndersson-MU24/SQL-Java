import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDAO {

    public void addProduct(String name, double price) {
        String query = "INSERT INTO products (name, price) VALUES (?,?);";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.executeUpdate(); // Skicka query till databasen
        } catch (SQLException e) {
            System.out.println("Could not create product.");
            e.printStackTrace();
        }
    }

    public List<Product> viewAllProducts() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM products;";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get products");
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> searchProductByName(String name) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?;";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get products!");
            e.printStackTrace();
        }

        return products;
    }

    public HashMap getTotalSalesPerProduct() {
        HashMap<String, String> result = new HashMap<>();
        String query = "SELECT products.name AS Product, SUM(products.price * order_details.quantity) AS 'Total sales' FROM products\n" +
                "\tJOIN order_details ON products.id = order_details.product_id\n" +
                "    GROUP BY Product;";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.put(rs.getString(1), String.valueOf(rs.getDouble(2)));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get products!");
            e.printStackTrace();
        }

        return result;
    }

    public void updateProduct(int id, String newName, double newPrice) {
        String query = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newName);
            stmt.setDouble(2, newPrice);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to update product!");
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?;";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete product!");
            e.printStackTrace();
        }
    }

}
