package acsl.numble;

import java.util.ArrayList;
import java.util.Arrays;

public class Numble {

    private final String i1;
    private final String i2;

    public Numble (String input1, String input2) {
        i1 = input1;
        i2 = input2;
    }

    /**
     * sums each integer in an array
     * @param data an int array to be summed up
     * @return sum
     */
    static int arraySum(int[] data) {
        int ret = 0;
        for (int i : data) {
            ret += i;
        }
        return ret;
    }

    /**
     * Recursively determines every combination that satisfies numble
     * @param list Master list to be appended
     * @param data Complete list of all data at disposal
     * @param newAddition Precondition: empty int[]
     * @param start Precondition: 0
     * @param idx Precondition: 0
     * @param mustContain Crossing digit that must be included
     * @param end The maximum length
     */
    private static void helper (ArrayList<int[]> list, int[] data, int[] newAddition, int start, int idx, int mustContain, int end) {
        // base case is when the targetNums is fulfilled, we are at the end of the index, and the sum % 5 == 0
        if (start >= end) {
            boolean contains = false;
            for (int i : newAddition) {
                if (i == mustContain)
                    contains = true;
            }
            if (arraySum(newAddition) % 5 == 0 && contains)
                list.add(newAddition.clone());
        } else {
            helper(list, data, newAddition, start + 1, idx, mustContain, end);
            int[] temp = newAddition;

            temp[idx] = data[start];
            helper(list, data, temp, start + 1, idx + 1, mustContain, end);
        }
    }

    /**
     * Returns the numble combination with the maximum sum
     * @param input numbers in String form, no more than one 0
     * @param n length of return
     * @param x crossing digit that must be included
     * @return int[] of the greatest possible numble combo
     */
    static int[] result (String input, int n, int x) {
        int[] inputArray = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            inputArray[i] = Character.getNumericValue(input.charAt(i));
        }
        Arrays.sort(inputArray);
        // Reverse inputArray to be from highest -> lowest
        for (int i = 0; i < inputArray.length / 2; i++) {
            int temp = inputArray[i];
            inputArray[i] = inputArray[inputArray.length - 1 - i];
            inputArray[inputArray.length - 1 - i] = temp;
        }

        ArrayList<int[]> combos = new ArrayList<>();
        helper(combos, inputArray, new int[n], 0, 0, x, n);

        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < combos.size(); i++) {
            if (arraySum(combos.get(i)) > max) {
                maxIndex = i;
                max = arraySum(combos.get(i));
            }
        }
        return combos.get(maxIndex);
    }

    public static void test() {
        Numble testBoard = new Numble("9768014", "6874514");
        testBoard.processBoard(7, 7, 7);
    }

    /**
     * Print a new numble combination
     * @param len1 horizontal length
     * @param len2 vertical length
     * @param cross digit at which they cross
     */
    void processBoard (int len1, int len2, int cross) {
        int[] horizontal = result(i1, len1, cross);
        int[] vertical = result(i2, len2, cross);
        int[][] fin = new int[len2][len1];
        int crossX = -1;
        int crossY = -1;
        for (int i = 0; i < len1; i++) {
            if (horizontal[i] == cross)
                crossX = i;
        }
        for (int i = 0; i < len2; i++) {
            if (vertical[i] == cross)
                crossY = i;
        }

        // process into 2d array
        // y axis
        for (int i = 0; i < fin.length; i++) {
            if (i == crossY)
                fin[i] = horizontal;
            else
                // x axis
                for (int j = 0; j < fin[i].length; j++)
                    if (j == crossX)
                        fin[i][j] = vertical[i];
        }

        System.out.println();
        // print 2d array with spaces in where's empty
        for (int i = 0; i < fin.length; i++) {
            for (int j : fin[i]) {
                if (j == 0 && i != crossY)
                    System.out.print(" ");
                else System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
