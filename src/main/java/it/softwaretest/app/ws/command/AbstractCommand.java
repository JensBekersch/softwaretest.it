package it.softwaretest.app.ws.command;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    protected List<Command> commandQueue = new ArrayList<>();

    public void executeCommands() {
        this.buildCommands();

        for (Command command: this.commandQueue) {
            command.execute();
        }
    }

    public abstract void buildCommands();

    protected void addCommand(Command command) {
        this.commandQueue.add(command);
    }

}
