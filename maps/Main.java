package acsl.maps;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Inputs: ");
        Trip[] trips = new Trip[5];

        for (int i = 0; i < trips.length; i++) {
            String[] params = sc.nextLine().split(", ");
            char city1 = params[0].charAt(0);
            char city2 = params[1].charAt(0);
            char car = params[2].charAt(0);
            char road = params[3].charAt(0);
            double price = Double.parseDouble(params[4]);

            trips[i] = new Trip(city1, city2, car, road, price);
        }

        for (Trip i : trips) {
            i.printOutput();
        }
    }
}
