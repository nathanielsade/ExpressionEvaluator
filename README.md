#Expression Calculator

This project is an implementation of an expression calculator in Java. It supports various mathematical operations such as addition, multiplication, division, logarithms, trigonometric functions, and more. The calculator can differentiate and simplify expressions and evaluate them with given variable assignments.

## Setup Process

### How to Run the Project

<details>
<summary>Instructions</summary>

1. **Navigate to the Project Directory:**
    ```sh
    cd path/to/EX4
    ```

2. **Compile the Java Files:**
    ```sh
    javac -d bin src/*.java
    ```

3. **Run the Main Class:**
    ```sh
    java -cp bin ExpressionsTest
    ```

</details>

## Description of Classes

<details>
<summary>Class Descriptions</summary>

- **ExpressionsTest.java:** Entry point of the application, responsible for running the expression tests.
- **Expression.java:** Interface representing a mathematical expression.
- **BinaryExpression.java:** Abstract class for binary operations.
- **UnaryExpression.java:** Abstract class for unary operations.
- **Plus.java:** Represents the addition operation.
- **Minus.java:** Represents the subtraction operation.
- **Mult.java:** Represents the multiplication operation.
- **Div.java:** Represents the division operation.
- **Pow.java:** Represents the power function.
- **Sin.java:** Represents the sine function.
- **Cos.java:** Represents the cosine function.
- **Log.java:** Represents the logarithm function.
- **Neg.java:** Represents the negation operation.
- **Num.java:** Represents a numerical constant.
- **Var.java:** Represents a variable.

</details>

## Simulation

![Simulation](./path/to/2024-07-11170039-ezgif.com-optimize.gif)
![Simulation](./path/to/2024-07-11171415-ezgif.com-optimize.gif)

## Dependencies

- **Java JDK 19.0.2:** Make sure you have Java installed.
- **PowerShell:** To run the provided commands on Windows.

## Author

Nethaniel Sade
