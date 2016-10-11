package starcalculator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 10/5/16.
 */
public class Utilities {

    // Pre: none
    // Post:   Returns a string hello message to the user.
    public String hello(){
        String helloMessage = "Hello and welcome to the Star Distance Program! \n" +
                "This program extracts all relevant information from the hyg database and stores them in a list. \n" +
                "Computations performed by this program include -" + "\n" +
                "MEAN, MINIMUM, and MAX values for the nearest neighboring star for all stars...  \n" +
                "Where SOL is the origin... \n \n";

            return helloMessage;
    }


    // Pre: File is in directory "hygxyz.csv"
    // Post: a vector containing all relevant stars distance < 10,000,000 parsecs is returned. Vector is of type StarMath
    public List<StarMath> getStarData(){
        List<StarMath> stars = new ArrayList<>();
        double x_temp, y_temp, z_temp; //Temp vals to create the objects.
        double distanceUnknown = 10000000;  // Integer val for stars with an unknown distance in parsecs from SOL
        double tempDistance; // holds the extracted stars distance to be preprocessed out if  == distanceUnknown
        try{
            BufferedReader br = new BufferedReader(new FileReader("hygxyz.csv"));

            String fileRead = br.readLine();    //Read from first line...
            fileRead = br.readLine();    // Clearing Second line  so that a string is not captured.
                                         // NOTE: This code only works for the "hygcyz.csv" file (star file)
            while(fileRead != null) {
                String[] tokenize = fileRead.split(",");
                tempDistance = Double.parseDouble(tokenize[9]);    // Distance is index 9 (in csv file)
                x_temp = Double.parseDouble(tokenize[17]);   // X is index 17
                y_temp = Double.parseDouble(tokenize[18]);   // Y is index 18
                z_temp = Double.parseDouble(tokenize[19]);   // Z is index 19

                if (tempDistance == distanceUnknown) {
                    fileRead = br.readLine(); // not including vals of 10,000,000 as they are no bueno haha
                } else {
                    StarMath tempStar = new StarMath(x_temp, y_temp, z_temp);    // Add star
                    stars.add(tempStar);

                    fileRead = br.readLine(); // pointer to next line
                }
            }
            br.close();  //close the file stream

        }catch (FileNotFoundException fnfe){
            System.out.print("Specified File not Found... " + fnfe + '\n');
        }
        catch(IOException ioe){
            System.out.print("IO exception caught: " + ioe + '\n');
        }

        return stars;
    }

}
