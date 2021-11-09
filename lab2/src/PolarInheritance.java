public class PolarInheritance extends Vector2D{

    public PolarInheritance(double x, double y){
        super(x, y);
    }

    public double getAngle(){
        double[] components = super.getComponents();
        return Math.toDegrees(Math.atan(components[1] / components[0]));
    }


}
