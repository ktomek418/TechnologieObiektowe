public class PolarInheritance extends Vector2D{

    public PolarInheritance(double x, double y){
        super(x, y);
    }

    public double getAngle(){
        double[] components = super.getComponents();
        double angle =  Math.round(Math.toDegrees(Math.atan(components[1] / components[0])) * 100)/100.0;
        if(components[0] >= 0 && components[1] >= 0) {return angle;}
        else if(components[0] >= 0 && components[1] <= 0) {return angle + 360.0;}
        else  {return angle + 180.0;}
    }

    @Override
    public String toString(){
        return "PolarVector2D (r= " +  super.abs() + ", fi= " + getAngle()  + ")";
    }

    public String cartesian(){
        double[] components = super.getComponents();
        return "Vector2D (" + components[0] + ", " + components[1]  + ")";
    }

}
