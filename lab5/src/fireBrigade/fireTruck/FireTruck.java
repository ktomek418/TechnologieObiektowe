package fireBrigade.fireTruck;

import Vector.IVector;
import Vector.Vector2D;
import emergencyNotification.EmergencyObserver;
import emergencyNotification.emergencyState.IEmergencyState;
import fireBrigade.fireTruck.fireTruckState.FireTruckState;
import fireBrigade.fireTruck.fireTruckState.Free;
import fireBrigade.fireTruck.fireTruckState.InAction;
import fireBrigade.fireTruck.fireTruckState.OnTheWay;

import java.awt.*;

import java.util.Random;

public class FireTruck implements IFireTruck, EmergencyObserver {

    private static final Random random = new Random();

    private final IVector startingPosition;
    private IVector currentPosition;
    private IVector destination;
    private IVector movement;
    private FireTruckState state;
    private int speed;
    private boolean goingHome = false;

    public FireTruck(IVector startingPosition, FireTruckState state){
        this.startingPosition = startingPosition;
        double[] comp = startingPosition.getComponents();
        this.currentPosition = new Vector2D(comp[0], comp[1]) ;
        this.state = state;
    }

    @Override
    public IVector getPosition() {
        return currentPosition;
    }

    @Override
    public String getState() {
        return state.getState();
    }

    @Override
    public Color getColor() {
        return state.getColor();
    }

    @Override
    public boolean inAction(){
        return state.getState().equals("InAction");
    }

    public boolean atDesignatedPlace(){
        if(destination==null) return false;
        return currentPosition.getDistance(destination) < 15;
    }
    private boolean atHome(){
        return this.currentPosition.getDistance(this.startingPosition) < 10;
    }

    public void send(IVector dest){
        this.state = new OnTheWay();
        this.speed = (int) dest.getDistance(currentPosition) / 4 + random.nextInt(50);
        this.destination = dest;
        this.movement = dest.getMovement(currentPosition);
        this.goingHome = false;
    }

    public void goHome(){
        this.goingHome = true;
        this.destination = null;
        this.movement = this.startingPosition.getMovement(currentPosition);
        this.state = new OnTheWay();
    }

    private void setHome(){
        this.state = new Free();
        double[] startingPosition = this.startingPosition.getComponents();
        this.currentPosition = new Vector2D(startingPosition[0], startingPosition[1]);
        this.destination = null;
        this.movement = null;
    }

    public void move(){
        if(this.state.getState().equals("OnTheWay")){
            if(goingHome){
                if(atHome()) setHome();
                else step();
            }
            else if(atDesignatedPlace()) {
                this.state = new InAction();
            } else step();
        }
        }



    private void step(){
        double[] comp = this.movement.getComponents();
        currentPosition.moveX(comp[0]/speed);
        currentPosition.moveY(comp[1]/speed);
    }


    @Override
    public void update(IEmergencyState state) {
        if(state.getName().equals("Completed")){
            goHome();
        }
    }
}
