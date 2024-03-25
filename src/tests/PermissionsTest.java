package src.tests;

import org.junit.Test;
import src.logic.Permissions;

import static org.junit.Assert.*;

public class PermissionsTest {

    @Test
    public void testGetAndSetWindowsPermission() {
        Permissions permissions = new Permissions(true, false, false, false, false);
        assertTrue(permissions.getWindowsPermission());
        permissions.setWindowsPermission(false);
        assertFalse(permissions.getWindowsPermission());
    }

    @Test
    public void testGetAndSetDoorsPermission() {
        Permissions permissions = new Permissions(false, true, false, false, false);
        assertTrue(permissions.getDoorsPermission());
        permissions.setDoorsPermission(false);
        assertFalse(permissions.getDoorsPermission());
    }

    @Test
    public void testGetAndSetGarageDoorPermission() {
        Permissions permissions = new Permissions(false, false, true, false, false);
        assertTrue(permissions.getGarageDoorPermission());
        permissions.setGarageDoorPermission(false);
        assertFalse(permissions.getGarageDoorPermission());
    }

    @Test
    public void testGetAndSetLightsPermission() {
        Permissions permissions = new Permissions(false, false, false, true, false);
        assertTrue(permissions.getLightsPermission());
        permissions.setLightsPermission(false);
        assertFalse(permissions.getLightsPermission());
    }

    @Test
    public void testGetAndSetTemperaturePermission() {
        Permissions permissions = new Permissions(false, false, false, false, true);
        assertTrue(permissions.getTemperaturePermission());
        permissions.setTemperaturePermission(false);
        assertFalse(permissions.getTemperaturePermission());
    }
}
