package src.components;

import src.commands.Command;

public abstract class Component {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand(){
        command.execute();
    }
}
