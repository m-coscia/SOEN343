package src.main.java.Observer;

import src.main.java.logic.Profile;

import java.io.IOException;

public interface Observer {
    public void update(Profile user) throws IOException;
}
