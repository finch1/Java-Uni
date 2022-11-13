package clnt;

public class Cyl3D extends Shapes3D {

    private double cylRadius;
    private double cylHeight;
    private String cylName;

    private double cylArea;
    private double cylPer;
    private double cylVol;

    public Cyl3D(String cylName, double cylRadius, double cylHeight){
        super(cylName, 4);
        this.cylName = cylName;
        this.cylHeight = cylHeight;
        this.cylRadius = cylRadius;
        this.getPerimeter();
        this.getArea();
        this.getVolume();
    }

    @Override
    public double getPerimeter(){
        cylPer =  2 * Math.PI * cylRadius;
        return  cylPer;
    }

    @Override
    public double getArea(){
        cylArea =  Math.PI * cylRadius * cylRadius;
        return  cylArea;
    }

    @Override
    public double getVolume(){
        cylVol =  Math.PI * cylRadius * cylRadius * cylHeight;
        return  cylVol;
    }

    public double setRadius(){ return this.cylRadius;}

    public double setHeight(){ return this.cylHeight;}

    public Object[] toArray(){
        return  new Object[]{cylName, cylRadius, cylHeight, cylPer, cylArea, cylVol};
    }

}
