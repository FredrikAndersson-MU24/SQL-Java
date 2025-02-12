public class OrderDetail {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;

    public OrderDetail(int id, int order_id, int product_id, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order details: " +
                "\n\tID: " + id +
                "\n\tOrder ID: " + order_id +
                "\n\tProduct ID: " + product_id +
                "\n\tQuantity: " + quantity;
    }
}
