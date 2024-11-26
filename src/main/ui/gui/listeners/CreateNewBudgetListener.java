package ui.gui.listeners;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Budget;
import ui.gui.GraphicalBudgetingApp;

public class CreateNewBudgetListener implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    GraphicalBudgetingApp app;
    List<Budget> budgets;

    // EFFECTS: creates a new CreateNewBudgetListener with given app and app's List<Budget>
    public CreateNewBudgetListener(GraphicalBudgetingApp app) {

        this.app = app;
        budgets = app.getBudgets();
    }

    @Override
    // EFFECTS: Opens window for making new budgets
    public void actionPerformed(ActionEvent e) {
        System.out.println("Opening New Budget Maker Window");

        // Creating Window
        JFrame createBudgetWindow = new JFrame();
        createBudgetWindow.setSize(WIDTH, HEIGHT);
        createBudgetWindow.setLayout(new GridLayout(4, 2));
        createBudgetWindow.setResizable(false);

        // Create input fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel startDateLabel = new JLabel("Start Date: ");
        JTextField startDateField = new JTextField();

        JLabel endDateLabel = new JLabel("End Date: ");
        JTextField endDateField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new SaveNewBudgetListener(createBudgetWindow, nameField,
                startDateField, endDateField, app));

        // Add components to the frame
        createBudgetWindow.add(nameLabel);
        createBudgetWindow.add(nameField);
        createBudgetWindow.add(startDateLabel);
        createBudgetWindow.add(startDateField);
        createBudgetWindow.add(endDateLabel);
        createBudgetWindow.add(endDateField);
        createBudgetWindow.add(createButton);

        createBudgetWindow.setVisible(true);

    }
}
