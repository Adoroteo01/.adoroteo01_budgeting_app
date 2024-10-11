package ui.console.menus;

public class MainMenu extends Window {

    public MainMenu() {
        super();
        title = "MAIN MENU";
        options = "1) Open Budget\n2) New Buget\n3) Exit";
    }

    @Override
    public void open() {
        newPage();
        printTitle();
        drawDivider();
        printOptions();
    }

    @Override
    public void set(Object object) {

    }

}
