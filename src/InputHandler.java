import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static double getPositiveDouble(String text) {
        while (true) {
            System.out.println(text);
            if (scanner.hasNextDouble()) {
                double result = scanner.nextDouble();
                if (result >= 0) {
                    scanner.nextLine();
                    return result;
                }
            }
            scanner.nextLine();
            System.out.println("Please enter a valid positive double!");
        }
    }

    public static int getPositiveInt(String text) {
        while (true) {
            System.out.println(text);
            if (scanner.hasNextInt()) {
                int result = scanner.nextInt();
                if (result >= 0) {
                    scanner.nextLine();
                    return result;
                }
            }
            scanner.nextLine();
            System.out.println("Please enter a valid positive integer!");
        }
    }

    public static String getString(String text) {
        while (true) {
            System.out.println(text);
            String result = scanner.nextLine();
            if (!result.isEmpty()) {
                return result;
            }
            System.out.println("Please enter a valid string! Can not be empty.");
        }
    }


    public static boolean isEmailValid(String email) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }

}
