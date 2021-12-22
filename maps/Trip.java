package acsl.maps;

import java.util.HashMap;
import java.util.Arrays;

public class Trip {

    private static final HashMap<Character, Integer> cities = new HashMap<>();
    private static final HashMap<Character, Integer> cars   = new HashMap<>();
    private static final HashMap<Character, Integer> roads  = new HashMap<>();

    char from;
    char to;
    char car;
    char road;
    double gas;

    public Trip (char cityFrom, char cityTo, char carType, char roadType, double gasPrice) {
        int totalDistance = 0;
        // Initialize cities
        for (int i = 0; i < 7; i++) {
            final int[] distances = {0, 450, 140, 120, 320, 250, 80};
            totalDistance += distances[i];
            String cityLetters = "ABCDEFG";

            cities.put(cityLetters.charAt(i), totalDistance);
        }
        // Initialize cars
        for (int i = 0; i < 4; i++) {
            String carLetters = "CMFV";
            final int[] mpg = {28, 25, 22, 20};
            cars.put(carLetters.charAt(i), mpg[i]);
        }
        // Initialize roads
        for (int i = 0; i < 4; i++) {
            String roadLetters = "IHMS";
            int[] mph = {65, 60, 55, 45};
            roads.put(roadLetters.charAt(i), mph[i]);
        }

        from = cityFrom;
        to = cityTo;
        car = carType;
        road = roadType;
        gas = gasPrice;
    }

    private String formatTime (double hours) {
        int timeTruncate = (int) hours;
        double remainder = hours - timeTruncate;
        remainder = Math.round(remainder * 60);
        String remainStr = String.valueOf((int) remainder);
        if (remainder / 10 < 1) {
            remainStr = "0" + (int) remainder;
        }
        return timeTruncate + ":" + remainStr;
    }

    void printOutput () {
        int distance = Math.abs(cities.get(from) - cities.get(to));
        String time = formatTime((double) distance / roads.get(road));
        double price = Math.round(((double) distance / cars.get(car)) * gas * 100.0) / 100.0;
        String priceStr = String.valueOf(price);
        if (priceStr.contains(".")) {
            while (priceStr.split("\\.")[1].length() < 2) {
                priceStr += "0";
            }
        }
        else {
            priceStr += ".00";
        }
        System.out.println(distance + ", " + time + ", $" + priceStr);
    }
}
