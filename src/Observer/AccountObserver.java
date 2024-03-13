package src.Observer;

import src.logic.Profile;

import java.io.File;

public class AccountObserver implements Observer {
    @Override
    public void update(Profile user) {
        File f = new File("accountLog.txt");

    }
}
