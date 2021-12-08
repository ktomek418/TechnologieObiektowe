package fireBrigade.fireTruck.fireTruckState;

import java.awt.*;

public class InAction implements FireTruckState {


    @Override
    public String getState() {
        return "InAction";
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }
}
