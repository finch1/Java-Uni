package clnt;

public class Shapes2D extends Shapes {

    int numSides;
    String name;

    double twoDPer;
    double twoDArea;

    public Shapes2D(String name, int numSides){
        super(name, numSides);
        this.name = name;
        this.numSides = numSides;
    }


    public int getNumSides(){return numSides;}

    public String getName(){return name;}

    public double getPerimeter(){return twoDPer;}

    public double getArea(){return twoDArea;}

    public Object[] toArray(){return  new Object[]{};}


}

