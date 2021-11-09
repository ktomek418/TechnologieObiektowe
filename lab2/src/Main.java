public class Main {

    public static void main(String[] args) {
        Vector2D vector2D = new Vector2D(3, 5);
        Polar2DAdapter polarVector2D = new Polar2DAdapter(new Vector2D(2, 5));
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(new Vector2D(7, 4), 2);

        System.out.println("Wspołrzędne kartezjańskie");
        System.out.println(vector2D);
        System.out.println(polarVector2D.cartesian());
        System.out.println(vector3DDecorator);
        System.out.println();

        System.out.println("Współrzędne biegunowe");
        System.out.println(new Polar2DAdapter(vector2D));
        System.out.println(polarVector2D);
        System.out.println();

        System.out.println("Iloczyny skalarne");
        System.out.println(vector2D + "  *  " + polarVector2D + "  =  " + vector2D.cdot(polarVector2D));
        System.out.println(vector2D + "  *  " + vector3DDecorator + "  =  " + vector2D.cdot(vector3DDecorator));
        System.out.println(vector3DDecorator +"  *  "+ polarVector2D + "  =  " + vector3DDecorator.cdot(polarVector2D));
        System.out.println();

        System.out.println("Iloczyny wektorowe");
        System.out.println();
        Vector3DDecorator product1 = vector3DDecorator.cross(vector2D);
        System.out.println(vector3DDecorator + "  X  " + vector2D + "  =  " + product1);
        System.out.println("Sprawdzenie");
        System.out.println(vector3DDecorator + "  *  " + product1 + "  =  " + vector3DDecorator.cdot(product1));
        System.out.println(vector2D + "  *  " + product1 + "  =  " + vector2D.cdot(product1));
        System.out.println();
        Vector3DDecorator product2 = vector3DDecorator.cross(polarVector2D);
        System.out.println(vector3DDecorator + "  X  " + polarVector2D + "  =  " + product2);
        System.out.println("Sprawdzenie");
        System.out.println(vector3DDecorator + "  *  " + product2 + "  =  " + vector3DDecorator.cdot(product2));
        System.out.println(polarVector2D + "  *  " + product2 + "  =  " + polarVector2D.cdot(product2));
        System.out.println();
    }
}
