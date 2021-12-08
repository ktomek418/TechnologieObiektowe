package emergencyNotification;

import emergencyNotification.emergencyState.IEmergencyState;

public interface EmergencyObserver {

    void update(IEmergencyState state);
}
