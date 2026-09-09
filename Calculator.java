
package javaapplication41;
import java.util.Scanner;

// Node class for the doubly linked list
class Node {
    char data;
    Node prev, next;

    Node(char data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// Node class for the stack
class StackNode {
    int data;
    StackNode next;

    StackNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// Custom stack class
class CustomStack {
    private StackNode top;

    public CustomStack() {
        top = null;
    }

    public void push(int data) {
        StackNode newNode = new StackNode(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) {
            throw new RuntimeException("Stack underflow");
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public int peek() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class Calculator {
    private Node head;
    private Node tail;

    public Calculator() {
        head = null;
        tail = null;
    }

    public void append(char data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public int calculate() {
        if (head == null)
            return 0;

        CustomStack stack = new CustomStack(); // Custom stack for calculation
        int result = 0;
        char operation = '+';

        Node current = head;
        while (current != null) {
            if (Character.isDigit(current.data)) { // Character checking
                int num = 0;
                while (current != null && Character.isDigit(current.data)) {
                    num = num * 10 + (current.data - '0');
                    current = current.next;
                }

                switch (operation) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                    case '%':
                        stack.push(stack.pop() % num);
                        break;
                }
            } else if (current.data == '+' || current.data == '-' || current.data == '*' || current.data == '/' || current.data == '%') {
                operation = current.data;
                current = current.next;
            } else {
                current = current.next;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        // Clear the linked list after calculation
        clear();

        return result;
    }

    // Method to clear the linked list
    private void clear() {
        head = null;
        tail = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for input
        Calculator calc = new Calculator();

        while (true) {
            System.out.print("Enter an expression (or press '0' to reset): ");
            String expression = scanner.nextLine();

            if (expression.equals("clear") || expression.equals("0")) {
                calc.clear();
                System.out.println("Calculator cleared.");
            } else if (isValidExpression(expression)) {
                for (int i = 0; i < expression.length(); i++) {
                    char ch = expression.charAt(i);
                    if (ch != ' ') {
                        calc.append(ch);
                    }
                }

                int result = calc.calculate();
                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid input. Please enter a valid arithmetic expression.");
            }
        }
    }

    // Method to validate the expression
    private static boolean isValidExpression(String expression) {
        for (char ch : expression.toCharArray()) {
            if (!Character.isDigit(ch) && ch != '+' && ch != '-' && ch != '*' && ch != '/' && ch != '%' && ch != ' ') {
                return false;
            }
        }
        return true;
    }
}
