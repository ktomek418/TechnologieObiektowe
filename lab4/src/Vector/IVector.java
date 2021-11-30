package Vector;

public interface IVector {
    double[] getComponents();

    double abs();

    double cdot(IVector vector);

    void moveX(double newX);
    void moveY(double newY);

    double getDistance(IVector vector);
}
