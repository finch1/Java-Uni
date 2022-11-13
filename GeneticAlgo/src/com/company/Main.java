package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    // random number generator object
    static Random _random = new Random();

    // shuffle cities
    static int[] Shuffle(int[] array)
    {
        int n = array.length;
        int i = 0, r = 0;
        r = _random.nextInt(n);
        i = _random.nextInt(n);
        int t = array[r];
        array[r] = array[i];
        array[i] = t;

        return array;
    }

    // swap two elements
    static int[] Swap(int[] array, int indexA, int indexB)
    {
        int t = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = t;

        return array;
    }

    // portion of array to another array
    static int[]  Segment(int[] array, int indexA, int indexB)
    {
        int len = indexB - indexA;
        int[] temp = new int[len];

        for (int i = 0; i < len; i++)
        {
            temp[i] = array[i+ indexA];
        }

        return temp;
    }

    // mutation
    static void mutation(int totalPopulation, double mutationRate, int[] order)
    {
        for (int i = 0; i < totalPopulation; i++)
        {
            if (_random.nextDouble() < mutationRate)
            {
                int indexA = (int)Math.floor(ThreadLocalRandom.current().nextDouble(order.length));
                int indexB = (int)Math.floor(ThreadLocalRandom.current().nextDouble(order.length));

                Swap(order, indexA, indexB);
            }
        }
        //return order;
    }

    // cross over
    static int[] crossOver(int[] orderA, int[] orderB)
    {
        // here we are picking a random spot
        int start = 0;
        int end = 0;
        start = (int) Math.floor(ThreadLocalRandom.current().nextDouble(orderA.length));
            //end = (int) Math.floor(ThreadLocalRandom.current().nextDouble(start + 1, orderA.length)); // int case start equals end
        end = (int) Math.floor(ThreadLocalRandom.current().nextDouble(start, orderA.length)); // int case start equals end

        int[] neworder = Segment(orderA, start, end);
        boolean contains = true;
        int index = neworder.length -1;
        neworder = Arrays.copyOf(neworder, orderB.length);
        //Array.Resize(ref neworder, orderB.length);

        for (int i = 0; i < orderB.length; i++)
        {
            for (int j = 0; j < neworder.length; j++) // check if contains
            {
                if (neworder[j] == i)
                {
                    contains = true;
                    break;
                }
                else
                    contains = false;
            }
            if (!contains)
                neworder[++index] = i;

            contains = false; // check again

        }

        return neworder;
    }

    // Selection Pool Algorithm
    static int pickOne(int[][] list, double[] prob)
    {
        int index = 0;
        double r = _random.nextDouble();

        while (r > 0)
        {
            r = r - prob[index];
            index++;
        }
        index--; // to counter the final increment
        return index;
    }

    // minimum value in array
    public static double getMinValue(double[] numbers){
        double minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    // index of element in array
    public static int indexOf(double[] numbers, double element){
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] == element){
                return i;
            }
        }
        return -1;
    }

    // clear whole array - double
    public static void clear(double[] numbers, double clearValue){
        for(int i=1;i<numbers.length;i++){
            numbers[i] = clearValue;
        }
    }

    // make copy of array
    public static void cloneTwoD(double[][] source, double[][] destination){
        for (int i = 0; i < destination.length; ++i) {
            // allocating space for each row of destination array
            destination[i] = new double[source[i].length];
            for (int j = 0; j < destination[i].length; ++j) {
                destination[i][j] = source[i][j];
            }
        }
    }

    public static void main(String[] args) {

        double recordDistance = Double.MAX_VALUE;
        int fitnessVar = 16;
        double mutationRate = 0.02;


        Map<Integer, double[][]> records = new HashMap<Integer, double[][]>();
        // read text file
        try {
            File file = new File("D:\\test1.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                // remove multiple spaces
                st = st.replaceAll("\\s{2,}", " ").trim();
                st = st.replace('\t' , ',');
                st = st.replace(' ' , ',');
                String[] values = st.split(",");
                if(!records.containsKey(values[0]))
                    records.put(Integer.parseInt(values[0]),  new double[][] {{Double.parseDouble(values[1]),Double.parseDouble(values[2])}});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // array of cities
        double[][] nodes = new double[records.size()][2];
        // total population for every run
        int totalPopulation = records.size() * 500;

        // load cities into array
        int _i = Collections.min(records.keySet());
        for (int i = _i; i <= records.size(); i++) {
            nodes[i-1] = records.get(i)[0];
        }

        // number of cities
        int vertLen = nodes.length;

        // distance matirx
        double[][] weigth = new double[vertLen][vertLen];

        // best distance and route holder
        int[] bestEver = new int[vertLen];

        long timer = System.nanoTime();

        // calculate adjacency matrix
        for (int i = 0; i < vertLen; i++)
        {
            for (int j = 0; j < vertLen; j++)
            {
                // zero out same city combination in matrix
                if (i == j)
                    weigth[i][j] = 0;

                else
                {   // calculate weights for all coordinates
                    double x = nodes[i][0] - nodes[j][0];
                    double y = nodes[i][1] - nodes[j][1];

                    double answer = Math.sqrt(Math.pow(x, 2.0d) + Math.pow(y, 2.0d));

                    weigth[i][j] = answer; // store weight
                }
            }
        }

        // order of cities by index
        int[] order = new int[vertLen];
        for (int i = 0; i < vertLen; i++)
        {
            order[i] = i;
        }

        // population array
        int[][] population = new int[totalPopulation][vertLen];
        // first set of population shuffled cities
        for (int i = 0; i < totalPopulation; i++)
        {
            population[i] = Shuffle(Arrays.copyOf(order, order.length));
        }

        double[] fitness = new double[totalPopulation];

        // Calculate Fitness
        double currentRecord = Double.MAX_VALUE;
        double sum = 0;
        int k = 0;

        for (int i = 0; i < population.length; i++)
        {
            sum = 0;
            k = 0;
            // obtain distance from adjecency matrix
            for (; k < population[i].length-1; k++)
            {
                sum += weigth[population[i][k]][population[i][k+1]];
            }

            sum += weigth[population[i][k]][ population[i][0]];

            if (sum < recordDistance)
            {
                recordDistance = sum;
                bestEver = population[i];
            }
            // for every element in the population log a fitness score in array
            fitness[i] = 1 / (Math.pow(sum, fitnessVar) + 1); // improved fitness function
        }

        sum = 0;
        // normalize fitness across array
        for (int i = 0; i < fitness.length; i++)
        {
            sum += fitness[i];
        }
        for (int i = 0; i < fitness.length; i++)
        {
            fitness[i] = fitness[i] / sum;
        }

        // record distance holder
        double prev_recordDistance = Double.MAX_VALUE;
        long finish = 0, start = 0;

        do{
            start = System.nanoTime();

            for (var i = 0; i < population.length; i++)
            {
                // pick a route with higher fitness still maintaining equal probability
                int[] orderA = population[pickOne(population, fitness)];
                int[] orderB = population[pickOne(population, fitness)];
                // cross over
                order = crossOver(orderA, orderB);
                // mutate
                mutation(totalPopulation, mutationRate, order);
                // update route
                population[i] = order;
            }


            sum = 0;
            k = 0;
            // Calculate Fitness
            for (int i = 0; i < population.length; i++)
            {
                sum = 0;
                k = 0;
                for (; k < population[i].length - 1; k++)
                {
                    sum += weigth[population[i][k]][population[i][k + 1]];
                }

                sum += weigth[population[i][k]][population[i][0]];

                if (sum < recordDistance)
                {
                    recordDistance = sum;
                    bestEver = population[i];
                }

                fitness[i] = 1 / (Math.pow(sum, fitnessVar) + 1);
            }

            sum = 0;
            // normalize
            for (int i = 0; i < fitness.length; i++)
            {
                sum += fitness[i];
            }
            for (int i = 0; i < fitness.length; i++)
            {
                fitness[i] = fitness[i] / sum;
            }

            finish = System.nanoTime();

            // keep track of best route and distance obtained
            if((prev_recordDistance - recordDistance) > 30){
                for (int i = 0; i < bestEver.length; i++) // to match cities starting from 1
                {
                    System.out.print(bestEver[i]+1 + ",");
                }
                System.out.println(bestEver[0] +1);
                System.out.println("Total Distance : " + recordDistance);
                System.out.println("Time Elapsed = " + (finish - start)/ 1000000);
                prev_recordDistance = recordDistance;
            }
        }while ((finish - timer)/ 1000000000 <= 240);

    }
}
