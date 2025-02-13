import java.util.ArrayList;
import java.util.List;

public abstract class OrderService {

    private static OrderDetailDAO orderDetailDao = new OrderDetailDAO();
    private static OrderDAO orderDao = new OrderDAO();


    // ORDERS
    public static void addOrder() {
//        System.out.println("Please enter customer ID: ");
//        int customerId = scanner.nextInt();
//        scanner.nextLine();
        orderDao.addOrder(InputHandler.getPositiveInt("Please enter customer ID: "));
    }

    public static void getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        orders.forEach(o -> System.out.println(o));
    }

    public static void getOrdersByCustomerID() {
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

    public static void addOrderDetails() {
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

    public static void getOrderDetailsByOrderId() {
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

    public static void getOrderHistory() {
        List<OrderHistory> orderHistory = orderDao.getOrderHistory();
        orderHistory.forEach(o -> System.out.println(o));
    }

    public static void addOrderWithOrderDetails() {
        int products = InputHandler.getPositiveInt("Enter number of products: ");
        List<OrderDetail> odList = new ArrayList<>();
        for (int i = 0; i < products; i++) {
            System.out.println("Please enter product ID: ");
            odList.add(new OrderDetail(0, 0, InputHandler.getPositiveInt("Enter product ID: "), InputHandler.getPositiveInt("Please enter quantity: ")));
        }
        orderDao.insertOrder(InputHandler.getPositiveInt("Please enter customer ID: "), odList);
    }

}
