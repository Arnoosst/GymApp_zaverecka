import GUI.AppFrame;
import Model.User;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        AppFrame appFrame = new AppFrame(new UserManager(), new User());
    }
}