public interface IVector {
    double[] getComponents();

    double abs();

    double cdot(IVector vector);
}
