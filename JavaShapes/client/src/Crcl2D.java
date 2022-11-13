package clnt;

public class Crcl2D extends Shapes2D {

    private double crclRadius;
    private String crclName;

    private double crclArea;
    private double crclPer;

    public Crcl2D(String crclName, double crclRadius){
        super(crclName, 1);
        this.crclName = crclName;
        this.crclRadius = crclRadius;
        this.getPerimeter();
        this.getArea();
    }

    @Override
    public double getPerimeter(){
        crclPer =  2 * Math.PI * crclRadius;
        return  crclPer;
    }

    @Override
    public double getArea(){
        crclArea =  Math.PI * (crclRadius * crclRadius);
        return  crclArea;
    }

    public double setRadius(){ return this.crclRadius;}

    public Object[] toArray(){

        return  new Object[]{crclName, crclRadius, crclPer, crclArea};
    }

}
