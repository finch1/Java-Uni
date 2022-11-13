package clnt;

public class Shapes3D extends Shapes implements Volume{

    int numSides;
    String name;

    double threeDPer;
    double threeDArea;
    double threeDVol;

    public Shapes3D(String name, int numSides){
        super(name, numSides);
        this.name = name;
        this.numSides = numSides;
    }

    public int getNumSides(){
        return numSides;
    }

    public String getName(){
        return name;
    }

    public double getPerimeter(){return threeDPer;}

    public double getArea(){return threeDArea;}

    public double getVolume(){return threeDVol;}

    public Object[] toArray(){return  new Object[]{};}

}

