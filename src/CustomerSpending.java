public class CustomerSpending {
    int id;
    String name;
    double spending;

    public CustomerSpending(int id, String name, double spending) {
        this.id = id;
        this.name = name;
        this.spending = spending;
    }


    @Override
    public String toString() {
        return "Customer ID: " + id +
                "\tName: " + name +
                "\tSpendings: " + spending;
    }
}
