public class Vector3DInheritance extends Vector2D{

    private final double z;

    public Vector3DInheritance(double x, double y, double z){
        super(x, y);
        this.z = z;
    }

    @Override
    public double[] getComponents(){
        double[] components = super.getComponents();
        return new double[] {components[0], components[1], z};
    }

    @Override
    public double abs(){
        double[] components = super.getComponents();
        return Math.sqrt(Math.pow(components[0], 2) + Math.pow(components[1], 2) + Math.pow(z, 2));
    }

    @Override
    public double cdot(IVector vector){
        double[] paramComponents = vector.getComponents();
        if(paramComponents.length == 2) {return super.cdot(vector); }
        return super.cdot(vector) + z * paramComponents[2];
    }

    public IVector getSrcV(){
        double[] components = super.getComponents();
        return new Vector2D(components[0], components[1]);
    }

    public Vector3DInheritance cross(IVector vector){
        double[] paramComponents = vector.getComponents();
        double[] thisComponents = getComponents();
        double newZ = thisComponents[0] * paramComponents[1] - thisComponents[1] * paramComponents[0];
        if(paramComponents.length == 3){
            double newX = thisComponents[1] * paramComponents[2] - thisComponents[2] * paramComponents[1];
            double newY = thisComponents[2] * paramComponents[0] - thisComponents[0] * paramComponents[2];
            return new Vector3DInheritance(newX, newY, newZ);
        }
        double newX =  - thisComponents[2] * paramComponents[1];
        double newY = thisComponents[2] * paramComponents[0];
        return new Vector3DInheritance(newX, newY, newZ);
    }

    @Override
    public String toString(){
        double[] components = getComponents();
        return "Vector3D (" + components[0] + ", " + components[1] + ", " + components[2] +")";
    }

}
