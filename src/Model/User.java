package Model;

import java.util.ArrayList;

public class User {
    private String userName;
    private int age;
    private int height;
    private Gender gender;
    private ArrayList<Meal> mealLogs;
    private ArrayList<Workout> workoutLogs = new ArrayList<>();

    public User(String userName, int age, int height, Gender gender) {
        this.userName = userName;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.mealLogs = new ArrayList<>();
        this.workoutLogs = new ArrayList<>();
    }

    public User() {
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
