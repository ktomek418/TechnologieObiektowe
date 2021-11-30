package states;

import java.awt.*;

public class SickWithoutSymptoms implements IState{

    private final Color color = Color.black;
    private final String name = "SickWithoutSymptoms";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
