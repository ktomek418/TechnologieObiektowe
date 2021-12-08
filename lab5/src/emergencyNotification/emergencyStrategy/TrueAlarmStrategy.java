package emergencyNotification.emergencyStrategy;

import Simulation.Simulation;

import java.util.Random;

public class TrueAlarmStrategy implements EmergencyStrategy{

    @Override
    public int getRequiredTime() {
        return Simulation.FPS * 5 + new Random().nextInt(Simulation.FPS * 20);
    }
}
