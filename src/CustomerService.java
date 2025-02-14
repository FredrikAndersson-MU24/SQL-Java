import java.util.List;

public class CustomerService {

    private static CustomerDAO customerDAO = new CustomerDAO();

    //CUSTOMERS
    public static void addCustomer() {
//        System.out.println("Enter the customers name: ");
//        String name = scanner.nextLine();
//        System.out.println("Enter customers email: ");
//        String email = scanner.nextLine();
        String name = InputHandler.getString("Enter the customers name: ");
        String email;
        while (true) {
            email = InputHandler.getString("Enter customers email: ");
            if (InputHandler.isEmailValid(email)) {
                break;
            }
            ;
        }
        customerDAO.addCustomer(name, email);
    }

    public static void getCustomerByID() {
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

    public static void getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        System.out.println("Customers: ");
        customers.forEach(customer -> System.out.println(customer));
    }

    public static void getCustomerSpending() {
        List<CustomerSpending> customerSpending = customerDAO.getCustomerSpending();
        System.out.println("-- Customer spending's --");
        customerSpending.forEach(p -> System.out.println(p));
    }


}
