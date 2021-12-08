package fireBrigade;

import Converter.CoordinateConverter;
import Vector.IVector;
import Vector.Vector2D;
import emergencyNotification.ReportedAlarm;
import emergencyNotification.EmergencyObserver;
import fireBrigade.fireTruck.FireTruck;
import fireBrigade.firefightingUnit.FirefightingUnit;

import java.util.*;

public class SKKM {

    private final static IVector FIRST = new Vector2D(50.05996570858558, 19.943146420623105);
    private final static IVector SECOND = new Vector2D(50.03342211752489, 19.9358444860342);
    private final static IVector THIRD = new Vector2D(50.075730116164856, 19.88732172239953);
    private final static IVector FOURTH = new Vector2D(50.03770900932225, 20.005782620294);
    private final static IVector FIFTH = new Vector2D(50.092245910577475, 19.922180469444026);
    private final static IVector SIXTH = new Vector2D(50.01615701644086, 20.01561495090194);
    private final static IVector SEVENTH = new Vector2D(50.09414622624687, 19.977350222048297);
    private final static IVector SKAWINA = new Vector2D(49.96841421424377, 19.799588734841137);
    private final static IVector ASPIRANTOW = new Vector2D(50.077101267126466, 20.032646343428127);
    private final static IVector BALICE = new Vector2D(50.07708286470859, 19.788568371447585);

    private ArrayList<FirefightingUnit> firefightingUnits;
    private HashMap<ReportedAlarm, ArrayList<FireTruck>> emergencyNotifications;

    public SKKM(){
        createUnits();
    }

    private void createUnits(){
        this.firefightingUnits = new ArrayList<>();
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(FIRST)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(SECOND)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(THIRD)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(FOURTH)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(FIFTH)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(SIXTH)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(SEVENTH)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(SKAWINA)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(ASPIRANTOW)));
        firefightingUnits.add(new FirefightingUnit(CoordinateConverter.getPositionInPixels(BALICE)));
        this.emergencyNotifications = new HashMap<>();
    }

    public ArrayList<FirefightingUnit> getFirefightingUnits() {
        return firefightingUnits;
    }

    private void sortEmergency(IVector position){
        Comparator<FirefightingUnit> comparator
                = (h1, h2) -> (int) (h1.getDistance(position) - h2.getDistance(position));
        firefightingUnits.sort(comparator);
    }

    public void addEmergency(ReportedAlarm emergency){
        this.emergencyNotifications.put(emergency, new ArrayList<>());
    }


    private void checkEmergency(){
        Iterator<ReportedAlarm> itr = emergencyNotifications.keySet().iterator();
        while (itr.hasNext()) {
            ReportedAlarm emergency = itr.next();
            if(emergency.getState().getName().equals("Completed")) itr.remove();
            else if(emergency.hasRequiredCars()){
                    if(carsAreInPlace(emergency)){
                        emergency.startTimer();
                    }
            } else sendHelp(emergency);
        }
    }

    private boolean carsAreInPlace(ReportedAlarm emergency){
        boolean ok = true;
        ArrayList<FireTruck> cars = emergencyNotifications.get(emergency);
        for(FireTruck truck: cars) if(!truck.atDesignatedPlace()) ok = false;
        return ok;
    }

    public ArrayList<ReportedAlarm> getEmergencyNotifications() {
        return new ArrayList<>(this.emergencyNotifications.keySet());
    }

    private void sendHelp(ReportedAlarm emergency){
        sortEmergency(emergency.getPosition());
        for(FirefightingUnit unit: firefightingUnits){
            ArrayList<EmergencyObserver> cars = unit.sendUnits(emergency.missingCars(), emergency.getPosition());
            emergency.addObserver(cars);
            for(EmergencyObserver obs: cars) emergencyNotifications.get(emergency).add((FireTruck) obs);
            if(emergency.hasRequiredCars()) break;
        }
    }

    public void nextMove(){
        checkEmergency();
        for(FirefightingUnit unit: firefightingUnits){
            unit.nextMove();
        }
    }

}
