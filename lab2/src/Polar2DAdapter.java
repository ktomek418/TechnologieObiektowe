public class Polar2DAdapter implements IVector, IPolar2D {

    private final Vector2D srcVector;

    public Polar2DAdapter(Vector2D srcVector){
        this.srcVector = srcVector;
    }

    @Override
    public double[] getComponents() {
        return srcVector.getComponents();
    }

    @Override
    public double abs() {
        return srcVector.abs();
    }

    @Override
    public double cdot(IVector vector) {
        return srcVector.cdot(vector);
    }

    @Override
    public double getAngle(){
        double[] components = srcVector.getComponents();
        double angle =  Math.round(Math.toDegrees(Math.atan(components[1] / components[0])) * 100)/100.0;
        if(components[0] >= 0 && components[1] >= 0) {return angle;}
        else if(components[0] >= 0 && components[1] <= 0) {return angle + 360.0;}
        else  {return angle + 180.0;}
    }

    @Override
    public String toString(){
        return "PolarVector2D (r= " +  abs() + ", fi= " + getAngle()  + ")";
    }

    public String cartesian(){
        double[] components = srcVector.getComponents();
        return "Vector2D (" + components[0] + ", " + components[1]  + ")";
    }
}
