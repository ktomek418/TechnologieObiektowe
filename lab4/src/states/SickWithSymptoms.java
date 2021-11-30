package states;

import java.awt.*;

public class SickWithSymptoms implements IState{

    private final Color color = Color.red;
    private final String name = "SickWithSymptoms";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
