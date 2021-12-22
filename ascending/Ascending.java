package acsl.ascending;

import java.util.ArrayList;

public class Ascending {

    String startNum;
    String numToProcess;

    public Ascending (String number) {
        startNum = number.substring(1,
                1 + Character.getNumericValue(number.charAt(0)));
        numToProcess = number.substring(Character.getNumericValue(number.charAt(0)) + 1);
        numToProcess = new StringBuilder(numToProcess).reverse().toString();
    }

    public void printOutput() {
        ArrayList<String> output = new ArrayList<>();
        output.add(startNum);
        String mostRecent = output.get(0);
        int digAmount = mostRecent.length();
        for (int i = 0; i + digAmount < numToProcess.length(); i += digAmount) {
            int currentNum = Integer.parseInt(numToProcess.substring(i, i + digAmount));
            while (currentNum <= Integer.parseInt(mostRecent)) {
                digAmount++;
                currentNum = Integer.parseInt(numToProcess.substring(i, i + digAmount));
            }
            output.add(currentNum + "");
            mostRecent = currentNum + "";
        }
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i) + " ");
        }
        System.out.println();
    }
}
