package fireBrigade.firefightingUnit;

import Vector.IVector;
import emergencyNotification.EmergencyObserver;
import fireBrigade.fireTruck.FireTruck;

import java.util.ArrayList;

public interface IFirefightingUnit {
    IVector getPosition();

    ArrayList<FireTruck> getFireTrucks();

    double getDistance(IVector position);

    ArrayList<EmergencyObserver> sendUnits(int toSend, IVector position);

    void nextMove();
}
