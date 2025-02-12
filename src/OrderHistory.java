import java.sql.Date;

public class OrderHistory {
    private int orderID;
    private String customerName;
    private Date orderDate;
    private String productName;
    private int quantity;

    public OrderHistory(int orderID, String customerName, Date orderDate, String productName, int quantity) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.productName = productName;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\nOrder ID: " + orderID +
                "\tCustomer name: " + customerName +
                "\tOrder date: " + orderDate +
                "\tProduct name: " + productName +
                "\tQuantity: " + quantity;
    }
}
