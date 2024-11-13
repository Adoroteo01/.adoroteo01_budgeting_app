package ui;

import javax.swing.UIManager;

// import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

import ui.gui.GraphicalBudgetingApp;

public class Main {
    public static void main(String[] args) {

        // Sets the theme of Swing UI
        FlatLightLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Look and Feel Loading Error");
        }

        // new BudgetingApp(); // for console ui
        new GraphicalBudgetingApp(); // for gui
    }
}