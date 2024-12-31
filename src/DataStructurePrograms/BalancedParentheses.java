package DataStructurePrograms;
class Stack<T> {
    private T[] stack;
    private int top;
    private int capacity;

    public Stack(int size) {
        capacity = size;
        stack = (T[]) new Object[capacity];
        top = -1;
    }
    public void push(T item) {
        if (top == capacity - 1) {
            System.out.println("Stack overflow");
        } else {
            stack[++top] = item;
        }
    }
    public T pop() {
        if (top == -1) {
            System.out.println("Stack underflow");
            return null; // Return null if the stack is empty
        } else {
            return stack[top--];
        }
    }
    public T peek() {
        if (top == -1) {
            return null;
        } else {
            return stack[top];
        }
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public int size() {
        return top + 1;
    }
}

public class BalancedParentheses {
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>(expression.length());
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false; // Unmatched parentheses
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expression = "(5+6)*(7+8)/(4+3)";
        boolean result = isBalanced(expression);
        if (result) {
            System.out.println("The arithmetic expression is balanced.");
        } else {
            System.out.println("The arithmetic expression is not balanced.");
        }
    }
}
