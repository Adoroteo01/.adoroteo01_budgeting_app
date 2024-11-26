package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import model.Budget;
import persistence.JsonHandler;
import ui.gui.GraphicalBudgetingApp;

public class LoadButtonListener implements ActionListener {

    GraphicalBudgetingApp app;
    List<Budget> appBudgets;
    JsonHandler jsonHandler;

    // EFFECTS: creates new LoadButtonListener with given associated app
    public LoadButtonListener(GraphicalBudgetingApp app) {
        this.app = app;
        appBudgets = app.getBudgets();
        jsonHandler = new JsonHandler();
    }

    @Override
    // EFFECTS: loads save file into app
    public void actionPerformed(ActionEvent e) {

        List<Budget> loadedBudgets; // = jsonHandler.loadBudgetsFromFile();

        try {
            loadedBudgets = jsonHandler.loadBudgetsFromFile("data/save.json");

            appBudgets.clear();
            appBudgets.addAll(loadedBudgets);

            app.setCurrentBudget(appBudgets.get(0));
            app.updateScrollingList(app.getBudgetsScroller(), app.getBudgetNames());
            app.updateBudgetEntriesList();
            app.updateTrackerEntriesList();

            System.out.println("file loaded sucessfully!!!");
        } catch (IOException ex) {
            System.out.println("Could not load file...");
        }
    }

}
