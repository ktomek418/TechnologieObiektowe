package emergencyNotification;

import Vector.IVector;
import emergencyNotification.emergencyState.Completed;
import emergencyNotification.emergencyState.IEmergencyState;
import emergencyNotification.emergencyState.InProgress;
import emergencyNotification.emergencyState.NotStarted;
import emergencyNotification.emergencyStrategy.EmergencyStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReportedAlarm implements IReportedAlarm {

    private final IVector position;
    private IEmergencyState state;
    private final ArrayList<EmergencyObserver> observers;
    private final EmergencyStrategy strategy;
    private final int requiredCars;
    private Timer requiredTime;
    private Color color;

    public ReportedAlarm(IVector position, EmergencyStrategy strategy, int requiredCars, Color color){
        this.position = position;
        this.state = new NotStarted();
        this.observers = new ArrayList<>();
        this.strategy = strategy;
        this.requiredCars = requiredCars;
        this.color = color;
    }

    @Override
    public IVector getPosition() {
        return position;
    }

    @Override
    public IEmergencyState getState() {
        return state;
    }

    public int missingCars(){
        return requiredCars - observers.size();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void startTimer(){
        this.state = new InProgress();
        requiredTime = new Timer(strategy.getRequiredTime() * 40, e -> finish());
        requiredTime.start();
    }

    private void finish(){
        this.requiredTime.stop();
        this.state = new Completed();
        notifyAllObservers();
    }

    @Override
    public void addObserver(EmergencyObserver observer){
        if(observer != null) this.observers.add(observer);
    }

    @Override
    public void addObserver(ArrayList<EmergencyObserver> observers){
        for (EmergencyObserver next : observers) {
            addObserver(next);
        }
    }

    @Override
    public boolean hasRequiredCars(){
        return requiredCars == observers.size();
    }

    public void removeAllObserver(){
        observers.removeAll(observers);
    }

    public void notifyAllObservers(){
        observers.forEach(observer -> observer.update(this.state));
        removeAllObserver();
    }


}
