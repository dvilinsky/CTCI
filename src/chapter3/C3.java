package chapter3;

public class C3 {
    public static void main(String[] args) {
        MinStack<Integer> stack = new MinStack<>();
        int[] data = {5, 6, 2, 11, 14, 13, 12, 0, 4, 7};
        for (int i = 0; i < data.length; i++) {
            stack.push(data[i]);
        }
        System.out.println(stack);
        System.out.println(stack.getMin());
        stack.pop(); stack.pop(); stack.pop();
        System.out.println(stack.getMin());
        stack.push(-25);
        System.out.println(stack.getMin());
    }
}
