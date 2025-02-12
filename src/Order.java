import java.sql.Date;

public class Order {

    private int id;
    private int customer_id;
    private Date order_date;

    public Order(int id, int customer_id, Date order_date) {
        this.id = id;
        this.customer_id = customer_id;
        this.order_date = order_date;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return  "Orderid: " + id +
                "\tCustomer ID: " + customer_id +
                "\tOrder date: " + order_date;
    }
}
