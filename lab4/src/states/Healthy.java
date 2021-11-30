package states;

import java.awt.*;

public class Healthy implements IState{

    private final Color color = Color.green;
    private final String name = "Healthy";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
