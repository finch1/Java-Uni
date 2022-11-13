package clnt;

public class Rect2D extends Shapes2D {

    private double recWidth;
    private double recHeight;
    private String recName;

    private double recArea;
    private double recPer;

    public Rect2D(String recName, double recWidth, double recHeight){
        super(recName, 4);
        this.recName = recName;
        this.recHeight = recHeight;
        this.recWidth = recWidth;
        this.getPerimeter();
        this.getArea();
    }

    @Override
    public double getPerimeter(){
        recPer =  recHeight + recWidth;
        return  recPer;
    }

    @Override
    public double getArea(){
        recArea =  recHeight * recWidth;
        return  recArea;
    }

    public double setWidth(){ return this.recWidth;}

    public double setHeight(){ return this.recHeight;}

    public Object[] toArray(){
        return  new Object[]{recName, recWidth, recHeight, recPer, recArea};
    }

}
