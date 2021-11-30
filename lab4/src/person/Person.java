package person;

import Vector.IVector;
import Vector.Vector2D;
import simulation.Simulation;
import states.*;

import java.awt.*;
import java.util.Random;

public class Person {

    private IState state;
    private final IVector position;
    private IVector movement;
    private static final Random random = new Random();
    private int timeToGetSickFromPersonWithoutSymptoms = 55;
    private int timeToGetSickFromPersonWithSymptoms = 55;
    private int timeToResistance = 0;

    public Person(IState state, double xPos, double yPos){
        this.state = state;
        position = new Vector2D(xPos, yPos);
        generateMovement();
        if(state.getColor() == Color.black || state.getColor() == Color.red) timeToResistance =
                (20 * Simulation.FPS) + (random.nextInt(10) * Simulation.FPS);
    }

    public Person(IState state, IVector position, IVector movement, int timeToGetSickFromPersonWithSymptoms,
                  int timeToGetSickFromPersonWithoutSymptoms, int timeToResistance){
        this.state = state;
        this.position = position;
        this.movement = movement;
        this.timeToGetSickFromPersonWithSymptoms = timeToGetSickFromPersonWithSymptoms;
        this.timeToGetSickFromPersonWithoutSymptoms = timeToGetSickFromPersonWithoutSymptoms;
        this.timeToResistance = timeToResistance;
    }

    public Color getColor(){
        return state.getColor();
    }

    public IVector getPosition() {
        return position;
    }

    public IVector getMovement() {return movement;}

    public int getTimeToGetSickFromPersonWithoutSymptoms() {
        return timeToGetSickFromPersonWithoutSymptoms;
    }

    public int getTimeToGetSickFromPersonWithSymptoms() {
        return timeToGetSickFromPersonWithSymptoms;
    }

    public IState getState() {
        return state;
    }

    public int getTimeToResistance() {
        return timeToResistance;
    }

    public void generateMovement(){
        if(random.nextDouble() <= 0.15 || movement == null){
            double max = Simulation.METER * 2.5;
            double x_movement = random.nextInt((int) max);
            double y_movement = random.nextInt((int)Math.sqrt(Math.pow(max, 2) - Math.pow(x_movement, 2)) );
            double whichDirection = random.nextDouble();
            if(whichDirection <= 0.25) this.movement = new Vector2D(x_movement, y_movement);
            else if( whichDirection <= 0.5) this.movement = new Vector2D(-1 * x_movement, y_movement);
            else if( whichDirection <= 0.75) this.movement = new Vector2D(-1 * x_movement, -1 * y_movement);
            else  this.movement = new Vector2D( x_movement,-1 * y_movement);

        }
    }
    public void move(){
        double[] comp = movement.getComponents();
        this.position.moveX(comp[0] / Simulation.FPS);
        this.position.moveY(comp[1]/ Simulation.FPS);
    }


    public void nextStepSickFromPersonWithSymptoms(){
        this.timeToGetSickFromPersonWithSymptoms --;
        if(timeToGetSickFromPersonWithSymptoms <= 0) {
            if(random.nextDouble() <= 0.5) state = new SickWithSymptoms();
            else state = new SickWithoutSymptoms();
            timeToResistance = (20 * Simulation.FPS) + (random.nextInt(10) * Simulation.FPS);
        }
    }
    public void nextStepSickFromPersonWithoutSymptoms(){
        this.timeToGetSickFromPersonWithoutSymptoms --;
        if(timeToGetSickFromPersonWithoutSymptoms <= 0) {
            if(getSick()){
                if(random.nextDouble() <= 0.5) state = new SickWithSymptoms();
                else state = new SickWithoutSymptoms();
                timeToResistance = (20 * Simulation.FPS) + (random.nextInt(10) * Simulation.FPS);
            }
        }
    }
    public void nextStepResist(){
        this.timeToResistance --;
        if(timeToResistance == 0) state = new Resist();
    }

    public void resetTimeToGetSickFromPersonWithSymptoms(){
        this.timeToGetSickFromPersonWithSymptoms = 55;
    }
    public void resetTimeToGetSickFromPersonWithoutSymptoms(){
        this.timeToGetSickFromPersonWithoutSymptoms = 55;
    }

    private boolean getSick(){
        return random.nextDouble() < 0.5;
    }

    public double getDistance(IVector vector){
        return position.getDistance(vector);
    }


    public void leftBorder(){
        double max = Simulation.METER * 2.5;
        double x_movement = random.nextInt((int) max);
        double y_movement = random.nextInt((int)Math.sqrt(Math.pow(max, 2) - Math.pow(x_movement, 2)));
        if(random.nextDouble() < 0.5) y_movement *= -1;
        this.movement = new Vector2D( x_movement, y_movement);
    }
    public void rightBorder(){
        double max = Simulation.METER * 2.5;
        double x_movement = random.nextInt((int) max) * -1;
        double y_movement = random.nextInt((int)Math.sqrt(Math.pow(max, 2) - Math.pow(x_movement, 2)));
        if(random.nextDouble() < 0.5) y_movement *= -1;
        this.movement = new Vector2D( x_movement, y_movement);
    }
    public void topBorder(){
        double max = Simulation.METER * 2.5;
        double x_movement = random.nextInt((int) max);
        double y_movement = random.nextInt((int)Math.sqrt(Math.pow(max, 2) - Math.pow(x_movement, 2)));
        if(random.nextDouble() < 0.5) x_movement *= -1;
        this.movement = new Vector2D( x_movement, y_movement);
    }
    public void bottomBorder(){
        double max = Simulation.METER * 2.5;
        double x_movement = random.nextInt((int) max);
        double y_movement = random.nextInt((int)Math.sqrt(Math.pow(max, 2) - Math.pow(x_movement, 2)) ) * -1;
        if(random.nextDouble() < 0.5) x_movement *= -1;
        this.movement = new Vector2D( x_movement, y_movement);
    }


    public static IState randomState(){
        double rand = random.nextDouble();
        if(rand <= 0.05) return new SickWithSymptoms();
        else if (rand <= 0.10) return new SickWithoutSymptoms();
        else return new Healthy();
    }



}
