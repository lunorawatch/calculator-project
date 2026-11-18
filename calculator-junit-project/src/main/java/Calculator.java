/**
 * کلاس ماشین حساب ۴ عمل اصلی (Calculator)
 * پیاده‌سازی عملیات جمع، تفریق، ضرب و تقسیم همراه با مدیریت استثناها
 */
public class Calculator {

    /**
     * جمع دو عدد
     */
    public double add(double a, double b) {
        validateInputs(a, b);
        return a + b;
    }

    /**
     * تفریق دو عدد (a - b)
     */
    public double subtract(double a, double b) {
        validateInputs(a, b);
        return a - b;
    }

    /**
     * ضرب دو عدد (با جلوگیری از بازگشت -0.0)
     */
    public double multiply(double a, double b) {
        validateInputs(a, b);
        double result = a * b;
        return (result == 0.0) ? 0.0 : result;
    }

    /**
     * تقسیم عدد اول بر عدد دوم (با مدیریت تقسیم بر صفر)
     */
    public double divide(double a, double b) {
        validateInputs(a, b);
        if (b == 0.0) {
            throw new IllegalArgumentException("تقسیم بر صفر مجاز نیست!");
        }
        double result = a / b;
        return (result == 0.0) ? 0.0 : result;
    }

    /**
     * اعتبارسنجی ورودی‌ها برای جلوگیری از مقادیر نامعتبر (NaN)
     */
    private void validateInputs(double a, double b) {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("ورودی نامعتبر (NaN) مجاز نیست!");
        }
    }
}
