package Vector;

public class Vector2D implements IVector {

    private double x;
    private double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double[] getComponents(){
        return new double[] {x, y};
    }

    @Override
    public void moveX(double newX){
        this.x += newX;
    }

    @Override
    public void moveY(double newY){
        this.y += newY;
    }

    public double getDistance(IVector vector){
        double[] param = vector.getComponents();
        return Math.sqrt((Math.pow(param[0] - x, 2) + Math.pow(param[1] - y, 2)));
    }

    public IVector getMovement(IVector vector){
        double[] param = vector.getComponents();
        return new Vector2D(x - param[0], y - param[1]);
    }

    @Override
    public String toString(){
        return "Vector.Vector2D (" + x + ", " + y  + ")";
    }
}
