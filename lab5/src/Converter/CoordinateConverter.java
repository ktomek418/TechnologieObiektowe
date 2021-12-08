package Converter;

import Vector.IVector;
import Vector.Vector2D;

import java.util.Random;

public class CoordinateConverter {

    public static IVector FIRSTVERTEX = new Vector2D(50.154564013341734, 19.688292482742394);
    public static IVector SECONDVERTEX = new Vector2D(49.95855025648944, 20.02470275868903);
    public static int SCALE = 2200;


    public static IVector getDimensionInPixels(){
        double[] firstComponents = FIRSTVERTEX.getComponents();
        double[] secondComponents = SECONDVERTEX.getComponents();
        double newX = Math.abs(firstComponents[1] - secondComponents[1]);
        double newY = Math.abs(firstComponents[0] - secondComponents[0]);
        return new Vector2D(newX*SCALE, newY*SCALE);
    }

    public static IVector getPositionInPixels(IVector positionVector){
        double[] startingPosition = FIRSTVERTEX.getComponents();
        double[] position = positionVector.getComponents();
        double newX = Math.abs(startingPosition[1] - position[1]);
        double newY = Math.abs(startingPosition[0] - position[0]);
        return new Vector2D(newX*SCALE, newY*SCALE);
    }

    public static IVector randomPositionWGS84(){
        Random random = new Random();
        double[] firstComp = FIRSTVERTEX.getComponents();
        double[] secondComp = SECONDVERTEX.getComponents();
        double newX = secondComp[0] + (firstComp[0] - secondComp[0]) * random.nextDouble();
        double newY = secondComp[0] + (secondComp[1] - firstComp[1]) * random.nextDouble();
        return new Vector2D(newX, newY);
    }


}
