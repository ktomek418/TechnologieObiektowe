package window;

import simulation.Simulation;
import snapshot.Caretaker;
import snapshot.Memento;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final JPanel menu = new JPanel();
    private final JPanel simulationWindow = new JPanel();
    private Simulation simulation;

    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");
    private final JButton startButton = new JButton("Start");
    private final JButton resetButton = new JButton("Reset");
    private final Caretaker caretaker = new Caretaker();
    private JComboBox comboBox = new JComboBox(caretaker.getListOfMementos());

    public Window(Simulation simulation){

        super("Epidemic simulation");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.simulation = simulation;

        this.setButtons();
        this.setMenu();
        this.setSimulationWindow();
        this.setActionListeners();

        this.add(menu, BorderLayout.NORTH);
        this.add(simulationWindow);
        this.pack();
        this.setVisible(true);
    }

    private void setMenu(){
        menu.setPreferredSize(new Dimension(1000, 100));
        menu.setBackground(Color.GRAY);
        menu.setLayout(new GridLayout(1,4, 10, 10));
        menu.add(comboBox);
        menu.add(loadButton);
        menu.add(saveButton);
        menu.add(startButton);
        menu.add(resetButton);

    }

    private void setButtons(){
        saveButton.setPreferredSize(new Dimension(70, 30));
        startButton.setPreferredSize(new Dimension(70, 30));
        loadButton.setPreferredSize(new Dimension(70, 30));
        resetButton.setPreferredSize(new Dimension(70, 30));
        comboBox.setPreferredSize(new Dimension(200, 30));
        if(this.comboBox.getSelectedIndex() < 0) loadButton.setEnabled(false);
    }

    private void setSimulationWindow(){
        simulationWindow.setPreferredSize(new Dimension(1500, 700));
        simulationWindow.setBackground(Color.getColor("0xEEEEEE"));
        simulationWindow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        simulationWindow.add(simulation);
    }

    private void setActionListeners(){
        startButton.addActionListener(e-> {
            if(this.simulation.isOn()) this.simulation.stopSimulation();
            else this.simulation.startSimulation();
        });

        loadButton.addActionListener(e -> {
            this.simulation.stopSimulation();
            this.simulationWindow.removeAll();
            Simulation sim = this.caretaker.getMemento(comboBox.getSelectedIndex()).getState();
            this.simulationWindow.add(sim);
            this.simulation = sim;
            repaint();
            revalidate();
        });

        saveButton.addActionListener(e -> {
            this.caretaker.addMemento(new Memento(this.simulation));
            this.menu.remove(this.comboBox);
            this.comboBox = new JComboBox(this.caretaker.getListOfMementos());
            this.comboBox.setPreferredSize(new Dimension(200, 30));
            this.menu.add(this.comboBox, 0);
            this.revalidate();
            this.repaint();
            this.loadButton.setEnabled(true);
        });

        resetButton.addActionListener(e -> {
            this.simulationWindow.removeAll();
            Simulation sim = Simulation.createSimulationWithResistance(
                    this.simulation.getSimulationWidth() / Simulation.METER,
                    this.simulation.getSimulationHeight() / Simulation.METER,
                    this.simulation.getStartingPopulation());
            this.simulationWindow.add(sim);
            this.simulation.stopSimulation();
            this.simulation = sim;
            repaint();
            revalidate();
        });
    }
}
