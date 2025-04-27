package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add meal":
                System.out.println("Add meal");
                break;
            case "Add workout":
                System.out.println("Add workout");
                break;
            case "User profile":
                System.out.println("User profile");
                break;
            case "End app":
                System.exit(0);
                break;
            default:
                System.out.println("error unknown command");
        }
    }
}
