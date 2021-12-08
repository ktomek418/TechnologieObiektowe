package fireBrigade.fireTruck;

import Vector.IVector;
import java.awt.*;

public interface IFireTruck {
    IVector getPosition();

    String getState();

    Color getColor();

    boolean inAction();

    void send(IVector destination);

    void move();


}
