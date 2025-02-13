import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private CustomerDAO customerDAO = new CustomerDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDao = new OrderDAO();
    private OrderDetailDAO orderDetailDao = new OrderDetailDAO();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("-- Main menu --");
            System.out.println("1. Product menu");
            System.out.println("2. Customer menu");
            System.out.println("3. Order menu");
            System.out.println("0. Quit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    productMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                case "3":
                    orderMenu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input. Enter a valid menu item.");
            }
        }
    }

    public void productMenu() {
        while (true) {
            System.out.println("-- Product catalog --");
            System.out.println("1. Add product");
            System.out.println("2. View all products");
            System.out.println("3. Search product by name");
            System.out.println("4. Show total sale per product");
            System.out.println("5. Update product");
            System.out.println("6. Delete product");
            System.out.println("0. To main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    viewAllProducts();
                    break;
                case "3":
                    searchProductsByName();
                    break;
                case "4":
                    getTotalSalesPerProduct();
                    break;
                case "5":
                    updateProduct();
                    break;
                case "6":
                    deleteProduct();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please enter a valid menu item.");
            }
        }
    }

    public void customerMenu() {
        while (true) {
            System.out.println("-- Customer registry --");
            System.out.println("1. Add customer");
            System.out.println("2. View all customers");
            System.out.println("3. Get customer by ID");
            System.out.println("4. Get customer spendings");
            System.out.println("0. To main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addCustomer();
                    break;
                case "2":
                    getAllCustomers();
                    break;
                case "3":
                    getCustomerByID();
                    break;
                case "4":
                    getCustomerSpending();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please enter a valid menu item.");
            }
        }
    }

    public void orderMenu() {
        while (true) {
            System.out.println("-- Order book --");
            System.out.println("1. Create order");
            System.out.println("2. View all orders");
            System.out.println("3. Find orders by customer ID");
            System.out.println("4. Add order details");
            System.out.println("5. View order details by order ID");
            System.out.println("6. View order history");
            System.out.println("7. Add order with order details");
            System.out.println("0. To main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addOrder();
                    break;
                case "2":
                    getAllOrders();
                    break;
                case "3":
                    getOrdersByCustomerID();
                    break;
                case "4":
                    addOrderDetails();
                    break;
                case "5":
                    getOrderDetailsByOrderId();
                    break;
                case "6":
                    getOrderHistory();
                    break;
                case "7":
                    addOrderWithOrderDetails();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please enter a valid menu item.");
            }
        }
    }


    //CUSTOMERS
    private void addCustomer() {
//        System.out.println("Enter the customers name: ");
//        String name = scanner.nextLine();
//        System.out.println("Enter customers email: ");
//        String email = scanner.nextLine();
        customerDAO.addCustomer(InputHandler.getString("Enter the customers name: "), InputHandler.getString("Enter customers email: "));
    }

    private void getCustomerByID() {
//        System.out.println("Enter the ID of the customer you want to find: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
        Customer customer = customerDAO.getCustomerById(InputHandler.getPositiveInt("Enter the ID of the customer you want to find: "));
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer ID not found!");
        }
    }

    private void getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        System.out.println("Customers: ");
        customers.forEach(customer -> System.out.println(customer));
    }

    private void getCustomerSpending() {
        List<CustomerSpending> customerSpending = customerDAO.getCustomerSpending();
        System.out.println("-- Customer spending's --");
        customerSpending.forEach(p -> System.out.println(p));
    }


    // PRODUCTS
    private void addProduct() {
//        System.out.println("Enter the product name: ");
//        String name = scanner.nextLine();
//        System.out.println("Enter the price: ");
//        double price = scanner.nextDouble();
//        scanner.nextLine();
//        String name = InputHandler.getString("Enter the product name: ");
//        double price = InputHandler.getPositiveDouble("Enter price");
        productDAO.addProduct(InputHandler.getString("Enter the product name: "), InputHandler.getPositiveDouble("Enter price"));
    }

    private void viewAllProducts() {
        List<Product> products = productDAO.viewAllProducts();
        System.out.println("Products: ");
        products.forEach(p -> System.out.println(p));
    }

    private void searchProductsByName() {
//        System.out.println("Please enter a term to search for: ");
//        String search = scanner.nextLine();
        List<Product> result = productDAO.searchProductByName(InputHandler.getString("Please enter a term to search for: "));
        if (!result.isEmpty()) {
            result.forEach(p -> System.out.println(p));
        } else {
            System.out.println("No products matching search term. Try again.");
        }
    }

    private void getTotalSalesPerProduct() {
        HashMap<String, String> map = productDAO.getTotalSalesPerProduct();
        for (String i : map.keySet()) {
            System.out.println(i + "\t" + map.get(i));
        }
    }

    private void updateProduct() {
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

    private void deleteProduct() {
//        System.out.println("Enter the ID of the product you want to delete: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
        productDAO.deleteProduct(InputHandler.getPositiveInt("Enter the ID of the product you want to delete: "));
    }


    // ORDERS
    public void addOrder() {
//        System.out.println("Please enter customer ID: ");
//        int customerId = scanner.nextInt();
//        scanner.nextLine();
        orderDao.addOrder(InputHandler.getPositiveInt("Please enter customer ID: "));
    }

    public void getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        orders.forEach(o -> System.out.println(o));
    }

    public void getOrdersByCustomerID() {
//        System.out.println("Please enter customer ID to get orders: ");
        int customerId = InputHandler.getPositiveInt("Please enter customer ID to get orders: ");
//        scanner.nextLine();
        List<Order> orders = orderDao.getOrdersByCustomerID(customerId);
        if (!orders.isEmpty()) {
            int numberOfOrders = orders.size();
            System.out.println("Customer " + customerId + " has " + numberOfOrders + " order(s): ");
            orders.forEach(o -> System.out.println(o));
        } else {
            System.out.println("Customer " + customerId + " has no orders.");
        }
    }

    private void addOrderDetails() {
//        System.out.println("Please enter order ID: ");
//        int orderId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Please enter product ID: ");
//        int productId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Please enter quantity: ");
//        int quantity = InputHandler.getPositiveInt("Please enter quantity: ");
//        scanner.nextLine();
        orderDetailDao.addOrderDetail(InputHandler.getPositiveInt("Please order ID: "), InputHandler.getPositiveInt("Please product ID: "), InputHandler.getPositiveInt("Please enter quantity: "));
    }

    private void getOrderDetailsByOrderId() {
//        System.out.println("Please enter orderID: ");
//        int orderId = InputHandler.getPositiveInt("Please enter orderID: ");
//        scanner.nextLine();
        OrderDetail orderDetail = orderDetailDao.getOrderDetailByOrderId(InputHandler.getPositiveInt("Please enter orderID: "));
        if (orderDetail != null) {
            System.out.println(orderDetail);
        } else {
            System.out.println("Order ID not found.");
        }
    }

    private void getOrderHistory() {
        List<OrderHistory> orderHistory = orderDao.getOrderHistory();
        orderHistory.forEach(o -> System.out.println(o));
    }

    private void addOrderWithOrderDetails() {
        int products = InputHandler.getPositiveInt("Enter number of products: ");
        List<OrderDetail> odList = new ArrayList<>();
        for (int i = 0; i < products; i++) {
            System.out.println("Please enter product ID: ");
            odList.add(new OrderDetail(0, 0, InputHandler.getPositiveInt("Enter product ID: "), InputHandler.getPositiveInt("Please enter quantity: ")));
        }
        orderDao.insertOrder(InputHandler.getPositiveInt("Please enter customer ID: "), odList);
    }
}
