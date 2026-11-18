import java.util.Scanner;

/**
 * کلاس اجرایی برای تعامل با کاربر در ترمینال
 */
public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ماشین حساب ۴ عمل اصلی ===");
        System.out.print("عدد اول را وارد کنید: ");
        double num1 = scanner.nextDouble();

        System.out.print("عملگر را وارد کنید (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        System.out.print("عدد دوم را وارد کنید: ");
        double num2 = scanner.nextDouble();

        try {
            double result = switch (operator) {
                case '+' -> calc.add(num1, num2);
                case '-' -> calc.subtract(num1, num2);
                case '*' -> calc.multiply(num1, num2);
                case '/' -> calc.divide(num1, num2);
                default -> throw new IllegalArgumentException("عملگر نامعتبر است!");
            };
            System.out.println("نتیجه: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("خطا: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
