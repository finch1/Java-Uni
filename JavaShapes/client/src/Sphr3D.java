package clnt;

public class Sphr3D extends Shapes3D {

    private double sphrRadius;
    private String sphrName;

    private double sphrArea;
    private double sphrVol;


    public Sphr3D(String sphrName, double sphrRadius){
        super(sphrName, 1);
        this.sphrName = sphrName;
        this.sphrRadius = sphrRadius;
        this.getArea();
    }


    @Override
    public double getArea(){
        sphrArea =  4 * Math.PI * (sphrRadius * sphrRadius);
        return  sphrArea;
    }

    public double getVolume(){
        sphrArea =  (4/3) * Math.PI * (sphrRadius * sphrRadius * sphrRadius);
        return  sphrArea;
    }


    public double setRadius(){ return this.sphrRadius;}

    public Object[] toArray(){
        return  new Object[]{sphrName, sphrRadius, sphrArea, sphrVol};
    }
}
