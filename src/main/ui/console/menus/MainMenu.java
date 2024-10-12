package ui.console.menus;

public class MainMenu extends Window {

    // EFFECTS: creates new main menu with title and needed options
    public MainMenu() {
        super();
        title = "MAIN MENU";
        options = "1) Open Budget\n2) New Buget\n3) Exit";
    }

    @Override
    // EFFECTS: prints main menu
    public void open() {
        newPage();
        printTitle();
        drawDivider();
        printOptions();
    }

    @Override
    // EFFECTS: nothing
    public void set(Object object) {

    }

}
