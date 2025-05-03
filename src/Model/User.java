package Model;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<Meal> mealLogs;
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
        this.mealLogs = new ArrayList<>();
        this.workoutLogs = new ArrayList<>();
    }

    public User() {
    }


    public boolean addMealToLog(Meal meal) {
        mealLogs.add(meal);
        return true;
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
    public ArrayList<Meal> get7daysOldMeal() {
        ArrayList<Meal> temp = new ArrayList<>();
        int a = workoutLogs.size();
        if(workoutLogs.size() < 7){
            a = 0;
        }
        for(int i = a ; i < mealLogs.size(); i++){
            temp.add(mealLogs.get(i));
        }
        return temp;
    }
    public int getKcalBurned() {
        int totalCaloriesBurned = 0;
        for(Workout workout : workoutLogs){
            totalCaloriesBurned += workout.getKcalBurned();
        }
        return totalCaloriesBurned;
    }
    public int getTotalVolumeLiftedFromLogWorkout(){
        int totalVolumeLifted = 0;
        for(Workout workout : workoutLogs){
            totalVolumeLifted += workout.getTotalVolumeLifted();
        }
        return totalVolumeLifted;
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
    public ArrayList<Meal> getMealLogs() {
        return mealLogs;
    }
    public void setMealLogs(ArrayList<Meal> mealLogs) {
        this.mealLogs = mealLogs;
    }
    public ArrayList<Workout> getWorkoutLogs() {
        return workoutLogs;
    }
    public void setWorkoutLogs(ArrayList<Workout> workoutLogs) {
        this.workoutLogs = workoutLogs;
    }
    public String getPassword() { return password; }

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
