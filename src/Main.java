import GUI.LoginFrame;
import GUI.RegisterFrame;
import Model.User;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        User user = new User();
        LoginFrame loginFrame = new LoginFrame(userManager, user);
        loginFrame.setVisible(true);
    }
}