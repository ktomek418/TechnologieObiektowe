public class Main {

    public static void main(String[] args) {
        Vector2D vector2D = new Vector2D(3, 10);
        Polar2DAdapter polarVector2D = new Polar2DAdapter(new Vector2D(2, 5));
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(new Vector2D(7, 4), 2);
        Vector3DDecorator vector3DDecorator2 = new Vector3DDecorator(new Vector2D(3, -4), 12);

        System.out.println("Wspołrzędne kartezjańskie");
        System.out.println(vector2D);
        System.out.println(polarVector2D.cartesian());
        System.out.println(vector3DDecorator);
        System.out.println(vector3DDecorator2);
        System.out.println();

        System.out.println("Współrzędne biegunowe");
        System.out.println(new Polar2DAdapter(vector2D));
        System.out.println(polarVector2D);
        System.out.println();

        System.out.println("Iloczyny skalarne");
        System.out.println(vector2D + "  *  " + polarVector2D + "  =  " + vector2D.cdot(polarVector2D));
        System.out.println(vector2D + "  *  " + vector3DDecorator + "  =  " + vector2D.cdot(vector3DDecorator));
        System.out.println(vector2D + "  *  " + vector3DDecorator2 + "  =  " + vector2D.cdot(vector3DDecorator2));
        System.out.println(vector3DDecorator +"  *  "+ polarVector2D + "  =  " + vector3DDecorator.cdot(polarVector2D));
        System.out.println(vector3DDecorator +"  *  "+ vector3DDecorator2 + "  =  " + vector3DDecorator.cdot(vector3DDecorator2));
        System.out.println(vector3DDecorator2 +"  *  "+ polarVector2D + "  =  " + vector3DDecorator2.cdot(polarVector2D));
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

        Vector3DDecorator product3 = vector3DDecorator2.cross(vector2D);
        System.out.println(vector3DDecorator2 + "  X  " + vector2D + "  =  " + product3);
        System.out.println("Sprawdzenie");
        System.out.println(vector3DDecorator2 + "  *  " + product3 + "  =  " + vector3DDecorator2.cdot(product3));
        System.out.println(vector2D + "  *  " + product3 + "  =  " + vector2D.cdot(product3));
        System.out.println();

        Vector3DDecorator product4 = vector3DDecorator2.cross(polarVector2D);
        System.out.println(vector3DDecorator2 + "  X  " + polarVector2D + "  =  " + product4);
        System.out.println("Sprawdzenie");
        System.out.println(vector3DDecorator2 + "  *  " + product4 + "  =  " + vector3DDecorator2.cdot(product4));
        System.out.println(polarVector2D + "  *  " + product4 + "  =  " + polarVector2D.cdot(product4));
        System.out.println();

        Vector3DDecorator product5 = vector3DDecorator.cross(vector3DDecorator2);
        System.out.println(vector3DDecorator + "  X  " + vector3DDecorator2 + "  =  " + product5);
        System.out.println("Sprawdzenie");
        System.out.println(vector3DDecorator + "  *  " + product5 + "  =  " + vector3DDecorator.cdot(product5));
        System.out.println(vector3DDecorator2 + "  *  " + product5 + "  =  " + vector3DDecorator2.cdot(product5));
        System.out.println();


    }
}
