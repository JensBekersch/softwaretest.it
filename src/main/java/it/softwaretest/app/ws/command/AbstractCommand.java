package it.softwaretest.app.ws.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {

    protected List<Command> commandQueue = new ArrayList<>();

    public void executeCommands() throws Exception {
        this.buildCommands();

        for (Command command: this.commandQueue) {
            command.execute();
        }

        this.commandQueue.clear();
    }

    public abstract void buildCommands();

    protected void addCommand(Command command) {
        this.commandQueue.add(command);
    }

}
