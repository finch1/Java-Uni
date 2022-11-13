package clnt;


import java.io.Serializable;

public abstract class Shapes implements Serializable {
    private int numSides;
    private String name;


    public Shapes(String name, int numSides){
        this.name = name;
        this.numSides = numSides;
    }

    public int getNumSides(){
        return numSides;
    }
     
    public String getName(){
        return name;
    }

    protected abstract double getArea();

    protected abstract double getPerimeter();

    public abstract Object[] toArray();

}

