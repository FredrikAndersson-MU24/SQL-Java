import java.util.Scanner;

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
        System.out.println(text);
        return scanner.nextLine();
    }

}
