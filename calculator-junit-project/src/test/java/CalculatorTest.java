import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * مجموعه تست‌های واحد (Unit Tests) برای کلاس Calculator با استفاده از JUnit 5
 */
@DisplayName("تست‌های جامع ماشین حساب ۴ عمل اصلی")
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Nested
    @DisplayName("گروه تست‌های عملگر جمع (Addition)")
    class AdditionTests {

        @Test
        @DisplayName("جمع دو عدد مثبت، منفی و صفر")
        void testBasicAddition() {
            assertEquals(15.0, calculator.add(10, 5));
            assertEquals(-8.0, calculator.add(-5, -3));
            assertEquals(0.0, calculator.add(-5, 5));
            assertEquals(7.0, calculator.add(7, 0));
        }

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @DisplayName("تست پارامتری جمع")
        @CsvSource({
            "1.5, 2.5, 4.0",
            "-10.5, 5.5, -5.0",
            "0.0, 0.0, 0.0",
            "1000000.0, 2500000.0, 3500000.0"
        })
        void testParameterizedAddition(double a, double b, double expected) {
            assertEquals(expected, calculator.add(a, b), 0.0001);
        }
    }

    @Nested
    @DisplayName("گروه تست‌های عملگر تفریق (Subtraction)")
    class SubtractionTests {

        @Test
        @DisplayName("تفریق اعداد مثبت، منفی و صفر")
        void testBasicSubtraction() {
            assertEquals(5.0, calculator.subtract(10, 5));
            assertEquals(-5.0, calculator.subtract(5, 10));
            assertEquals(-2.0, calculator.subtract(-5, -3));
            assertEquals(10.0, calculator.subtract(10, 0));
        }

        @ParameterizedTest(name = "{0} - {1} = {2}")
        @DisplayName("تست پارامتری تفریق")
        @CsvSource({
            "20.0, 8.0, 12.0",
            "-4.5, 2.5, -7.0",
            "3.3, 1.1, 2.2"
        })
        void testParameterizedSubtraction(double a, double b, double expected) {
            assertEquals(expected, calculator.subtract(a, b), 0.0001);
        }
    }

    @Nested
    @DisplayName("گروه تست‌های عملگر ضرب (Multiplication)")
    class MultiplicationTests {

        @Test
        @DisplayName("ضرب اعداد مثبت، منفی و قانون ضرب در صفر")
        void testBasicMultiplication() {
            assertEquals(50.0, calculator.multiply(10, 5));
            assertEquals(-50.0, calculator.multiply(-10, 5));
            assertEquals(50.0, calculator.multiply(-10, -5));
            assertEquals(0.0, calculator.multiply(-15, 0));
        }

        @ParameterizedTest(name = "{0} * {1} = {2}")
        @DisplayName("تست پارامتری ضرب")
        @CsvSource({
            "2.5, 4.0, 10.0",
            "-3.0, 3.0, -9.0",
            "0.5, 0.5, 0.25"
        })
        void testParameterizedMultiplication(double a, double b, double expected) {
            assertEquals(expected, calculator.multiply(a, b), 0.0001);
        }
    }

    @Nested
    @DisplayName("گروه تست‌های عملگر تقسیم (Division) و خطاها")
    class DivisionTests {

        @Test
        @DisplayName("تقسیم عادی و تقسیم با حاصل اعشاری")
        void testNormalDivision() {
            assertEquals(2.0, calculator.divide(10, 5));
            assertEquals(-2.0, calculator.divide(-10, 5));
            assertEquals(0.0, calculator.divide(0, -5));
            assertEquals(3.333, calculator.divide(10, 3), 0.001);
        }

        @Test
        @DisplayName("تست پرتاب استثنا هنگام تقسیم بر صفر")
        void testDivisionByZeroThrowsException() {
            IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {
                calculator.divide(10, 0);
            });
            assertEquals("تقسیم بر صفر مجاز نیست!", ex1.getMessage());

            assertThrows(IllegalArgumentException.class, () -> {
                calculator.divide(-10, -0.0);
            });
        }

        @Test
        @DisplayName("تست ورودی‌های نامعتبر (NaN)")
        void testInvalidNaNInputs() {
            assertThrows(IllegalArgumentException.class, () -> calculator.add(Double.NaN, 5));
            assertThrows(IllegalArgumentException.class, () -> calculator.subtract(5, Double.NaN));
            assertThrows(IllegalArgumentException.class, () -> calculator.multiply(Double.NaN, 2));
            assertThrows(IllegalArgumentException.class, () -> calculator.divide(Double.NaN, 2));
        }
    }
}
