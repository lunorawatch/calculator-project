# پروژه درس تست نرم‌افزار: ماشین حساب ۴ عمل اصلی (Java + JUnit 5)

## ۱. ساختار پروژه (بدون پوشه‌های اضافه)
- `pom.xml`: تنظیمات Maven و وابستگی JUnit 5
- `src/main/java/Calculator.java`: منطق اصلی ماشین حساب
- `src/main/java/Main.java`: رابط کاربری کنسول
- `src/test/java/CalculatorTest.java`: مجموعه ۱۹ تست واحد با JUnit 5

---

## ۲. گراف‌های جریان کنترل (Control Flow Graphs - White-Box Testing)

### الف) گراف جریان کنترل متد `divide(double a, double b)`
پیچیدگی سیکلوماتیک مک‌کیب: $V(G) = E - N + 2 = 9 - 8 + 2 = 3$

```mermaid
flowchart TD
    N1([1: Start / validateInputs]) --> N2{2: b == 0.0 ?}
    N2 -- True --> N3[3: throw IllegalArgumentException]
    N2 -- False --> N4[4: result = a / b]
    N4 --> N5{5: result == 0.0 ?}
    N5 -- True --> N6[6: return 0.0]
    N5 -- False --> N7[7: return result]
    N3 --> N8([8: Exit])
    N6 --> N8
    N7 --> N8
```

**مسیرهای مستقل (Basis Paths):**
1. **مسیر ۱ (تقسیم بر صفر):** `1 → 2 → 3 → 8` (تست‌شده در `testDivisionByZeroThrowsException`)
2. **مسیر ۲ (صورت صفر):** `1 → 2 → 4 → 5 → 6 → 8` (تست‌شده در `divide(0, -5)`)
3. **مسیر ۳ (تقسیم عادی):** `1 → 2 → 4 → 5 → 7 → 8` (تست‌شده در `divide(10, 5)`)

---

### ب) گراف جریان کنترل متد `multiply(double a, double b)`
پیچیدگی سیکلوماتیک: $V(G) = 6 - 6 + 2 = 2$

```mermaid
flowchart TD
    M1([1: Start / validateInputs]) --> M2[2: result = a * b]
    M2 --> M3{3: result == 0.0 ?}
    M3 -- True --> M4[4: return 0.0]
    M3 -- False --> M5[5: return result]
    M4 --> M6([6: Exit])
    M5 --> M6
```

---

### ج) گراف جریان کنترل متد `validateInputs(double a, double b)`
پیچیدگی سیکلوماتیک: $V(G) = 5 - 5 + 2 = 2$

```mermaid
flowchart TD
    V1([1: Start validateInputs]) --> V2{2: isNaN a OR isNaN b ?}
    V2 -- True --> V3[3: throw IllegalArgumentException]
    V2 -- False --> V4[4: Normal Return]
    V3 --> V5([5: Exit])
    V4 --> V5
```

---

### د) گراف جریان کنترل متدهای خطی (`add` و `subtract`)
پیچیدگی سیکلوماتیک: $V(G) = 3 - 4 + 2 = 1$

```mermaid
flowchart LR
    L1([1: Start]) --> L2[2: validateInputs] --> L3[3: return a ± b] --> L4([4: Exit])
```

---

## ۳. گراف فراخوانی پروژه (Call Graph)

```mermaid
flowchart LR
    Test[CalculatorTest] --> Add[Calculator.add]
    Test --> Sub[Calculator.subtract]
    Test --> Mul[Calculator.multiply]
    Test --> Div[Calculator.divide]
    Main[Main] --> Add
    Main --> Sub
    Main --> Mul
    Main --> Div
    Add --> Val[Calculator.validateInputs]
    Sub --> Val
    Mul --> Val
    Div --> Val
```

## ۴. دستورات اجرا
- اجرای تست‌ها: `mvn test`
- اجرای برنامه: `mvn compile exec:java -Dexec.mainClass="Main"`
