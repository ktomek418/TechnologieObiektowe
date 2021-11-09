public class Polar2DAdapter implements IVector, IPolar2D {

    private Vector2D srcVector;

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
        return Math.toDegrees(Math.atan(components[1] / components[0]));
    }
}
