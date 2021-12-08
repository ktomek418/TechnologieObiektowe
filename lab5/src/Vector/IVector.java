package Vector;

public interface IVector {
    double[] getComponents();

    void moveX(double newX);
    void moveY(double newY);

    double getDistance(IVector vector);
    IVector getMovement(IVector vector);
}
