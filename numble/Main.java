package acsl.numble;

import java.util.Scanner;

public class Main {
    public static void main (String[] args)  {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input: ");
        String[] initialize = sc.nextLine().split(", ");

        Numble board = new Numble(initialize[0], initialize[1]);
        int[][] inputArray = new int[5][3];
        for (int i = 0; i < 5; i++) {
            String[] inputStr = sc.nextLine().split(", ");
            for (int j = 0; j < inputStr.length; j++) {
                inputArray[i][j] = Integer.parseInt(inputStr[j]);
            }
        }
        for (int[] i : inputArray) {
            board.processBoard(i[0], i[1], i[2]);
        }
    }
}

