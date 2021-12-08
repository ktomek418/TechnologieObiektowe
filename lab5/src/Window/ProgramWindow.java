package Window;

import Simulation.Simulation;

import javax.swing.*;
import java.awt.*;

public class ProgramWindow extends JFrame {

    private final JPanel simulationWindow = new JPanel();
    private final Simulation simulation;



    public ProgramWindow(Simulation simulation){

        super("Epidemic simulation");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.simulation = simulation;

        this.setSimulationWindow();
        this.add(simulationWindow);
        this.pack();
        this.setVisible(true);
    }


    private void setSimulationWindow(){
        simulationWindow.setPreferredSize(new Dimension(900, 700));
        simulationWindow.setBackground(Color.getColor("0xEEEEEE"));
        simulationWindow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        simulationWindow.add(simulation);
    }

}
