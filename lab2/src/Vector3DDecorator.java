public class Vector3DDecorator implements IVector {

    public IVector srcVector;
    public double z;

    public Vector3DDecorator(IVector srcVector, double z){
        this.srcVector = srcVector;
        this.z = z;
    }

    @Override
    public double[] getComponents() {
        double[] components = srcVector.getComponents();
        return new double[] {components[0], components[1], z};
    }

    @Override
    public double abs() {
        double[] components = srcVector.getComponents();
        return Math.sqrt(Math.pow(components[0], 2) + Math.pow(components[1], 2) + Math.pow(z, 2));
    }

    @Override
    public double cdot(IVector vector) {
        double[] paramComponents = vector.getComponents();
        if(paramComponents.length == 2) {return srcVector.cdot(vector); }
        return srcVector.cdot(vector) + z * paramComponents[2];
    }

    public IVector getSrcV(){
        return srcVector;
    }

    public Vector3DDecorator cross(IVector vector){
        double[] paramComponents = vector.getComponents();
        double[] thisComponents = getComponents();
        double newZ = thisComponents[0] * paramComponents[1] - thisComponents[1] * paramComponents[0];
        if(paramComponents.length == 3){
            double newX = thisComponents[1] * paramComponents[2] - thisComponents[2] * paramComponents[1];
            double newY = thisComponents[2] * paramComponents[0] - thisComponents[0] * paramComponents[2];
            return new Vector3DDecorator(new Vector2D(newX, newY), newZ);
        }
        double newX =  - thisComponents[2] * paramComponents[1];
        double newY = thisComponents[2] * paramComponents[0];
        return new Vector3DDecorator(new Vector2D(newX, newY), newZ);
    }

    @Override
    public String toString(){
        double[] components = srcVector.getComponents();
        return "Vector3D (" + components[0] + ", " + components[1] + ", " + z +")";
    }
}
