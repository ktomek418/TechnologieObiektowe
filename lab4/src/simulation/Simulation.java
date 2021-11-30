package simulation;

import person.Person;
import states.Healthy;
import states.IState;
import states.Resist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Simulation extends JPanel implements ActionListener {

    public static final int FPS = 25;
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 650;
    public static final int MAX_POPULATION = 150;
    public static final int METER = 25;

    private ArrayList<Person> population;

    private Timer nextSimulationStepTimer;
    private Timer newMovementTimer;
    private Timer newPersonOnTheBorderTimer;
    private final int simulationWidth;
    private final int simulationHeight;
    private final int startingPopulation;

    public Simulation(){

        this.simulationWidth = FRAME_WIDTH;
        this.simulationHeight = FRAME_HEIGHT;
        this.startingPopulation = MAX_POPULATION;
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        this.setPreferredSize(new Dimension(this.simulationWidth, this.simulationHeight));
        this.setTimers();
    }

    public Simulation(int simulationWidth, int simulationHeight, int population){
        if(simulationWidth * METER > FRAME_WIDTH || simulationWidth < 5) this.simulationWidth = FRAME_WIDTH;
        else this.simulationWidth = simulationWidth * METER;
        if(simulationHeight * METER > FRAME_HEIGHT || simulationHeight < 5) this.simulationHeight = FRAME_HEIGHT;
        else this.simulationHeight = simulationHeight * METER;
        if(population > MAX_POPULATION || population < 0) this.startingPopulation = MAX_POPULATION;
        else this.startingPopulation = population;
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        this.setPreferredSize(new Dimension(this.simulationWidth, this.simulationHeight));
        this.setTimers();
    }

    public int getSimulationWidth() {
        return simulationWidth;
    }

    public int getSimulationHeight() {
        return simulationHeight;
    }

    public int getStartingPopulation() {
        return startingPopulation;
    }

    private void  setTimers(){
        nextSimulationStepTimer = new Timer(1000/FPS, this);
        newMovementTimer = new Timer(500, e ->{for(Person person: population) person.generateMovement();});
        newPersonOnTheBorderTimer = new Timer(280, e-> {
            if(!(population.size() > startingPopulation + 3))
                population.add(newPersonOnTheBorder());
        });
    }


    public void add(ArrayList<Person> persons){
        this.population = persons;
        repaint();
    }

    public static Simulation createSimulationWithoutResistance(int simulationWidth, int simulationHeight, int population){
        Simulation simulation = new Simulation(simulationWidth, simulationHeight, population);
        ArrayList<Person> persons = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<population; i++){
            persons.add(new Person(new Healthy(), random.nextInt(simulation.getSimulationWidth()),
                    random.nextInt(simulation.getSimulationHeight())));
        }
        simulation.add(persons);
        return simulation;
    }

    public static Simulation createSimulationWithResistance(int simulationWidth, int simulationHeight, int population){
        Simulation simulation = new Simulation(simulationWidth, simulationHeight, population);
        ArrayList<Person> persons = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<population; i++){
            if(random.nextDouble() < 0.15)
                persons.add(new Person(new Resist(), random.nextInt(simulation.getSimulationWidth()),
                        random.nextInt(simulation.getSimulationHeight())));
            else persons.add(new Person(new Healthy(), random.nextInt(simulation.getSimulationWidth()),
                    random.nextInt(simulation.getSimulationHeight())));
        }
        simulation.add(persons);
        return simulation;
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Person person : population) {
            g2d.setPaint(person.getColor());
            double[] position = person.getPosition().getComponents();
            g2d.fillOval((int) position[0], (int) position[1], 13, 13);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Person person: population) {
            person.move();
        }
        checkDistanceBetweenPeople();
        checkBorders();
        repaint();
    }

    private void checkBorders(){
        for (Iterator<Person> it = population.iterator(); it.hasNext();) {
            Person next = it.next();
            if (onTheBorder(next)){
                if(new Random().nextDouble() <= 0.5) it.remove();
                turnBack(next);
            }
        }
    }

    public void turnBack(Person person){
        double[] comp = person.getPosition().getComponents();
        if(comp[0] <= 0){
            person.leftBorder();
        } else if(comp[0] >= this.simulationWidth - 10){
            person.rightBorder();
        } else if(comp[1] <= 0 ){
            person.topBorder();
        } else{
            person.bottomBorder();
        }
    }

    public Person newPersonOnTheBorder(){
        Random random = new Random();
        double where = random.nextDouble();
        IState state = Person.randomState();
        Person person;
        if(where <= 0.25) person =  new Person(state, 0, random.nextInt(this.simulationHeight));
        else if( where <= 0.5) person =  new Person(state, this.simulationWidth-10, random.nextInt(this.simulationHeight) );
        else if( where <= 0.75) person =  new Person(state, random.nextInt(this.simulationWidth), 0);
        else person =  new Person(state, random.nextInt(this.simulationWidth) , this.simulationHeight - 10);
        turnBack(person);
        return person;
    }

    public boolean onTheBorder(Person person){
        double[] comp = person.getPosition().getComponents();
        return comp[0] <= 0 || comp[0] >= this.simulationWidth - 10 || comp[1] <= 0 || comp[1] >= simulationHeight - 10;
    }

    public boolean isOn(){
        return nextSimulationStepTimer.isRunning();
    }
    public void startSimulation(){
        nextSimulationStepTimer.start();
        newMovementTimer.start();
        newPersonOnTheBorderTimer.start();

    }
    public void stopSimulation(){
        nextSimulationStepTimer.stop();
        newMovementTimer.stop();
        newPersonOnTheBorderTimer.stop();
    }

    public void saveToFile(PrintWriter writer){
        writer.println(simulationWidth / METER + "," + simulationHeight / METER + "," + startingPopulation);
        for(Person person: population){
            StringBuilder builder = new StringBuilder();
            double[] position = person.getPosition().getComponents();
            double[] movement = person.getMovement().getComponents();
            int timeWithSymptoms = person.getTimeToGetSickFromPersonWithSymptoms();
            int timeWithoutSymptoms = person.getTimeToGetSickFromPersonWithoutSymptoms();
            int timeToResist = person.getTimeToResistance();
            builder.append(person.getState().getName());
            builder.append(",");
            builder.append(position[0]);
            builder.append(",");
            builder.append(position[1]);
            builder.append(",");
            builder.append(movement[0]);
            builder.append(",");
            builder.append(movement[1]);
            builder.append(",");
            builder.append(timeWithSymptoms);
            builder.append(",");
            builder.append(timeWithoutSymptoms);
            builder.append(",");
            builder.append(timeToResist);
            writer.println(builder);
        }
        writer.close();
    }

    private void checkDistanceBetweenPeople(){
        for(Person person: population) {
            boolean nearSickPersonWithSymptoms = false;
            boolean nearSickPersonWithoutSymptoms = false;

            if(person.getColor() == Color.blue) continue;

            if(person.getColor() == Color.red || person.getColor() == Color.black){
                person.nextStepResist();
            } else{
                for(Person person1: population){
                    if(person1.getColor() == Color.red){
                        if(person.getDistance(person1.getPosition()) <= 2* METER){
                            person.nextStepSickFromPersonWithSymptoms();
                            nearSickPersonWithSymptoms = true;
                        }
                    }
                    if (person1.getColor() == Color.black){
                        if(person.getDistance(person1.getPosition()) <= 2* METER){
                            person.nextStepSickFromPersonWithoutSymptoms();
                            nearSickPersonWithoutSymptoms = true;
                        }
                    }
                }
            }
            if(!nearSickPersonWithSymptoms) person.resetTimeToGetSickFromPersonWithSymptoms();
            if(!nearSickPersonWithoutSymptoms) person.resetTimeToGetSickFromPersonWithoutSymptoms();
        }
    }
}
