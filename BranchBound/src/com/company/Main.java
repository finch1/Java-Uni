package com.company;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    // random number generator object
    static Random _random = new Random();

    // shuffle cities
    static void Shuffle(double[][] array)
    {
        int n = array.length;
        int i = 0, r = 0;
        r = _random.nextInt(n);
        i = _random.nextInt(n);
        double t = array[r][0];
        double u = array[r][1];
        array[r][0] = array[i][0];
        array[r][1] = array[i][1];
        array[i][0] = t;
        array[i][1] = u;

        //return array;
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
        for(int i=0;i<numbers.length;i++){
            numbers[i] = clearValue;
        }
    }

    // clear whole array - int
    public static void clearI(int[] numbers, int clearValue){
        for(int i=0;i<numbers.length;i++){
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

        Map<Integer, double[][]> records = new HashMap<Integer, double[][]>();
        // read text file
        try {
            File file = new File("D:\\test4-19.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                // remove multiple spaces
                st = st.replaceAll("\\s{2,}", " ").trim();
                // replace tabs and spaces
                st = st.replace('\t' , ',');
                st = st.replace(' ' , ',');
                String[] values = st.split(",");
                // remove dublicate cities
                if(!records.containsKey(values[0]))
                    records.put(Integer.parseInt(values[0]),  new double[][] {{Double.parseDouble(values[1]),Double.parseDouble(values[2])}});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // array of cities
        double[][] nodes = new double[records.size()][2];

        int _i = Collections.min(records.keySet());
        // load cites from text file
        for (int i = _i; i <= records.size(); i++) {
            nodes[i-1] = records.get(i)[0];
        }

        // number of cities
        int vertLen = nodes.length;
        // distance between cities matrix for calculations
        double[][] weigth = new double[vertLen][vertLen];
        // original distance betwen cities matrix
        double[][] original_weigth = new double[vertLen][vertLen];
        // reduced matrix between cities
        double[][][] path = new double[vertLen][vertLen][vertLen];

        // keep track of visited cities and remaining ones
        boolean[] depart, arrive;
        depart = new boolean[vertLen];
        arrive = new boolean[vertLen];

        // keep track of minimum distance calculated
        double[] pathcost = new double[vertLen];
        // keep track of optimal route while looping
        int[] route = new int[vertLen+1];
        // keep track of optimal route
        int[] bestEver = new int[vertLen+1];

        // temporary calculation matrix
        double[][] weigth_Copy = new double[vertLen][vertLen];
        // loop variables
        double min =  Double.MAX_VALUE , reducedCost = 0, nodeReducedCost = 0;
        // distance holder, best ever distance holder
        double totalDistance = 0, recordDistance = Double.MAX_VALUE;

        // timing variables
        long timer = System.nanoTime();
        long finish = 0, start = 0, bestTime = 0;
        start = System.nanoTime();
        do {

            // store last city
            route[vertLen] = 0;
            // loop variables
            min =  Double.MAX_VALUE;
            reducedCost = 0;
            nodeReducedCost = 0;

            // keep track of best route index
            int global_route = 0;
            // temporary city index holder
            int temp_idx = 0;
            // distance holder, best ever distance holder
            totalDistance = 0;

            // shuffle cities results in different answers
            Shuffle(nodes);

            // calculate weights and reduce map
            for (int i = 0; i < vertLen; i++)
            {
                /* INITIALIZE ARRAYS*/
                depart[i] = false;
                arrive[i] = false;
                pathcost[i] = Double.MAX_VALUE;
                route[i] = 0;

                min = Double.MAX_VALUE;

                for (int j = 0; j < vertLen; j++)
                {
                    if (i==j) // max out same city combination in matrix
                        weigth[i][j] = Double.MAX_VALUE;

                    else
                    {  // calculate weights for all coordinates
                        double x = nodes[i][0] - nodes[j][0];
                        double y = nodes[i][1] - nodes[j][1];

                        double answer = Math.sqrt(Math.pow(x, 2.0d) + Math.pow(y, 2.0d));

                        weigth[i][j] = answer; // store weight in matrix
                        original_weigth[i][j] = answer; // store original weight  for later use
                        if (answer < min) // find minimum  distance in each row to reduce with
                        {
                            min = answer;
                        }
                    }
                }

                for (int k = 0; k < vertLen; k++)
                {
                    weigth[i][k] = weigth[i][k] - min;  //reduce horizontally
                }

                reducedCost += min; // sum reduced cost
            }

            // calculate column's minimum distance and reduce vertically
            for (int i = 0; i < vertLen; i++)
            {
                min = Double.MAX_VALUE;

                for (int j = 0; j < vertLen; j++)
                {
                    if (weigth[j][i] < min)
                    min = weigth[j][i];
                }

                for (int j = 0; j < vertLen; j++) // subtract all columns
                {
                    weigth[j][i] = weigth[j][i] - min;
                }

                reducedCost += min; // sum reduced cost for later calculation
            }

            /////////////////////////////////////////////////////////////////////////////////////////////
            // visit next node from first city
            // store first reduced in first place
            cloneTwoD(weigth, path[0]); // object not reference is passed

            route[global_route++] = temp_idx; // build route sequence

            depart[temp_idx] = true; // mark city as visited

            // loop until route is found
            while (route[vertLen - 1] == 0)
            {
                for (int p = 0; p < vertLen; p++) {
                    if (!depart[p]) // if city already visited
                    {
                        arrive[p] = true; // keep track of visited city

                        cloneTwoD(path[global_route - 1], weigth_Copy); // get reduced matrix from last city
                        weigth_Copy[p][temp_idx] = Double.MAX_VALUE; // mark the route back

                        nodeReducedCost = 0;

                        // calculate row minimums
                        for (int i = 0; i < vertLen; i++) {
                            min = Double.MAX_VALUE;

                            for (int j = 0; j < vertLen; j++) {
                                if (depart[i] || arrive[j]) // whole row (if i equals from) || whole column (if j equals to) set row
                                    weigth_Copy[i][j] = Double.MAX_VALUE;

                                if (weigth_Copy[i][j] < min) // reduce row; find minimum
                                    min = weigth_Copy[i][j]; // while looping matrix keep track of minimum calculation
                            }

                            // subtract min if not zero from current row
                            if (min != 0 && min != Double.MAX_VALUE) {   // reduce matrix rows by minimum
                                for (int q = 0; q < vertLen; q++) {
                                    weigth_Copy[i][q] -= min;
                                }

                                nodeReducedCost += min;
                            }
                        }

                        // calculate column minimums
                        for (int i = 0; i < vertLen; i++) {
                            min = Double.MAX_VALUE;
                            // find minimum value per column
                            for (int j = 0; j < vertLen; j++) {
                                if (weigth_Copy[j][i] < min)
                                    min = weigth_Copy[j][i];
                            }

                            // subtract min if not zero
                            if (min != 0 && min != Double.MAX_VALUE) {
                                for (int j = 0; j < vertLen; j++) // subtract all columns with minimum value
                                {
                                    weigth_Copy[j][i] = weigth_Copy[j][i] - min;
                                }

                                nodeReducedCost += min; // sum reduced costs
                            }
                        }
                        // after calculation done keep copy of reduced matrix
                        cloneTwoD(weigth_Copy, path[p]);
                        // calculate cost from current city to next city
                        double cost = weigth[temp_idx][p] + reducedCost + nodeReducedCost;
                        // keep track of minimum cost
                        if (pathcost[p] > cost) // track smallest cost
                            pathcost[p] = cost;
                        // clear current calculated city
                        arrive[p] = false;
                    }
                }
                // find minimum calculated cost
                reducedCost = getMinValue(pathcost);
                // get index of minimum route found
                temp_idx = indexOf(pathcost, reducedCost);
                // update route table
                route[global_route++] = temp_idx; // store route

                // mark city as visited
                depart[temp_idx] = true; // keep track of visited cities

                for (int i = 0; i < vertLen; i++) {
                    /*CLEAR ARRAYS*/
                    arrive[i] = false;
                    pathcost[i] = Double.MAX_VALUE;

                }
            }



            // calculate total distance from original distance matrix
            for (int i = 0; i < vertLen; i++)
            {
                totalDistance += original_weigth[route[i]][route[i + 1]];
            }

            if (totalDistance < recordDistance)
            {
                //bestTime = (finish - start) /1000;
                recordDistance = totalDistance;
                bestEver = Arrays.copyOf(route, route.length);
                System.out.println("\nTotal Distance : " + recordDistance);
            }

            // clear route array
            clearI(route, 0);

            // clear reduced matrix holder
            for (int t = 0; t < vertLen; t++)
            {
                for(int x = 0; x < vertLen; x++){
                    for(int z = 0; z < vertLen; z++){
                        path[t][x][z]=0;
                        path[t][x][z]=0;
                    }
                }
            }

        }while(recordDistance > 34191);

        // print best ever route
        for (int i = 0; i < bestEver.length; i++)
        {
            System.out.print(bestEver[i]+1 + ",");
        }
        finish = System.nanoTime();
        System.out.println("Finish Time : " + finish + " ns");
        System.out.println("\nTotal Distance : " + recordDistance);
        //System.out.println("Best Time : " + bestTime + " us");
    }
}
