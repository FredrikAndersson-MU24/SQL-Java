public class ConsoleView {

    public void showMenu() {
        while (true) {
            String choice = InputHandler.getString("""
                    -- Main menu --
                    1. Product menu
                    2. Customer menu
                    3. Order menu
                    0. Quit
                    """);
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
            String choice = InputHandler.getString("""
                    -- Product catalog --
                    1. Add product
                    2. View all products
                    3. Search product by name
                    4. Show total sale per product
                    5. Update product
                    6. Delete product
                    0. To main menu""");
            switch (choice) {
                case "1":
                    ProductService.addProduct();
                    break;
                case "2":
                    ProductService.viewAllProducts();
                    break;
                case "3":
                    ProductService.searchProductsByName();
                    break;
                case "4":
                    ProductService.getTotalSalesPerProduct();
                    break;
                case "5":
                    ProductService.updateProduct();
                    break;
                case "6":
                    ProductService.deleteProduct();
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
            String choice = InputHandler.getString("""
                    -- Customer registry --
                    1. Add customer
                    2. View all customers
                    3. Get customer by ID
                    4. Get customer spendings
                    0. To main menu
                    """);
            switch (choice) {
                case "1":
                    CustomerService.addCustomer();
                    break;
                case "2":
                    CustomerService.getAllCustomers();
                    break;
                case "3":
                    CustomerService.getCustomerByID();
                    break;
                case "4":
                    CustomerService.getCustomerSpending();
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
            String choice = InputHandler.getString("""         
                    -- Order book --
                    1. Create order
                    2. View all orders
                    3. Find orders by customer ID
                    4. Add order details
                    5. View order details by order ID
                    6. View order history
                    7. Add order with order details
                    0. To main menu
                    """);
            switch (choice) {
                case "1":
                    OrderService.addOrder();
                    break;
                case "2":
                    OrderService.getAllOrders();
                    break;
                case "3":
                    OrderService.getOrdersByCustomerID();
                    break;
                case "4":
                    OrderService.addOrderDetails();
                    break;
                case "5":
                    OrderService.getOrderDetailsByOrderId();
                    break;
                case "6":
                    OrderService.getOrderHistory();
                    break;
                case "7":
                    OrderService.addOrderWithOrderDetails();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Please enter a valid menu item.");
            }
        }
    }


}
