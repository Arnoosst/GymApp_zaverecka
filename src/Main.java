import GUI.AppFrame;
import Model.User;
import Model.UserManager;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        new AppFrame(userManager);

        //TODO v app frame a v modelu ve 2 tridach, v wokroutinfoframe, projit workout execution (toz uz je v planu)
        //TODO projit vlaste k tomu to sedi selectworkoutpanel
        //TODO jinak jeste ty unitTesty
        //TODO predelani executionWorkout

        //TODO predelani userpanelu refreshu take zkusit to
    }
}