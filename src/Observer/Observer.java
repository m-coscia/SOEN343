package src.Observer;

import src.Observer.Events.Event;
import src.logic.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Observer {
    public void update(Event event) throws IOException;

}
