package states;

import java.awt.*;

public class Resist implements IState{

    private final Color color = Color.blue;
    private final String name = "Resist";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
