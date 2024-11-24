package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
import ui.gui.listeners.CreateNewBudgetEntryListener;
import ui.gui.listeners.CreateNewBudgetListener;
import ui.gui.listeners.CreateNewTrackerEntryListener;

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

        // TODO: implement budget entry adding
        // TODO: implement tracker entry adding

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JButton newBudgetWindowOpener = createNewItemButton("New Budget", new CreateNewBudgetListener(budgets));
        JButton newBudgetEntryWindowOpener = createNewItemButton("New Budget Entry",
                new CreateNewBudgetEntryListener());
        JButton newTrackerEntryWindowOpener = createNewItemButton("New Tracker Entry",
                new CreateNewTrackerEntryListener());

        List<String> budgetsSampleData = new ArrayList<String>();
        budgetsSampleData.add("Jan");
        budgetsSampleData.add("Feb");
        budgetsSampleData.add("Mar");

        List<String> budgetEntriesSampleData = new ArrayList<String>();
        budgetEntriesSampleData.add("Expense 1");
        budgetEntriesSampleData.add("Expense 2");
        budgetEntriesSampleData.add("Expense 3");

        List<String> trackerSampleData = new ArrayList<String>();
        trackerSampleData.add("Jan 1 - $20");
        trackerSampleData.add("Jan 2 - $30");
        trackerSampleData.add("jan 2 - $4");

        JScrollPane budgetsScroller = createScrollingList(budgetsSampleData);
        JScrollPane budgetEntriesScroller = createScrollingList(budgetEntriesSampleData);
        JScrollPane trackerScroller = createScrollingList(budgetEntriesSampleData);

        panel.add(new JScrollPane(budgetsScroller));
        panel.add(newBudgetWindowOpener);
        panel.add(new JScrollPane(budgetEntriesScroller));
        panel.add(newBudgetEntryWindowOpener);
        panel.add(new JScrollPane(trackerScroller));
        panel.add(newTrackerEntryWindowOpener);

        return panel;
    }

    // REQUIRES: scrollingList contains EXACTLY a single JList<String>
    // EFFECTS: updates the JList<String> inside given scrollingList with the given
    // updatedList
    private void updateScrollingList(JScrollPane scrollingList, List<String> updatedList) {

        JList<String> list = (JList<String>) scrollingList.getViewport().getView();

        String[] newlistData = updatedList.toArray(new String[0]);
        list.setListData(newlistData);
    }

    // EFFECT: returns a JScrollPane that contains a JList with given content
    private JScrollPane createScrollingList(List<String> lsitContent) {

        String[] listContentArray = lsitContent.toArray(new String[0]);
        JList<String> listComponent = new JList<String>(listContentArray);

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
