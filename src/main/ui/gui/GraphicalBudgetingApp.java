package ui.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalBudgetingApp {

    private static final int SCREEN_HEIGHT = 512;
    private static final int SCREEN_WIDTH = 720;
    private JFrame appWindow;

    // Creates new Budgeting app
    public GraphicalBudgetingApp() {
        run();
    }

    // EFFECTS: Runs Budgeting app gui
    private void run() {

        createAppWindow();

        JPanel sideMenu = createSideMenu();

        JPanel mainContent = createMainContentPanel();

        appWindow.add(sideMenu, BorderLayout.WEST);
        appWindow.add(mainContent, BorderLayout.EAST);

        appWindow.setVisible(true);
    }

    // EFFECT: returns Jpanel for Main functionality of the app
    private JPanel createMainContentPanel() {
        JPanel mainContent = new JPanel();
        return mainContent;
    }

    // EFFECT: returns JPanel for the side menu of the app
    private JPanel createSideMenu() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        JButton loadButton = new JButton("Load File");
        JButton saveButton = new JButton("Save File");
        sideMenu.add(loadButton);
        sideMenu.add(saveButton);
        return sideMenu;
    }

    // EFFECT: initialises the app window
    private void createAppWindow() {
        appWindow = new JFrame("Budget.ly");
        appWindow.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        appWindow.setResizable(false);
        appWindow.setLayout(new BorderLayout());
    }
}
