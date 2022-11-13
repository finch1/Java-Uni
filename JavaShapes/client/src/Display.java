package clnt;

import java.util.*;

public class Display{

    Store storeShapes = new Store();
    String fileName;

    public void iterateMenu(String[] menu) {
        System.out.println("Choose an option from left side:");
        for (String s : menu) { //display main menu options
            System.out.println(s);
        }
    }

    private String getShapeName(){
        System.out.print("Enter shape details:\n");
        System.out.print("Name: ");
        return this.getStringInput();
    }

    private double getShapeWidth(){
        System.out.print("Width: ");
        return getNumInput();
    }

    private double getShapeHeight(){
        System.out.print("Height: ");
        return getNumInput();
    }

    private double getShapeRadius(){
        System.out.print("Radius: ");
        return getNumInput();
    }

    public void getRectangle() {

        String name = getShapeName();
        double width = getShapeWidth();
        double height = getShapeHeight();

        //initializing new object of type Rect2D + save object with data
        Shapes myShape = new Rect2D(name, width, height);
        storeShapes.shapeStore(myShape.toArray(), 0);
    }

    public void getCircle() {

        String name = getShapeName();
        double radius = getShapeRadius();

        //initializing new object of type Rect2D + save object with data
        Shapes myShape = new Crcl2D(name, radius);
        storeShapes.shapeStore(myShape.toArray(), 1);
    }

    public void getTriangle() {

        String name = getShapeName();
        double width = getShapeWidth();
        double height = getShapeHeight();

        //initializing new object of type Rect2D + save object with data
        Shapes myShape = new Tri2D(name, width, height);
        storeShapes.shapeStore(myShape.toArray(), 2);
    }

    public void getCylinder() {

        String name = getShapeName();
        double radius = getShapeRadius();
        double height = getShapeHeight();

        //initializing new object of type Rect2D + save object with data
        Shapes myShape = new Cyl3D(name, radius, height);
        storeShapes.shapeStore(myShape.toArray(), 3);
    }

    public void getSphere() {

        String name = getShapeName();
        double radius = getShapeRadius();

        //initializing new object of type Rect2D + save object with data
        Shapes myShape = new Sphr3D(name, radius);
        storeShapes.shapeStore(myShape.toArray(), 4);
    }

    public double getNumInput(){

        double input;

        Scanner console = new Scanner(System.in);//listen for input from user
        while(true){
            try{
                input = console.nextDouble();//pull user choice
                return input;
            }
            catch(InputMismatchException ime) {
                System.out.print("Your input is invalid, please enter the number again: ");
                console.next();
            }
        }
    }

    public String getStringInput(){

        String input;

        Scanner console = new Scanner(System.in); //listen for input from user
        while(true){
            try {
                input = console.nextLine();

                if(input.equals(" "))
                {
                    System.out.println("Name cannot be only space, please try again");
                    System.out.print("Name: ");
                }
                else {
                    return input;
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Your input is invalid, please try again\n");
                console.next();
            }
        }
    }

    //send files to server
    public void shapeTransfer(String shapeType, int instruction){

        if(fileName != null) {
            System.out.print("Use last file name: " + fileName + "?\ny - yes\nn - no\n");

            if (getStringInput().equals("n")) {
                System.out.print("Enter a filename: ");
                fileName = getStringInput();
            }
        }
        else {
            System.out.print("Enter a filename: ");
            fileName = getStringInput();
        }

        if(instruction == 1){
            new Client(storeShapes.allSend(), 1, fileName);//send shapes
        }
        else{
            new Client(instruction, fileName, shapeType);//get shapes
        }
    }


    //print shape details info before numbers
    public static void showHeader(int index){
        String[] headerArray = {"Printing Rectangles",
                                "\nPrinting Circles",
                                "\nPrinting Triangles",
                                "\nPrinting Cylinder",
                                "\nPrinting Sphere"};

        String[] definitionArray = {"Name\t\tWidth\tHeight\tPerimeter\tArea",
                                    "Name\t\tRadius\tPerimeter\tArea",
                                    "Name\t\tWidth\tHeight\tPerimeter\tArea",
                                    "Name\t\tRadius\tHeight\tPerimeter\tArea\tVolume",
                                    "Name\t\tRadius\tArea\tVolume"};

        System.out.println(headerArray[index]);
        System.out.println(definitionArray[index]);
    }

}//Menu








