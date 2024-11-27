package ui.gui.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Event;
import model.EventLog;

public class ExitAppListener extends WindowAdapter {

    @Override
    // EFFECTS: prints all logged events to console before closing window
    public void windowClosing(WindowEvent e) {

        for (Event event : EventLog.getInstance()) {

            System.out.println(event.toString());
        }
    }
}
