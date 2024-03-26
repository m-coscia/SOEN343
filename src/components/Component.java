package src.components;

import java.io.IOException;

import src.commands.Command;

public abstract class Component {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand() throws IOException{
        command.execute();
    }
}
