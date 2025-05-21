package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Represents a user of the fitness and meal tracking system.
 * Stores personal data, custom meals/workouts, logs and preferences.
 * Implements Serializable for data persistence.
 *
 * Author: Vojtěch
 */
public class User implements Serializable {
    private String userName;
    private int age;
    private String name;
    private int height;
    private double weight;
    private Gender gender;
    private String password;
    private double caloriesGoal;
    private HashSet<Meal> customMeals = new HashSet<>();
    private HashSet<Workout> customWorkouts = new HashSet<>();
    private HashMap<LocalDate, ArrayList<Meal>> mealLogs;
    private ArrayList<Workout> workoutLogs = new ArrayList<>();

    public User(String userName, int age, String name, int height, int weight, Gender gender, String password) {
        this.userName = userName;
        this.age = age;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.password = password;
        this.customMeals = new HashSet<>();
        this.customWorkouts = new HashSet<>();
        this.mealLogs = new HashMap<>();
        this.workoutLogs = new ArrayList<>();
    }

    public User() {
    }




    /**
     * Adds a meal to today's meal log.
     * @param meal the meal to add
     * @return true if successful
     */

    public boolean addMealToLog(Meal meal) {
        if (meal == null) return false;

        LocalDate today = LocalDate.now();
        mealLogs.computeIfAbsent(today, k -> new ArrayList<>()).add(meal);
        return true;
    }



    /**
     * Adds a workout to the workout log.
     * @param workout the workout to add
     * @return true if successful
     */
    public boolean addWorkoutToLog(Workout workout) {
        workoutLogs.add(workout);
        return true;
    }

    /**
     * Adds a custom meal.
     * @param meal the meal to add
     * @return true if added
     */
    public boolean addCustomMeal(Meal meal) {
        customMeals.add(meal);
        return true;
    }


    /**
     * Adds a custom workout.
     * @param workout the workout to add
     * @return true if added
     */
    public boolean addCustomWorkout(Workout workout) {
        customWorkouts.add(workout);
        return true;
    }

    /**
     * Calculates BMR using Mifflin-St Jeor Equation.
     * @return calculated BMR
     */
    public double calculateBMR(User user) {
        double bmr = 0;
        if(user.getGender() == Gender.MALE){
            bmr=  user.getWeight() * 10 + user.getHeight() * 6.25 - user.getAge() * 5 + 5;
        }else {
            bmr=  user.getWeight() * 10 + user.getHeight() * 6.25 - user.getAge() * 5 - 161;
        }
        return bmr;
    }



    /**
     * Calculates total calories burned from all workouts.
     * @return total burned kcal
     */
    public int getTotalCaloriesBurned() {
        int total = 0;
        for (Workout w : workoutLogs) {
            total += w.calculateKcalBurned();
        }
        return total;
    }


    /**
     * Calculates total volume lifted across all workouts.
     * @return total kg lifted
     */
    public double getTotalVolumeLifted() {
        double total = 0;
        for (Workout w : workoutLogs) {
            total += w.calculateTotalVolumeLifted();
        }
        return total;
    }


    /**
     * Calculates total workout time in hours.
     * @return total hours
     */
    public double getTotalWorkoutHours() {
        double total = 0;
        for (Workout w : workoutLogs) {
            total += w.getDuration();
        }
        return total/60.0;
    }


    public boolean removeCustomMeal(Meal meal) {
        return customMeals.remove(meal);
    }
    public boolean removeCustomWorkoout(Workout workout){
        return customWorkouts.remove(workout);
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public HashSet<Meal> getCustomMeals() {
        return customMeals;
    }

    public void setCustomMeals(HashSet<Meal> customMeals) {
        this.customMeals = customMeals;
    }

    public HashSet<Workout> getCustomWorkouts() {
        return customWorkouts;
    }

    public void setCustomWorkouts(HashSet<Workout> customWorkouts) {
        this.customWorkouts = customWorkouts;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public ArrayList<Workout> getWorkoutLogs() {
        return workoutLogs;
    }
    public void setWorkoutLogs(ArrayList<Workout> workoutLogs) {
        this.workoutLogs = workoutLogs;
    }
    public String getPassword() { return password; }
    public ArrayList<Meal> getMealsToday() {
        return mealLogs.getOrDefault(LocalDate.now(), new ArrayList<>());
    }

    public double getCaloriesGoal() {
        return caloriesGoal;
    }

    public void setCaloriesGoal(double caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }

    public HashMap<LocalDate, ArrayList<Meal>> getMealLogs() {
        return mealLogs;
    }

    public void setMealLogs(HashMap<LocalDate, ArrayList<Meal>> mealLogs) {
        this.mealLogs = mealLogs;
    }

    public void setPassword(String password) { this.password = password; }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", gender=" + gender +
                '}';
    }
}
