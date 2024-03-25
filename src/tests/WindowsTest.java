package src.tests;

import org.junit.Test;
import src.components.Windows;

import static org.junit.Assert.*;

public class WindowsTest {

    @Test
    public void testDefaultConstructor() {
        Windows windows = new Windows();
        assertFalse(windows.getObstructed());
        assertFalse(windows.getOpen());
    }

    @Test
    public void testCloseWindowsCommand() {
        Windows windows = new Windows();
        windows.closeWindowsCommand();
        assertFalse(windows.getOpen());
    }

    @Test
    public void testOpenWindowsCommand() {
        Windows windows = new Windows();
        windows.openWindowsCommand();
        assertTrue(windows.getOpen());
    }

    @Test
    public void testSetObstructed() {
        Windows windows = new Windows();
        assertFalse(windows.getObstructed());
        windows.setObstructed(true);
        assertTrue(windows.getObstructed());
    }

    @Test
    public void testGetObstructed() {
        Windows windows = new Windows();
        assertFalse(windows.getObstructed());
        windows.setObstructed(true);
        assertTrue(windows.getObstructed());
    }
}
