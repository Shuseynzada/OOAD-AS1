# Random Number Generator Statistics (Generator)

Name: Shamkhal Huseynzade  
Course: CSCI-3612 - 20964  
Project: Random Number Generator Statistics  
Date: 09/02/2026  

---

## Overview
This project implements a Java program that generates random `double` values in the range **[0, 1)** using three built-in Java random number generators and analyzes the generated data using descriptive statistics.

Random number generators used:
1. java.util.Random
2. Math.random()
3. java.util.concurrent.ThreadLocalRandom

For each generator, the program generates random values for three sample sizes:
- n = 10
- n = 1000
- n = 10000

For each run, the program computes and displays:
- n (count)
- mean
- sample standard deviation
- minimum
- maximum

As n increases:
- min approaches 0
- max approaches 1
- mean approaches 0.5
- stddev approaches about 0.29

---

## Files
Generator.java — single required class containing:
- populate(int n, int randNumGen)
- statistics(ArrayList<Double> randomValues)
- display(ArrayList<Double> results, boolean headerOn, String genName)
- execute()
- main(String[] args)

Note: The display method includes an extra parameter (genName) to show which generator produced the results.

---

## How to Compile and Run

### Compile
```
javac Generator.java
```
### Run
```
java Generator
```
---

## Sample Output Format
Your numbers will be different every run due to randomness:

```text
Generator                 n          mean         stddev            min            max
------------------ ---------- -------------- -------------- -------------- --------------
java.util.Random          10     0.48...        0.30...        0.02...        0.97...
Math.random()             10     0.52...        0.25...        0.10...        0.89...
ThreadLocalRandom         10     0.49...        0.31...        0.05...        0.95...
...

```

## Repository Structure

```
.
├── Generator.java
└── READM
