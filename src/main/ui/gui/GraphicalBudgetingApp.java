package ui.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Budget;
import model.TrackerEntry;
import model.budgetentries.BudgetEntry;
import ui.gui.listeners.CreateNewBudgetEntryListener;
import ui.gui.listeners.CreateNewBudgetListener;
import ui.gui.listeners.CreateNewTrackerEntryListener;
import ui.gui.listeners.SelectedBudgetListener;

public class GraphicalBudgetingApp {

    private static final int SCREEN_HEIGHT = 512;
    private static final int SCREEN_WIDTH = 720;
    private JFrame appWindow;

    private List<Budget> budgets;

    private Budget currentBudget;

    private JScrollPane budgetsScroller;
    private JScrollPane budgetEntriesScroller;
    private JScrollPane trackerEntriesScroller;

    // Creates new Budgeting app
    public GraphicalBudgetingApp() {

        budgets = new ArrayList<Budget>();

        run();
    }

    // EFFECT: returns a list of budget's names in budgets
    public List<String> getBudgetNames() {

        List<String> budgetNames = new ArrayList<String>();

        for (Budget budget : budgets) {

            String name = budget.getName();

            budgetNames.add(name);
        }

        return budgetNames;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public Budget getCurrentBudget() {
        return currentBudget;
    }

    public JScrollPane getBudgetsScroller() {
        return budgetsScroller;
    }

    public JScrollPane getBudgetEntriesScroller() {
        return budgetEntriesScroller;
    }

    public JScrollPane getTrackerEntriesScroller() {
        return trackerEntriesScroller;
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

        // TODO: implement tracker entry adding

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JButton newBudgetWindowOpener = createNewItemButton("New Budget", new CreateNewBudgetListener(this));
        JButton newBudgetEntryWindowOpener = createNewItemButton("New Budget Entry",
                new CreateNewBudgetEntryListener(this));
        JButton newTrackerEntryWindowOpener = createNewItemButton("New Tracker Entry",
                new CreateNewTrackerEntryListener(this));

        budgetsScroller = createScrollingList(this);
        budgetEntriesScroller = createScrollingList();
        trackerEntriesScroller = createScrollingList();

        panel.add(new JScrollPane(budgetsScroller));
        panel.add(newBudgetWindowOpener);
        panel.add(new JScrollPane(budgetEntriesScroller));
        panel.add(newBudgetEntryWindowOpener);
        panel.add(new JScrollPane(trackerEntriesScroller));
        panel.add(newTrackerEntryWindowOpener);

        return panel;
    }

    // REQUIRES: scrollingList contains EXACTLY a single JList<String>
    // EFFECTS: updates the JList<String> inside given scrollingList with the given
    // updatedList
    public void updateScrollingList(JScrollPane scrollingList, List<String> updatedList) {

        JList<String> list = (JList<String>) scrollingList.getViewport().getView();

        String[] newlistData = updatedList.toArray(new String[0]);
        list.setListData(newlistData);
    }

    // EFFECT: updates the contents of budgetEntriesScroller with currentBudget's
    // budget entries
    public void updateBudgetEntriesList() {
        updateScrollingList(budgetEntriesScroller, getCurrentBudgetEntriesName());
    }

    // EFFECT: updates the contents of trackerEntriesScroller with currentBudget's
    // tracker entries
    public void updateTrackerEntriesList() {
        updateScrollingList(trackerEntriesScroller, getCurrentTrackerEntriesName());
    }

    // EFFECT: returns a list of currentBudget's budget entry names
    private List<String> getCurrentBudgetEntriesName() {

        List<String> currentBudgetEntriesNames = new ArrayList<String>();

        for (BudgetEntry budgetEntry : currentBudget.getBudgetEntries()) {

            String name = budgetEntry.getName();
            String budgetAmount = String.valueOf(budgetEntry.getBudgetAmount());
            String actualAmount = String.valueOf(budgetEntry.getActualAmount());

            String rowname = name + " | " + budgetAmount + " | " + actualAmount;
            currentBudgetEntriesNames.add(rowname);
        }

        return currentBudgetEntriesNames;
    }

    // EFFECT: returns a list of strings about the details of currentBudget's
    // tracker entries
    private List<String> getCurrentTrackerEntriesName() {

        List<String> currentTrackerEntriesNames = new ArrayList<String>();

        for (TrackerEntry trackerEntry : currentBudget.getTracker().getEntries()) {

            String budgetEntry = trackerEntry.getBudgetEntry().getName();
            String date = trackerEntry.getDate();
            String amount = Double.toString(trackerEntry.getAmount());

            String name = amount + " | " + date + " | " + budgetEntry;
            currentTrackerEntriesNames.add(name);
        }

        return currentTrackerEntriesNames;
    }

    // EFFECT: returns a JScrollPane that contains a JList with given content and
    // has selectable functionality
    private JScrollPane createScrollingList(GraphicalBudgetingApp app) {

        JList<String> listComponent = new JList<String>();
        listComponent.addListSelectionListener(new SelectedBudgetListener(listComponent, app));

        JScrollPane listScroller = new JScrollPane(listComponent);
        listScroller.setPreferredSize(new Dimension(40, 500)); // TODO: remove later, for testing
        return listScroller;
    }

    // EFFECT: returns a JScrollPane that contains a JList with given content
    private JScrollPane createScrollingList() {

        JList<String> listComponent = new JList<String>();

        JScrollPane listScroller = new JScrollPane(listComponent);
        listScroller.setPreferredSize(new Dimension(40, 500)); // TODO: remove later, for testing
        return listScroller;
    }

    // REQUIRES: actionListener has open a new window functionality
    // EFFECT: returns a JButton with given buttonLabel and actionListener. This
    // button opens a new window specidfied by actionListener
    private JButton createNewItemButton(String buttonLabel, ActionListener actionListener) {
        JButton newItemWindowOpener = new JButton(buttonLabel);
        newItemWindowOpener.addActionListener(actionListener);
        return newItemWindowOpener;
    }

    // EFFECT: returns JPanel for the side menu of the app
    private JPanel createSideMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton loadButton = new JButton("Load File ");
        JButton saveButton = new JButton("Save File ");
        JButton summaryButton = new JButton("Summary");
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(summaryButton);

        return panel;
    }

    // EFFECT: initialises the app window
    private void createAppWindow() {
        appWindow = new JFrame("Budget.ly");
        appWindow.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // appWindow.setResizable(false); // TODO: decide to use resizable or not
        appWindow.setLayout(new BorderLayout());
    }

    // EFFECTS: Sets currentBudget to first budget found in budgets with the given
    // budgetName;
    public void setCurrentBudget(String budgetName) {
        for (Budget budget : budgets) {
            if (budget.getName() == budgetName) {
                currentBudget = budget;
            }
        }
    }

}
