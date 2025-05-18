import GUI.AppFrame;
import Model.User;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        new AppFrame(userManager);
    }

        //TODO Custom meal nefunguje

        //TODO vyresit nejak te inputpanel pro exe, tajhle to nefunguje asi musi byt pouzit dialog nebo nevim

        //TODO nelibi se mi ty dialogy predelat dat to na jframe a bude to vyresene


        //TODO Predelat exeinputdialog back na jframe
        //TODO Zkontrolovat cely mealGUI == jeste jsme nedelal nic na upravu

}