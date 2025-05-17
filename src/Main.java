import GUI.AppFrame;
import Model.User;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        new AppFrame(userManager);
    }

        //TODO Custom meal nefunguje



        //TODO Zkotrolvat vsechny vychozi moznosti u startWorkout dialog
        //TODO nelibi se mi ty dialogy predelat dat to na jframe a bude to vyresene


        //TODO aktualizovat workoutPanel a userPanel tim se resi to ukazovanu null

}