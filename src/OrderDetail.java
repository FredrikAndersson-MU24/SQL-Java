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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
