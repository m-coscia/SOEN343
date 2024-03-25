package Observer;

import logic.Profile;

import java.io.IOException;

public interface Observer {
    public void update(Event event) throws IOException;
}
