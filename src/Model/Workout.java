package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Workout {
    private String name;
    private int duration;
    private LocalDate date;
    private WorkoutLevel workoutLevel;
    private ArrayList<Exercise> exercises;

    public Workout(String name, int duration, LocalDate date, WorkoutLevel workoutLevel) {
        this.name = name;
        this.duration = duration;
        this.date = date;
        this.workoutLevel = workoutLevel;
        this.exercises = new ArrayList<>();
    }

    public Workout() {
        this.exercises = new ArrayList<>();  // Add this line
    }

    public boolean addExercise(Exercise exercise){
        if(exercise==null){
            return false;
        }
        if (exercises == null) {  // Add this check
            exercises = new ArrayList<>();
        }
        exercises.add(exercise);
        return true;
    }

    public double calculateTotalVolumeLifted() {
        double totalVolume = 0;
        for (Exercise exercise : exercises) {
            for (ExerciseSets set : exercise.getSets()) {
                totalVolume += set.getReps() * set.getWeight();
            }
        }
        return totalVolume;
    }

    public int calculateKcalBurned() {
        int kcal = 0;
        for (Exercise exercise : exercises) {
            for (ExerciseSets set : exercise.getSets()) {
                kcal += (int)(0.1 * set.getReps() * set.getWeight());
            }
        }
        return kcal;
    }




    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public WorkoutLevel getWorkoutLevel() {
        return workoutLevel;
    }

    public void setWorkoutLevel(WorkoutLevel workoutLevel) {
        this.workoutLevel = workoutLevel;
    }
}
