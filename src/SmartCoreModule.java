package src;

public class SmartCoreModule extends SmartHomeModule {
    public SmartCoreModule() {
    }

    @Override
    public void setParentPermissions() {
        getParentPermissions().setDoorsPermission(true);
        getParentPermissions().setWindowsPermission(true);
        getParentPermissions().setLightsPermission(true);
        getParentPermissions().setGaragePermission(true);
    }

    @Override
    public void setChildPermissions() {
        getChildPermissions().setDoorsPermission(true);
        getChildPermissions().setWindowsPermission(true);
        getChildPermissions().setLightsPermission(true);
        getChildPermissions().setGaragePermission(true);

    }

    @Override
    public void setGuestPermissions() {
        getGuestPermissions().setDoorsPermission(true);
        getGuestPermissions().setWindowsPermission(true);
        getGuestPermissions().setLightsPermission(true);
        getGuestPermissions().setGaragePermission(true);

    }

    @Override
    public void setStrangerPermissions() {
        getStrangerPermissions().setDoorsPermission(true);
        getStrangerPermissions().setWindowsPermission(true);
        getStrangerPermissions().setLightsPermission(true);
        getStrangerPermissions().setGaragePermission(true);

    }
}
