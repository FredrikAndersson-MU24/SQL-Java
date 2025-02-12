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
            System.out.println("4. Update product");
            System.out.println("5. Delete product");
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
                    updateProduct();
                    break;
                case "5":
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
                case "0":
                    return;
                default:
                    System.out.println("Please enter a valid menu item.");
            }
        }
    }


    //CUSTOMERS
    public void addCustomer() {
        System.out.println("Enter the customers name: ");
        String name = scanner.nextLine();
        System.out.println("Enter customers email: ");
        String email = scanner.nextLine();
        customerDAO.addCustomer(name, email);
    }

    private void getCustomerByID() {
        System.out.println("Enter the ID of the customer you want to find: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerDAO.getCustomerById(id);
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


    // PRODUCTS
    private void addProduct() {
        System.out.println("Enter the product name: ");
        String name = scanner.nextLine();
        System.out.println(name);
        System.out.println("Enter the price: ");
        double price = scanner.nextDouble();
        System.out.println(price);
        scanner.nextLine();
        productDAO.addProduct(name, price);
    }

    private void viewAllProducts() {
        List<Product> products = productDAO.viewAllProducts();
        System.out.println("Products: ");
        products.forEach(p -> System.out.println(p));
    }

    private void searchProductsByName() {
        System.out.println("Please enter a term to search for: ");
        String search = scanner.nextLine();
        List<Product> result = productDAO.searchProductByName(search);
        if (!result.isEmpty()) {
            result.forEach(p -> System.out.println(p));
        } else {
            System.out.println("No products matching search term. Try again.");
        }
    }

    private void updateProduct() {
        System.out.println("Enter ID for the product you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter the new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        productDAO.updateProduct(id, newName, newPrice);
    }

    private void deleteProduct() {
        System.out.println("Enter the ID of the product you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        productDAO.deleteProduct(id);
    }


    // ORDERS
    public void addOrder() {
        System.out.println("Please enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
//        System.out.println("Please enter order date (YYYY-MM-DD): ");
//        Date date = Date.valueOf(scanner.nextLine());
        orderDao.addOrder(customerId);
    }

    public void getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        orders.forEach(o -> System.out.println(o));
    }

    public void getOrdersByCustomerID() {
        System.out.println("Please enter customer ID to get orders: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
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
        System.out.println("Please enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        orderDetailDao.addOrderDetail(orderId, productId, quantity);
    }

    private void getOrderDetailsByOrderId() {
        System.out.println("Please enter orderID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        OrderDetail orderDetail = orderDetailDao.getOrderDetailByOrderId(orderId);
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
}
