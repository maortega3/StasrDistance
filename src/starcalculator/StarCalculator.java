package starcalculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mark on 10/4/16.
 */

public class StarCalculator {

    // Pre: An ArrayList of type StarMath has been initialized and populated (from csv file)
    // Post: An ArrayList conforming to the user specified radius is returned.
    public List<StarMath> withinRadius( List<StarMath> starSet){
        List<StarMath> smallerSet = new ArrayList<>();
        Scanner Cin = new Scanner(System.in);
        int radius;
        System.out.println("Enter a radius: (in parsecs) ");
        radius = Cin.nextInt();
        for(int i = 0; i  < starSet.size(); ++i){
            if(starSet.get(i).getThisDistance() < radius){
                smallerSet.add(starSet.get(i));
            }
        }
        System.out.print("Found " + smallerSet.size() + " stars within a " + radius + " parsec radius from SOL\n");
        return smallerSet;
    }

    // Pre: A List of Doubles containing the distance between nearest neighbors for all stars is passed in.
    // Post: returns a double equivalent to the minimum value in the neighbors list
    public Double getMin( List<Double> neighbors){
        double min = 10000000;    //val used for testing

        for( int i  = 0;  i < neighbors.size(); ++i){
            if(neighbors.get(i) < min){
                min = neighbors.get(i);
            }
        }
        return min;
    }

    // Pre:a list of Double vals has been created from the StarMath list
    //Post: Returns a ArrayList of doubles that are equivalnt to all of the closest distances
    public List<Double> getNeighbors( List<StarMath> starSet){
        List<StarMath> tempSet = starSet;
        List<Double> neighbors = new ArrayList<>();
        double min = 10000000;    //Value used for testing reasons (it will never be this large)
        double distance;

        for (int i = 0; i < tempSet.size(); ++i){
            for (int j = 0; j < tempSet.size(); ++j){
                distance = tempSet.get(i).getDistance(starSet.get(j));
                if( i != j) {   // This distance will always = 0.0
                    if (min > distance) {    // If min is greater than distance
                        min = distance;      // Replace it
                    }
                }
            }
            neighbors.add(min);
            min = 10000000;
        }
        return neighbors;
    }

    // Pre: a list of Double vals has been created from the StarMath list
    // Post: Returns a Double val equivalent to the max in the neighbors list
    public Double getMax( List<Double> neighbors){
        double max = 0.0;    //val used for testing

        for( int i  = 0;  i < neighbors.size(); ++i){
            if(neighbors.get(i) > max){
                max = neighbors.get(i);
            }
        }
        return max;
    }

    // Pre: a list of Double vals has been created from the StarMath list
    // Post: Returns a Double val equivalent to the mean value for all vals in the set
    public Double getMean( List<Double> neighbors){
        double mean = 0.0;

        for(int i = 0; i < neighbors.size(); ++i){
            mean += neighbors.get(i);
        }
        mean = mean/neighbors.size();

        return mean;
    }

    public static void main(String []args){

        Utilities tool = new Utilities();    // tool object for basic program functions
        StarCalculator obj = new StarCalculator();  // obj for accessing non static functions for this class


        List<StarMath> starList = new ArrayList<>();    // container for star data
        List<StarMath> smallerStarList = new ArrayList<>();    // container for the new set of data of smaller radius
        List<Double> neighborMinDist = new ArrayList<>();   // container for the nearest neighbor for all stars

        System.out.print(tool.hello()); // displaying hello message/program details to user

        starList = tool.getStarData(); /* calls a Utilites function that parses the hygxyz.csv data file and extracts
                                        the x, y, and z coordinates creates StarMath objects and stores them in an
                                         Array List to be copied. */

        smallerStarList = obj.withinRadius(starList);    // User sets radius. Smaller radius results in quicker computations

        neighborMinDist = obj.getNeighbors(smallerStarList);    // Sets list equal to a list of nearest neighbor vals for all stars

        for(int i = 0; i < neighborMinDist.size(); ++i){
            System.out.print("Star [" + (i + 1) + "]: " + neighborMinDist.get(i).doubleValue() + "\n");
        }
        System.out.print("Minimum: " + obj.getMin(neighborMinDist) + "\n");
        System.out.print("Maximum: " + obj.getMax(neighborMinDist) + "\n");
        System.out.print("Mean:    " + obj.getMean(neighborMinDist) + "\n");


    }


}
