package src;

import src.logic.SimulationParameter;

//everytime we move a child or guest, call setPermissions bc they change depending on the room
public class SmartCoreModule extends SmartHomeModule {
    public SmartCoreModule(SimulationParameter param) {
        parameter = param;      //set simulation parameter
    }

    @Override
    public void setParentPermissions() {
        getParentPermissions().setDoorsPermission(true);
        getParentPermissions().setWindowsPermission(true);
        getParentPermissions().setLightsPermission(true);
        getParentPermissions().setGarageDoorPermission(true);
    }

    @Override
    public void setChildPermissions() {
        getChildPermissions().setDoorsPermission(false);
        getChildPermissions().setWindowsPermission(false);
        getChildPermissions().setLightsPermission(false);
        getChildPermissions().setGarageDoorPermission(false);

    }

    @Override
    public void setGuestPermissions() {
        getGuestPermissions().setDoorsPermission(true);
        getGuestPermissions().setWindowsPermission(true);
        getGuestPermissions().setLightsPermission(true);
        getGuestPermissions().setGarageDoorPermission(true);

    }

    @Override
    public void setStrangerPermissions() {
        getStrangerPermissions().setDoorsPermission(false);
        getStrangerPermissions().setWindowsPermission(false);
        getStrangerPermissions().setLightsPermission(false);
        getStrangerPermissions().setGarageDoorPermission(false);

    }
}
