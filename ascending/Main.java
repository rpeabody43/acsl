package acsl.ascending;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
//        Ascending test = new Ascending("33984567176534321");
//        test.printOutput();

        System.out.println("Inputs:");
        Scanner sc = new Scanner(System.in);
        Ascending[] ascendings = new Ascending[5];
        for (int i = 0; i < 5; i++) {
            String input = sc.nextLine();
            ascendings[i] = new Ascending(input);
        }

        for (Ascending i : ascendings) {
            i.printOutput();
        }
    }
}
