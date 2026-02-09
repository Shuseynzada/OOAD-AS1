/*
Name: Shamkhal Huseynzade
Project: Random Number Generator Statistics
Class: CSCI-3612 - 20964
Date: 09/02/2026
*/

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Generator {

    // Constants
    public static final double LOWER_BOUND = 0.0;
    public static final double UPPER_BOUND = 1.0;

    public static final int[] N_VALUES = { 10, 1000, 10000 };

    // Lists
    private ArrayList<Double> randomList1;
    private ArrayList<Double> randomList2;
    private ArrayList<Double> randomList3;

    /*
    Creates and returns an ArrayList of n random doubles in [0, 1)
    randNumGen: 1 = java.util.Random, 2 = Math.random(), 3 = ThreadLocalRandom
    */
    static ArrayList<Double> populate(int n, int randNumGen) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0");
        }

        ArrayList<Double> res = new ArrayList<Double>(n);

        switch (randNumGen) {
            case 1: {
                Random rand = new Random();
                for (int i = 0; i < n; i++) {
                    res.add(LOWER_BOUND + rand.nextDouble() * (UPPER_BOUND - LOWER_BOUND));
                }
                break;
            }
            case 2: {
                for (int i = 0; i < n; i++) {
                    res.add(Math.random()*(UPPER_BOUND-LOWER_BOUND) + LOWER_BOUND);
                }
                break;
            }
            case 3: {
                for (int i = 0; i < n; i++) {
                    res.add(ThreadLocalRandom.current().nextDouble(LOWER_BOUND, UPPER_BOUND));
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Wrong key for randNumGen for populate method");
        }

        return res;
    }

    /*
    Calculates [n, mean, sample stddev, min, max]
    */
    static ArrayList<Double> statistics(ArrayList<Double> randomValues) {
        if (randomValues == null || randomValues.isEmpty()) {
            throw new IllegalArgumentException("randomValues must not be empty");
        }

        double n = randomValues.size();
        double sum = 0.0;
        double min = randomValues.get(0);
        double max = randomValues.get(0);

        for (double x : randomValues) {
            sum += x;
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        double mean = sum / n;

        // Sample standard deviation
        double m2 = 0.0;
        for (double x : randomValues) {
            double d = x - mean;
            m2 += d * d;
        }

        double stddev = (n > 1) ? Math.sqrt(m2 / (n - 1)) : 0.0;

        ArrayList<Double> res = new ArrayList<Double>();
        res.add(n);
        res.add(mean);
        res.add(stddev);
        res.add(min);
        res.add(max);
        return res;
    }

    /*
    Displays results in a tabular format.
    headerOn prints the header line.
    */
    static void display(ArrayList<Double> results, boolean headerOn, String genName) {
        if (results == null || results.size() < 5) {
            throw new IllegalArgumentException("results must contain [n, mean, stddev, min, max]");
        }

        long n = Math.round(results.get(0));
        double mean = results.get(1);
        double stddev = results.get(2);
        double min = results.get(3);
        double max = results.get(4);

        if (headerOn) {
            System.out.printf("%-18s %10s %14s %14s %14s %14s%n",
                    "Generator", "n", "mean", "stddev", "min", "max");
            System.out.printf("%-18s %10s %14s %14s %14s %14s%n",
                    "------------------", "----------", "--------------", "--------------", "--------------", "--------------");
        }

        System.out.printf("%-18s %10d %14.8f %14.8f %14.8f %14.8f%n",
                genName, n, mean, stddev, min, max);
    }

    /*
    Calls populate, statistics, and display for all combinations of n values and RNGs.
    Produces 9 results: 3 n-values x 3 generators.
    */
    void execute() {
        boolean headerPrinted = false;

        for (int n : N_VALUES) {
            // java.util.Random
            randomList1 = populate(n, 1);
            display(statistics(randomList1), !headerPrinted, "java.util.Random");
            headerPrinted = true;

            // Math.random()
            randomList2 = populate(n, 2);
            display(statistics(randomList2), false, "Math.random()");

            // ThreadLocalRandom
            randomList3 = populate(n, 3);
            display(statistics(randomList3), false, "ThreadLocalRandom");
        }
    }

    public static void main(String[] args) {
        Generator g = new Generator();
        g.execute();
    }
}
