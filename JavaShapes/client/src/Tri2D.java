package clnt;

public class Tri2D extends Shapes2D {

    private double triBase;
    private double triHeight;
    private String triName;

    private double triArea;
    private double triPer;

    public Tri2D(String triName, double triBase, double triHeight){
        super(triName, 3);
        this.triName = triName;
        this.triHeight = triHeight;
        this.triBase = triBase;
        this.getPerimeter();
        this.getArea();
    }

    @Override
    public double getPerimeter(){
        triPer =  triHeight + triBase + Math.sqrt((triHeight * triHeight) + (triBase * triBase)); //side + side + hyp
        return  triPer;
    }

    @Override
    public double getArea(){
        triArea =  (triHeight * triBase) /2;
        return  triArea;
    }

    public double setBase(){ return this.triBase;}

    public double setHeight(){ return this.triHeight;}

    public Object[] toArray(){
        return  new Object[]{triName, triBase, triHeight, triPer, triArea};
    }
}


