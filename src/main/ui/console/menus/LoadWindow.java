package ui.console.menus;

import java.util.ArrayList;

public class LoadWindow extends Window{

    // EFFECT: Creates new LoadWindow
    public LoadWindow() {
        super();
        inputs = new ArrayList<String>();
        inputs.add("Enter Path to Save File");
    }

    @Override
    // EFFECT: No effect
    public void set(Object object) {
        // no action
    }

    @Override
    // EFFECT: Opens LoadWindow page
    public void open() {
        newPage();
    }
    
}
