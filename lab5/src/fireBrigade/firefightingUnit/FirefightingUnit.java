package fireBrigade.firefightingUnit;

import Vector.IVector;
import Vector.Vector2D;
import emergencyNotification.EmergencyObserver;
import fireBrigade.fireTruck.FireTruck;
import fireBrigade.fireTruck.IFireTruck;
import fireBrigade.fireTruck.fireTruckState.Free;

import java.util.ArrayList;

public class FirefightingUnit implements IFirefightingUnit {

    private final IVector position;
    private ArrayList<FireTruck> fireTrucks;


    public FirefightingUnit(IVector position){
        this.position = position;
        createTruck();
    }

    @Override
    public IVector getPosition() {
        return position;
    }

    @Override
    public ArrayList<FireTruck> getFireTrucks() {
        return fireTrucks;
    }

    @Override
    public double getDistance(IVector position){
        return this.position.getDistance(position);
    }

    private void createTruck(){
        double[] components = position.getComponents();
        this.fireTrucks = new ArrayList<>();
        this.fireTrucks.add(new FireTruck(new Vector2D(components[0] + 2, components[1] - 10), new Free()));
        this.fireTrucks.add(new FireTruck(new Vector2D(components[0] - 11, components[1] ), new Free()));
        this.fireTrucks.add(new FireTruck(new Vector2D(components[0] + 15, components[1] ), new Free()));
        this.fireTrucks.add(new FireTruck(new Vector2D(components[0] - 11, components[1] + 13), new Free()));
        this.fireTrucks.add(new FireTruck(new Vector2D(components[0] + 15, components[1] + 13), new Free()));
    }

    @Override
    public ArrayList<EmergencyObserver> sendUnits(int toSend, IVector position){
        int carsSent = 0;
        ArrayList<EmergencyObserver> trucks = new ArrayList<>();
        for(FireTruck fireTruck: fireTrucks){
            if(fireTruck.getState().equals("Free")){
                fireTruck.send(position);
                trucks.add(fireTruck);
                carsSent ++;
            }
            if(carsSent == toSend) break;
        }
        return trucks;
    }

    @Override
    public void nextMove(){
        for(IFireTruck fireTruck: fireTrucks){
                fireTruck.move();
        }
    }
}
