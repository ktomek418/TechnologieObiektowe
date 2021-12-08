package emergencyNotification;

import Vector.IVector;
import emergencyNotification.emergencyState.IEmergencyState;

import java.util.ArrayList;

public interface IReportedAlarm {
    IVector getPosition();

    IEmergencyState getState();

    void startTimer();

    void addObserver(EmergencyObserver observer);

    void addObserver(ArrayList<EmergencyObserver> observers);

    boolean hasRequiredCars();
}
