package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
        JPanel content = createContentPanel();

        appWindow.add(sideMenu, BorderLayout.WEST);
        appWindow.add(content, BorderLayout.CENTER);

        appWindow.setVisible(true);
    }

    // EFFECT: returns Jpanel for app content with padding on the left
    private JPanel createContentPanel() {

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel padding = createPadding(0, 55, 0, 0);

        // Main Content
        JPanel mainContent = createMainContentPanel();

        // Adding compenents
        content.add(padding, BorderLayout.WEST);
        content.add(mainContent, BorderLayout.CENTER);
        return content;
    }

    // EFFECT: returns empty JPanel that has top space at the top, left space on the
    // left, bottom space at the bottom, and right space on the right
    private JPanel createPadding(int top, int left, int bottom, int right) {
        // Padding
        JPanel padding = new JPanel();
        padding.setBorder(new EmptyBorder(top, left, bottom, right));
        padding.setBackground(Color.RED); // TODO: Delete later. For testing
        return padding;
    }

    // EFFECT: returns JPanel for the main content of the app such as adding
    // budgets, budget entries, tracker entries, etc.
    private JPanel createMainContentPanel() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());

        // Top Logo bar
        JPanel titleBar = new JPanel();
        titleBar.setLayout(new BoxLayout(titleBar, BoxLayout.Y_AXIS));

        JLabel titleBarLabel = new JLabel("Budget.ly");
        titleBarLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titleBarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleBar.add(titleBarLabel);

        // Test content
        JButton testButton = new JButton("TEST BUTTON!");

        mainContent.add(titleBar, BorderLayout.NORTH);
        mainContent.add(testButton, BorderLayout.CENTER);

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
