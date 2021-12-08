package fireBrigade.fireTruck.fireTruckState;

import java.awt.*;

public class OnTheWay implements FireTruckState{
    @Override
    public String getState() {
        return "OnTheWay";
    }

    @Override
    public Color getColor() {
        return Color.yellow;
    }
}
