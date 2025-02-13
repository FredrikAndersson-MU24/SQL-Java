import java.util.HashMap;
import java.util.List;

public class ProductService {

    private static ProductDAO productDAO = new ProductDAO();


    // PRODUCTS
    public static void addProduct() {
//        System.out.println("Enter the product name: ");
//        String name = scanner.nextLine();
//        System.out.println("Enter the price: ");
//        double price = scanner.nextDouble();
//        scanner.nextLine();
//        String name = InputHandler.getString("Enter the product name: ");
//        double price = InputHandler.getPositiveDouble("Enter price");
        productDAO.addProduct(InputHandler.getString("Enter the product name: "), InputHandler.getPositiveDouble("Enter price"));
    }

    public static void viewAllProducts() {
        List<Product> products = productDAO.viewAllProducts();
        System.out.println("Products: ");
        products.forEach(p -> System.out.println(p));
    }

    public static void searchProductsByName() {
//        System.out.println("Please enter a term to search for: ");
//        String search = scanner.nextLine();
        List<Product> result = productDAO.searchProductByName(InputHandler.getString("Please enter a term to search for: "));
        if (!result.isEmpty()) {
            result.forEach(p -> System.out.println(p));
        } else {
            System.out.println("No products matching search term. Try again.");
        }
    }

    public static void getTotalSalesPerProduct() {
        HashMap<String, String> map = productDAO.getTotalSalesPerProduct();
        for (String i : map.keySet()) {
            System.out.println(i + "\t" + map.get(i));
        }
    }

    public static void updateProduct() {
//        System.out.println("Enter ID for the product you want to update: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Enter the new name: ");
//        String newName = scanner.nextLine();
//        System.out.println("Enter the new price: ");
//        double newPrice = scanner.nextDouble();
//        scanner.nextLine();
        productDAO.updateProduct(InputHandler.getPositiveInt("Enter ID for the product you want to update: "), InputHandler.getString("Enter the new name: "), InputHandler.getPositiveDouble("Enter the new price: "));
    }

    public static void deleteProduct() {
//        System.out.println("Enter the ID of the product you want to delete: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
        productDAO.deleteProduct(InputHandler.getPositiveInt("Enter the ID of the product you want to delete: "));
    }


}
