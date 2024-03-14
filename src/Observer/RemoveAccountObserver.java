package src.Observer;

import src.logic.Profile;

import java.io.File;

public class RemoveAccountObserver implements Observer {
    private File accountLog = new File("accountLog.txt");

    @Override
    public void update(Profile user) {

    }
}
