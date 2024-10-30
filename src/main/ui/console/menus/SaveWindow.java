package ui.console.menus;

import java.util.ArrayList;

public class SaveWindow extends Window {

    // EFFECTS: creates new save window with needed inputs
    public SaveWindow() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Name Your Save File");
        inputs.add("Enter Save Path");
    }

    @Override
    // EFFECT: no effect
    public void set(Object object) {
        // no action
    }

    // EFFECT: draws new page
    @Override
    public void open() {
        newPage();
    }
    
}
