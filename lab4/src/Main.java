import simulation.Simulation;
import window.Window;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        Window window = new Window(Simulation.createSimulationWithResistance(25, 16, 60));
    }
}
