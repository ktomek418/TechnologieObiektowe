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
    public static final int maxX = 1200;
    public static final int maxY = 650;
    public static final int maxPopulation = 150;
    public static final int METER = 30;


    private ArrayList<Person> persons;
    private Timer stepTimer;
    private Timer newMovement;
    private Timer newPerson;
    private final int simulationWidth;
    private final int simulationHeight;
    private final int startingPopulation;

    public Simulation(){

        this.simulationWidth = maxX;
        this.simulationHeight = maxY;
        this.startingPopulation = maxPopulation;
        this.setBackground(Color.getColor("0xEEEEEE"));
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        this.setPreferredSize(new Dimension(simulationWidth, this.simulationHeight));
        this.setTimers();
    }

    public Simulation(int simulationWidth, int simulationHeight, int population){
        if(simulationWidth * METER > maxX || simulationWidth < 5) this.simulationWidth = maxX;
        else this.simulationWidth = simulationWidth * METER;
        if(simulationHeight * METER > maxY || simulationHeight < 5) this.simulationHeight = maxY;
        else this.simulationHeight = simulationHeight * METER;
        if(population > maxPopulation || population < 0) this.startingPopulation = maxPopulation;
        else this.startingPopulation = population;
        this.setBackground(Color.getColor("0xEEEEEE"));
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
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
        stepTimer = new Timer(1000/FPS, this);
        newMovement = new Timer(500, e ->{for(Person person: persons) person.generateMovement();});
        newPerson = new Timer(280, e-> {
            if(!(persons.size() > startingPopulation + 3))
                persons.add(newPersonOnTheBorder());
        });
    }


    public void add(ArrayList<Person> persons){
        this.persons = persons;
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

    public static Simulation createSimulationWithoutResistance(){
        Simulation simulation = new Simulation();
        ArrayList<Person> persons = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<maxPopulation; i++){
            persons.add(new Person(new Healthy(), random.nextInt(maxX), random.nextInt(maxY)));
        }
        simulation.add(persons);
        return simulation;
    }

    public static Simulation createSimulationWithResistance(){
        Simulation simulation = new Simulation();
        ArrayList<Person> persons = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<maxPopulation; i++){
            if(random.nextDouble() < 0.15)
                persons.add(new Person(new Resist(), random.nextInt(maxX), random.nextInt(maxY)));
            else persons.add(new Person(new Healthy(), random.nextInt(maxX), random.nextInt(maxY)));
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
        for (Person person : persons) {
            g2d.setPaint(person.getColor());
            double[] position = person.getPosition().getComponents();
            g2d.fillOval((int) position[0], (int) position[1], 13, 13);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Person person: persons) {
            person.move();
        }
        checkDistanceBetweenPeople();
        checkBorders();
        repaint();
    }

    private void checkDistanceBetweenPeople(){
        for(Person person: persons) {
            boolean nearSickPersonWithSymptoms = false;
            boolean nearSickPersonWithoutSymptoms = false;

            if(person.getColor() == Color.blue) continue;

            if(person.getColor() == Color.red || person.getColor() == Color.black){
                person.nextStepResist();
            } else{
                for(Person person1: persons){
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

    private void checkBorders(){
        for (Iterator<Person> it = persons.iterator(); it.hasNext();) {
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
    public void startSimulation(){
        stepTimer.start();
        newMovement.start();
        newPerson.start();

    }
    public void stopSimulation(){
        stepTimer.stop();
        newMovement.stop();
        newPerson.stop();
    }

    public boolean isOn(){
        return stepTimer.isRunning();
    }

    public void saveToFile(PrintWriter writer){
        writer.println(simulationWidth / METER + "," + simulationHeight / METER + "," + startingPopulation);
        for(Person person: persons){
            StringBuilder builder = new StringBuilder();
            double[] postition = person.getPosition().getComponents();
            double[] movement = person.getMovement().getComponents();
            int timeWithSymptoms = person.getTimeToGetSickFromPersonWithSymptoms();
            int timeWithoutSymptoms = person.getTimeToGetSickFromPersonWithoutSymptoms();
            int timeToResist = person.getTimeToResistance();
            builder.append(person.getState().getName());
            builder.append(",");
            builder.append(postition[0]);
            builder.append(",");
            builder.append(postition[1]);
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
}
