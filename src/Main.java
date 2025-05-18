import GUI.AppFrame;
import Model.User;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        new AppFrame(userManager);
    }

        //TODO Neukladaj se nejake jidla do mellogu


        //TODO Predelat exeinputdialog back na jframe


}