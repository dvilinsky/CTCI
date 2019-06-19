package chapter3;

public class C3 {
    public static void main(String[] args) {
        ThreeStack stack = new ThreeStack(10);
        stack.push(1, 0);
        stack.push(2, 0);
        stack.push(3, 0);
        stack.push(4, 0);
        stack.push(5, 1);
        stack.push(22, 1);
        stack.push(19, 1);
        stack.push(9, 2);
        stack.push(56, 2);
        stack.push(27, 2);
        stack.push(6, 0);
        stack.push(7, 0);
        stack.push(11, 1);
        stack.push(44, 2);
        System.out.println(stack);
    }
}
