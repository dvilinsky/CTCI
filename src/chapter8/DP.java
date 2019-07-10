package chapter8;

public class DP {
    public static void main(String[] args) {
//        System.out.println(fibonacci(7));
        System.out.println(stairs(7));
    }

    private static int fibonacci(int n ) {
        return fibonacci(n, new int[n+1]);
    }

    private static int fibonacci(int i, int[] memo) {
        if (i == 0 || i == 1) {
            return i;
        }
        if (memo[i] == 0) {
            memo[i] = fibonacci(i -1, memo) + fibonacci(i - 2, memo);
        }
        return memo[i];
    }

    /**
     *
     * Running time: O(n) - we have a degenerate tree branches to the left with n levels
     * @param n The number of steps to climb
     * @return The amount of ways to climb those steps
     */
    public static int stairs(int n) {
        return stairs(n, new int[n + 1]);
    }

    private static int stairs(int n, int[] memo) {
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return n;
        } else if (n == 3) {
            return 4;
        } else if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = stairs(n - 1) + stairs(n - 2) + stairs(n - 3);
        return memo[n];
    }
}
