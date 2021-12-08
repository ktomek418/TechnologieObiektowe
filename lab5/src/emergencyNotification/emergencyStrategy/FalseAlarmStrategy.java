package emergencyNotification.emergencyStrategy;

public class FalseAlarmStrategy implements EmergencyStrategy{
    @Override
    public int getRequiredTime() {
        return 0;
    }
}
