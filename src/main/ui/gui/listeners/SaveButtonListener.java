package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import model.Budget;
import persistence.JsonHandler;
import ui.gui.GraphicalBudgetingApp;

public class SaveButtonListener implements ActionListener {

    GraphicalBudgetingApp app;
    List<Budget> appBudgets;
    JsonHandler jsonHandler;

    // EFFECTS: creates new SaveButtonListener with given associated app
    public SaveButtonListener(GraphicalBudgetingApp app) {
        this.app = app;
        appBudgets = app.getBudgets();
        jsonHandler = new JsonHandler();
    }

    @Override
    // EFFECTS: creates save file of app
    public void actionPerformed(ActionEvent e) {

        try {
            jsonHandler.writeBudgetsToFile(appBudgets, "data/", "save");
            System.out.println("File Saved!");
        } catch (FileNotFoundException ex) {
            System.out.println("Saving failed...");
        }
    }

}
