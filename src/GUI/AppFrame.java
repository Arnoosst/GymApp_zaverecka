package GUI;

import GUI.MealGUI.*;
import GUI.UserGUI.ChangeUserDataPanel;
import GUI.UserGUI.UserPanel;
import GUI.UserGUI.ViewMealLogsPanel;
import GUI.UserGUI.ViewWorkoutLogsPanel;
import GUI.WorkoutGUI.*;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;


/**
 * The main application window (frame) for the fitness app.
 * Manages panel navigation using CardLayout and initializes
 * all panels after a successful user login.
 *
 * @Author: Vojtěch Malínek
 */
public class AppFrame extends JFrame {
    private final CardLayout layout;
    private final JPanel cards;
    private final UserManager userManager;

    private User user;


    private ViewCustomWorkoutPanel viewCustomWorkoutPanel;
    private SelectUserWorkoutPanel selectUserWorkoutPanel;
    private DeleteCustomWorkoutPanel deleteCustomWorkoutPanel;
    private UserPanel userPanel;
    private WorkoutPanel workoutPanel;
    private ViewCustomMealPanel viewCustomMealPanel;
    private DeleteCustomMealPanel deleteCustomMealPanel;
    private CaloriesChartMenuPanel caloriesChartMenuPanel;
    private EditMealsPanel editMealsPanel;
    private ViewMealLogsPanel viewMealLogsPanel;
    private ViewWorkoutLogsPanel viewWorkoutLogsPanel;
    private AddMealFromOwnPanel addMealFromOwnPanel;
    private MealPanel mealPanel;


    /**
     * Constructs the main application frame.
     * Sets up the login and registration panels by default.
     *
     * @param userManager the manager responsible for user operations like login and registration
     */
    public AppFrame(UserManager userManager) {
        this.userManager = userManager;
        this.layout = new CardLayout();
        this.cards = new JPanel(layout);

        setTitle("Fitness App");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        cards.add(new LoginPanel(userManager, layout, cards, this), "login");
        cards.add(new RegisterPanel(userManager, layout, cards), "register");

        add(cards);
        layout.show(cards, "login");

        setVisible(true);
    }



    /**
     * Initializes all user-specific panels after successful login.
     *
     * @param user the logged-in user whose data will be used in the app
     */
    public void initializeUserPanels(User user) {
        this.user = user;

        viewCustomWorkoutPanel = new ViewCustomWorkoutPanel(user, layout, cards);
        viewWorkoutLogsPanel = new ViewWorkoutLogsPanel(user, layout,cards);
        viewMealLogsPanel = new ViewMealLogsPanel(user, layout,cards);
        deleteCustomWorkoutPanel = new DeleteCustomWorkoutPanel(user, userManager, cards, layout);
        workoutPanel = new WorkoutPanel(user, cards, layout, viewCustomWorkoutPanel, deleteCustomWorkoutPanel);

        userPanel = new UserPanel(user, userManager, layout, cards, viewMealLogsPanel, viewWorkoutLogsPanel);
        selectUserWorkoutPanel = new SelectUserWorkoutPanel(user, userManager, cards, layout, workoutPanel);

        viewCustomMealPanel = new ViewCustomMealPanel(user, layout, cards);
        deleteCustomMealPanel = new DeleteCustomMealPanel(user, userManager, layout, cards);
        editMealsPanel = new EditMealsPanel(user, userManager, layout, cards);
        mealPanel = new MealPanel(user, userManager, layout, cards, viewCustomMealPanel, deleteCustomMealPanel);
        caloriesChartMenuPanel = new CaloriesChartMenuPanel(user, userManager, layout, cards, editMealsPanel, mealPanel);
        editMealsPanel.setCaloriesChartMenuPanel(caloriesChartMenuPanel);
        addMealFromOwnPanel = new AddMealFromOwnPanel(user, userManager, layout, cards);

        cards.add(mealPanel, "meal");
        cards.add(new MainMenuPanel(layout, cards, mealPanel), "mainMenu");
        cards.add(new ViewPresetWorkoutsPanel(layout, cards), "viewPresetWorkouts");
        cards.add(viewCustomWorkoutPanel, "viewCustomWorkouts");
        cards.add(deleteCustomWorkoutPanel, "deleteWorkout");
        cards.add(selectUserWorkoutPanel, "selectUserWorkout");
        cards.add(workoutPanel, "workout");
        cards.add(new StartWorkoutPanel(user, layout, cards, selectUserWorkoutPanel), "startWorkout");
        cards.add(new SelectPreLoadWorkoutsPanel(user, userManager, cards, layout, workoutPanel), "selectPreLoadWorkout");
        cards.add(new CreateWorkoutPanel(user, userManager, cards, layout), "createWorkout");
        cards.add(viewWorkoutLogsPanel, "viewWorkoutLogs");
        cards.add(viewMealLogsPanel, "viewMealLogs");
        cards.add(userPanel, "user");
        cards.add(new ChangeUserDataPanel(user, userManager, layout, cards, userPanel), "changeUserData");

        cards.add(new ViewPresetMealPanel(layout, cards), "viewPresetMeal");
        cards.add(viewCustomMealPanel, "viewCustomMeal");

        cards.add(new ManageMealsPanel(user, userManager, layout, cards, caloriesChartMenuPanel, addMealFromOwnPanel), "manageMeals");
        cards.add(editMealsPanel, "editMeals");
        cards.add(deleteCustomMealPanel, "deleteCustomMeal");
        cards.add(new CreateCustomMealPanel(user, userManager, layout, cards), "createCustomMeal");
        cards.add(caloriesChartMenuPanel, "caloriesChartMenu");
        cards.add(new AddMealPanel(user, userManager, layout, cards), "addMeal");
        cards.add(addMealFromOwnPanel, "addMealFromOwn");
        cards.add(new AddMealFromPreLoadPanel(user, userManager, layout, cards), "addMealFromPreLoad");
    }
}
