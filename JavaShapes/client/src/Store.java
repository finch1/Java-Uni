package clnt;

import java.io.*;
import java.util.*;

public class Store implements Serializable
{
    private ArrayList<ArrayList<Object[]>> allShapes   = new ArrayList<>(); //storing all serialized shapes

    public Store(){
        int noOfShapes = 5;

        for(int indexNo = 0; indexNo < noOfShapes; indexNo++){ //initialize an array of arrays
            this.allShapes.add(new ArrayList<Object[]>());
        }
    }

    //save shape details created to local array
    public void shapeStore(Object[] shapeData, int index){  //index is shape type

        allShapes.get(index).add(shapeData); //store shapes

        ArrayList<Object[]> tmpArray = allShapes.get(index); //retrieve last saved for verification
        shapeData = tmpArray.get(tmpArray.size() -1);

        Display.showHeader(index);

        for(Object obj : shapeData){    //and print it
            if(obj instanceof String)
            {
                String str = (String) obj;
                System.out.format("%s", str);
            }

            if(obj instanceof Double)
            {
                Double dbl = (Double) obj;
                System.out.format("%10.2f", dbl);
            }
        }

        System.out.println("\n");
    }


    public ArrayList<ArrayList<Object[]>> allSend(){return allShapes;}


    public void shapeDisplay(Object collectionOfShapes, String option){

        switch (option){
            case "r":
                Display.showHeader(0);
                iterateShape((ArrayList<Object[]>)collectionOfShapes);
                break;
            case "c":
                Display.showHeader(1);
                iterateShape((ArrayList<Object[]>)collectionOfShapes);
                break;
            case "t":
                Display.showHeader(2);
                iterateShape((ArrayList<Object[]>)collectionOfShapes);
                break;
            case "a":
                allShapes = (ArrayList<ArrayList<Object[]>>)collectionOfShapes;
                for(int index = 0; index < allShapes.size(); index++) {

                    if(allShapes.get(index).size() != 0) { //do not print if user did not save particular shape
                        Display.showHeader(index);
                        iterateShape(allShapes.get(index));
                    }
                }
                //parse array
                break;
            default:
                break;
        }

        System.out.println();
    }

    private void iterateShape(ArrayList<Object[]> oneOfShape) {
        for (Object[] objA : oneOfShape) { //loop through array of objects
            for (Object obj : objA) {    //loop through elements in object[]
                if(obj instanceof String)
                {
                    String str = (String) obj;
                    System.out.format("%s", str);
                }

                if(obj instanceof Double)
                {
                    Double dbl = (Double) obj;
                    System.out.format("%10.2f", dbl);
                }
            }
            System.out.println();
        }
    }
}
