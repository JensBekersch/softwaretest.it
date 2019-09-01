package it.softwaretest.app.ws.command;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}
