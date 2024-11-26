package ui.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.Budget;
import model.budgetentries.BudgetEntry;
import persistence.JsonHandler;
import ui.gui.GraphicalBudgetingApp;

public class SummaryButtonListener implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;

    GraphicalBudgetingApp app;
    Budget currentBudget;
    JsonHandler jsonHandler;

    // EFFECTS: creates new SummaryButtonListener with given associated app
    public SummaryButtonListener(GraphicalBudgetingApp app) {
        this.app = app;
    }

    @Override
    // EFFECTS: opens summary window for current budget
    public void actionPerformed(ActionEvent e) {

        currentBudget = app.getCurrentBudget();
        List<BudgetEntry> budgetEntriesRanked = currentBudget.getBudgeter().rankSpending();
        List<String> budgetEntriesRankedFinal = budgetEntriesRankedToString(budgetEntriesRanked);

        // Creating Window
        JFrame createBudgetEntryWindow = new JFrame();
        createBudgetEntryWindow.setSize(WIDTH, HEIGHT);
        createBudgetEntryWindow.setResizable(true);

        JList<String> listComponent = new JList<String>();
        JScrollPane listScroller = new JScrollPane(listComponent);

        String[] summaryListData = budgetEntriesRankedFinal.toArray(new String[0]);
        listComponent.setListData(summaryListData);

        // Add components to the frame
        createBudgetEntryWindow.add(listScroller);

        createBudgetEntryWindow.setVisible(true);
    }

    // EFFECTS: returns given list of BudgetEntry as list of their names and actual
    // amounts
    private List<String> budgetEntriesRankedToString(List<BudgetEntry> budgetEntriesRanked) {

        List<String> budgetEntriesRankedStrings = new ArrayList<String>();

        for (BudgetEntry budgetEntry : budgetEntriesRanked) {

            String name = budgetEntry.getName();
            String actualAmount = String.valueOf(budgetEntry.getActualAmount());

            budgetEntriesRankedStrings.add(name + " | " + actualAmount);
        }

        return budgetEntriesRankedStrings;
    }

}
