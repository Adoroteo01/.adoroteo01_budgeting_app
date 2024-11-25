package ui.gui.listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui.gui.GraphicalBudgetingApp;

public class SelectedBudgetListener implements ListSelectionListener {

    JList<String> list;
    GraphicalBudgetingApp app;

    public SelectedBudgetListener(JList<String> list, GraphicalBudgetingApp app) {
        this.list = list;
        this.app = app;
    }

    @Override
    // REQUIRES: list.getSelectedValue() returns a name that is the name of an
    // existing budget
    // EFFECTS: changes currentBudget of app to the budget with the same name as
    // selectedBudgetName.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            String selectedBudgetName = list.getSelectedValue();

            app.setCurrentBudget(selectedBudgetName);
            System.out.println("Budget changed to " + selectedBudgetName); // TODO: for testing, delete later
        }
    }

}
