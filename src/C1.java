import java.util.*;

public class C1 {
    public static void main(String[] args) {
        int[][] matrix = {{5, 6, 0},
                          {2, 9, 8},
                          {0, 1, 4},
                          {12, 19, 3},
                          {7, 14, 20},
                          {0, 6, 0}};
        System.out.println(isRotation("erbottlewat", "waterbottle"));
        System.out.println(isRotation("waterbottle", "erbottlewat"));
        System.out.println(isRotation("rotate", "taerot"));
    }

    /* Problem 1.1
     * Time: O(n^2)
     * Space: O(1)
     */
    public static boolean isUniqueChars(String s){
        //O(n) space, O(n) time
//        List<Character> l = new ArrayList<>();
//        for (char c : s.toCharArray()) {
//            l.add(c);
//        }
//        return s.length() == (new HashSet<Character>(l)).size();
        //O(1) space, O(n^2) time
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = i+1; j < s.length(); j++) {
                if (c == s.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    /* Problem 1.2
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean arePermutations(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (char s : s1.toCharArray()) {
            set1.add(s);
        }
        for (char s : s2.toCharArray()) {
            set2.add(s);
        }
        return set1.equals(set2);
    }

    /* Problem 1.3
     * Time: O(n)
     * Space: O(n)
     */
    public static String replace(String s1, char toReplace) {
        char[] c = s1.toCharArray();
        int numSpaces = 0;
        int trueLength = s1.trim().length();
        for (int i = 0; i < trueLength; i++) {
            if (s1.charAt(i) == toReplace) {
                numSpaces++;
            }
        }
        int index = trueLength + numSpaces *2;
        for (int i = trueLength - 1; i >= 0; i--) {
            if (c[i] == ' ') {
                c[index - 1] = '0';
                c[index - 2] = '2';
                c[index - 3] = '%';
                index = index - 3;
            } else {
                c[index - 1] = c[i];
                index--;
            }
        }
        return toString(c);
    }

    private static String toString(char[] c) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            s.append(c[i]);
        }
        return s.toString();
    }

    private static List<Character> toList(char[] c) {
        List<Character> l = new ArrayList<>();
        for (int i = 0; i <c.length ; i++) {
            l.add(c[i]);
        }
        return l;
    }

    /**
     * Problem 1.4
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean isPalindromePermutation(String s) {
        if (s.length() == 0) {
            return true;
        }
        String s1 = s.toLowerCase().replaceAll(" ", "");
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (charCount.containsKey(s1.charAt(i))) {
                charCount.put(s1.charAt(i), charCount.get(s1.charAt(i)) + 1);
            } else {
                charCount.put(s1.charAt(i), 1);
            }
        }
        Set<Integer> values = new HashSet<>(charCount.values());
        return hasMaxOneOdd(values);
    }

    private static boolean hasMaxOneOdd(Set<Integer> values) {
        int numOdd = 0;
        for (int i : values) {
            if (i % 2 != 0) {
                numOdd++;
            }
        }
        return numOdd <= 1;
    }

    /**
     * Problem 1.5
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean editDistanceLessThanOne(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() == s2.length()) {
            //if they're equal in length, any changes must be substitutions
            return isOneSubAway(s1, s2);
        }
        String shorter = s1.length() <= s2.length() ? s1 : s2;
        String longer = s1.length() <= s2.length() ? s2 : s1;
        return isOneInsertOrDeleteAway(shorter, longer);
    }

    //When method is called, we know shorter and longer are not equal, lengths differ by exactly 1
    private static boolean isOneInsertOrDeleteAway(String shorter, String longer) {
        char[] shorterArray = shorter.toCharArray();
        char[] longerArray = longer.toCharArray();
        int numDifferences = 0, index = 0;
        for (int i = 0; i < shorterArray.length && index < longerArray.length; i++) {
            if (shorterArray[i] != longerArray[index]) {
                numDifferences++;
                i--; //stay on this character
            }
            index++;
        }
        return numDifferences <= 1;
    }


    //We know s1.length() == s2.length();
    private static boolean isOneSubAway(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int numDifferences = 0;
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                numDifferences++;
            }
        }
        return numDifferences == 1;
    }

    /**
     * Problem 1.6
     * Time: O(n)
     * Space: O(n)
     */
    public static String compress(String s) {
        if (s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        char current = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (current == s.charAt(i)) {
                count++;
            } else {
                sb.append(current);
                sb.append(count);
                count = 1;
                current = s.charAt(i);
            }
        }
        sb.append(current).append(count);
        return sb.length() < s.length() ? sb.toString() : s;
    }

    /**
     * Problem 1.7
     * Time: O(n^2)
     * Space: O(1)
     */
    public static void rotate(int[][] image, boolean right) {
        if (right) {
            rotateRight(image);
        } else {
            rotateLeft(image);
        }
    }

    private static void rotateRight(int[][] image) {
        int numLayers = image.length / 2; //how do I know there are n/2 layers? Conjecture
        for (int layer = 0; layer < numLayers; layer++) {
            int lastIndex = image.length - layer - 1;
            for (int j = 0; j < lastIndex - layer; j++) {
                //top -> right
                int temp = image[j+layer][lastIndex];
                image[j+layer][lastIndex] = image[layer][j+layer];

                //right -> bottom
                int temp2 = image[lastIndex][lastIndex-j];
                image[lastIndex][lastIndex-j] = temp;

                //bottm -> left
                int temp3 = image[lastIndex-j][layer];
                image[lastIndex-j][layer] = temp2;

                //left -> top - no temp variable because val at image[layer][j] already in position
                image[layer][j+layer] = temp3;
            }
        }
    }

    //TODO: Implement somdeday
    private static void rotateLeft(int[][] image) {

    }

    public static void print2dArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i][0]);
            for (int j = 1; j < a[i].length; j++) {
                System.out.print(" " + a[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Problem 1.8
     * Time: O(n^2)
     * Space: O(matrix.length + matrix[0].length)
     */
    public static void zero(int[][] matrix) {
        Set<Integer> rowsWithZeros = new TreeSet<>();
        Set<Integer> columnsWithZeros = new TreeSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsWithZeros.add(i);
                    columnsWithZeros.add(j);
                }
            }
        }
        for (int row : rowsWithZeros) {
            setRowToZero(matrix, row);
        }
        for (int column : columnsWithZeros) {
            setColumnToZero(matrix, column);
        }
    }

    private static void setRowToZero(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = 0;
        }
    }

    private static void setColumnToZero(int[][] matrix, int column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
    }

    /*
     * Complete problem 1.9
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String s3 = s1+s1;
        return s3.contains(s2);
    }
}
