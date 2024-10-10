package ui.console.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// a window in a budgetting app with a title, selectable options, inputs
public abstract class Window {

    private String DIVIDER = "-";
    private int DIVDER_LENGTH = 48;

    private String NEW_LINE = "\n";
    private int NEW_PAGE_SPACER_LENGTH = 8;


    protected Scanner scanner;
    protected String title;
    protected String options;

    protected List<String> inputs;


    public Window() {
        scanner = new Scanner(System.in);
    }

    protected void drawDivider() {
        final String divider;
        divider = DIVIDER.repeat(DIVDER_LENGTH);
        System.out.println(divider+"\n");
    }

    abstract public void open();

    protected void printTitle() {
        System.out.println(title);
    }

    protected void printOptions() {
        System.out.println(options+"\n\n");
    }

    public String getUserInput() {

        return scanner.nextLine();
    }

    public List<String> getAllInputs() {
        List<String> returnedInputs;
        returnedInputs = new ArrayList<String>();
        for (String desiredInput : inputs) {
            System.out.print(desiredInput+": ");
            returnedInputs.add(scanner.nextLine());
        }

        return returnedInputs;
    }

    protected void newPage() {
        final String newPage;
        newPage = NEW_LINE.repeat(NEW_PAGE_SPACER_LENGTH);
        System.out.println(newPage);
    }

    

    
}
