package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Budget;
import ui.gui.listeners.CreateNewBudgetListener;

public class GraphicalBudgetingApp {

    private static final int SCREEN_HEIGHT = 512;
    private static final int SCREEN_WIDTH = 720;
    private JFrame appWindow;

    private List<Budget> budgets;

    // Creates new Budgeting app
    public GraphicalBudgetingApp() {

        budgets = new ArrayList<Budget>();

        run();
    }

    // EFFECTS: Runs Budgeting app gui
    private void run() {

        createAppWindow();

        JPanel appLeft = createSideMenu();
        JPanel appRight = createAppRightPanel();

        appWindow.add(appLeft, BorderLayout.WEST);
        appWindow.add(appRight, BorderLayout.CENTER);

        appWindow.setVisible(true);
    }

    // EFFECT: returns Jpanel for app content with padding on the left
    private JPanel createAppRightPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel padding = createPadding(0, 55, 0, 0);

        // Main Content
        JPanel content = createContentPanel();

        // Adding compenents
        panel.add(padding, BorderLayout.WEST);
        panel.add(content, BorderLayout.CENTER);
        return panel;
    }

    // EFFECT: returns empty JPanel that has top space at the top, left space on the
    // left, bottom space at the bottom, and right space on the right
    private JPanel createPadding(int top, int left, int bottom, int right) {
        // Padding
        JPanel padding = new JPanel();
        padding.setBorder(new EmptyBorder(top, left, bottom, right));
        // padding.setBackground(Color.RED); // TODO: Delete later. For testing
        return padding;
    }

    // EFFECT: returns JPanel for the main content of the app such as adding
    // budgets, budget entries, tracker entries, etc.
    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Top Logo bar
        JPanel titleBar = createTitleBar();

        JPanel content = createMainContentPanel();

        panel.add(titleBar, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTitleBar() {
        JPanel titleBar = new JPanel();
        titleBar.setLayout(new BoxLayout(titleBar, BoxLayout.Y_AXIS));

        JLabel titleBarLabel = new JLabel("Budget.ly");
        titleBarLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleBarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleBar.add(titleBarLabel);
        return titleBar;
    }

    private JPanel createMainContentPanel() {

        // TODO: resume here
        // TODO: seperate this into more methods
        // TODO: implement budget entry adding
        // TODO: implement tracker entry adding

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JButton newBudgetWindowOpener = new JButton("New Budget");
        newBudgetWindowOpener.addActionListener(new CreateNewBudgetListener(budgets));

        List<String> budgetsSampleData = new ArrayList<String>();
        budgetsSampleData.add("Jan");
        budgetsSampleData.add("Feb");
        budgetsSampleData.add("Mar");
        String[] budgetsSampleArray = budgetsSampleData.toArray(new String[0]);

        List<String> budgetEntriesSampleData = new ArrayList<String>();
        budgetEntriesSampleData.add("Expense 1");
        budgetEntriesSampleData.add("Expense 2");
        budgetEntriesSampleData.add("Expense 3");
        String[] budgetEntriesSampleArray = budgetEntriesSampleData.toArray(new String[0]);

        List<String> trackerSampleData = new ArrayList<String>();
        trackerSampleData.add("Jan 1 - $20");
        trackerSampleData.add("Jan 2 - $30");
        trackerSampleData.add("jan 2 - $4");
        String[] trackerSampleArray = trackerSampleData.toArray(new String[0]);

        JList<String> budgetList = new JList<>(budgetsSampleArray);
        JList<String> budgetEntriesList = new JList<>(budgetEntriesSampleArray);
        JList<String> trackerList = new JList<>(trackerSampleArray);

        JScrollPane budgetsScroller = new JScrollPane(budgetList);
        budgetsScroller.setPreferredSize(new Dimension(40, 500)); // TODO: remove later, for testing

        JScrollPane budgetEntriesScroller = new JScrollPane(budgetEntriesList);
        budgetEntriesScroller.setPreferredSize(new Dimension(40, 500)); // TODO: remove later, for testing

        JScrollPane trackerScroller = new JScrollPane(trackerList);
        trackerScroller.setPreferredSize(new Dimension(40, 500)); // TODO: remove later, for testing

        panel.add(new JScrollPane(budgetsScroller));
        panel.add(newBudgetWindowOpener);
        panel.add(new JScrollPane(budgetEntriesScroller));
        panel.add(new JButton("New Budget Entry"));
        panel.add(new JScrollPane(trackerScroller));
        panel.add(new JButton("New Tracker Entry"));

        return panel;
    }

    // EFFECT: returns JPanel for the side menu of the app
    private JPanel createSideMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton loadButton = new JButton("Load File");
        JButton saveButton = new JButton("Save File");
        panel.add(loadButton);
        panel.add(saveButton);

        return panel;
    }

    // EFFECT: initialises the app window
    private void createAppWindow() {
        appWindow = new JFrame("Budget.ly");
        appWindow.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // appWindow.setResizable(false); // TODO: Decide to use resizable or not
        appWindow.setLayout(new BorderLayout());
    }
}
