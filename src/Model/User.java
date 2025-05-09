package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class User implements Serializable {
    private String userName;
    private int age;
    private String name;
    private int height;
    private double weight;
    private Gender gender;
    private String password;
    private HashSet<Meal> customMeals = new HashSet<>();
    private HashSet<Workout> customWorkouts = new HashSet<>();
    private HashMap<LocalDate, ArrayList<Meal>> mealLogs;
    private ArrayList<Workout> workoutLogs = new ArrayList<>();
    private ArrayList<Meal> mealsToday;

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
        this.mealsToday = new ArrayList<>();
    }

    public User() {
    }




    public boolean addWorkoutToLog(Workout workout) {
        workoutLogs.add(workout);
        return true;
    }

    public boolean addCustomMeal(Meal meal) {
        customMeals.add(meal);
        return true;
    }

    public boolean addCustomWorkout(Workout workout) {
        customWorkouts.add(workout);
        return true;
    }

    public double calculateBMR(User user) {
        double bmr = 0;
        if(user.getGender() == Gender.MALE){
            bmr=  user.getWeight() * 10 + user.getHeight() * 6.25 - user.getAge() * 5 + 5;
        }else {
            bmr=  user.getWeight() * 10 + user.getHeight() * 6.25 - user.getAge() * 5 - 161;
        }
        return bmr;
    }

    public ArrayList<Workout> get7daysOldWorkout() {
        ArrayList<Workout> temp = new ArrayList<>();
        int a = workoutLogs.size();
        if(workoutLogs.size() < 7){
            a = 0;
        }
        for(int i = a; i < workoutLogs.size(); i++){
            temp.add(workoutLogs.get(i));
        }
        return temp;
    }

    public int getTotalCaloriesBurned() {
        int total = 0;
        for (Workout w : workoutLogs) {
            total += w.calculateKcalBurned();
        }
        return total;
    }

    public double getTotalVolumeLifted() {
        double total = 0;
        for (Workout w : workoutLogs) {
            total += w.calculateTotalVolumeLifted();
        }
        return total;
    }

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
    public boolean addMeal(Meal meal) {
        if (meal == null) {
            return false;
        }
        mealsToday.add(meal);
        return true;
    }
    public ArrayList<Meal> getMealsToday() {
        return mealsToday;
    }

    public void setMealsToday(ArrayList<Meal> mealsToday) {
        this.mealsToday = mealsToday;
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
