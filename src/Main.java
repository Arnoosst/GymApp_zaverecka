import GUI.RegisterFrame;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        RegisterFrame registerFrame = new RegisterFrame(userManager);
        registerFrame.setVisible(true);
    }
}