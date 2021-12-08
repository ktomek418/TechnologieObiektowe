package fireBrigade.fireTruck.fireTruckState;

import java.awt.*;

public class Free implements FireTruckState {
    @Override
    public String getState() {
        return "Free";
    }

    @Override
    public Color getColor() {
        return Color.red;
    }

}
