package chapter3;

public class C3 {
    public static void main(String[] args) {
        MyStack<Integer> m = new MyStack<>();
        for (int i = 0; i < 10; i++) {
            m.push(i);
        }
    }
}
