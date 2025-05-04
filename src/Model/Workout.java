package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Workout {
    private String name;
    private int kcalBurned;
    private int duration;
    private LocalDate date;
    private int totalVolumeLifted;
    private WorkoutLevel workoutLevel;
    private ArrayList<Exercise> exercises;

    public Workout(String name, int kcalBurned, int duration, LocalDate date, int totalVolumeLifted, WorkoutLevel workoutLevel) {
        this.name = name;
        this.kcalBurned = kcalBurned;
        this.duration = duration;
        this.date = date;
        this.totalVolumeLifted = totalVolumeLifted;
        this.workoutLevel = workoutLevel;
        this.exercises = new ArrayList<>();
    }



    public Workout() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(name, workout.name);
    }

    public boolean addExercise(Exercise exercise){
        if(exercise==null){
            return false;
        }
        exercises.add(exercise);
        return true;
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

    public int getKcalBurned() {
        return kcalBurned;
    }

    public void setKcalBurned(int kcalBurned) {
        this.kcalBurned = kcalBurned;
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

    public int getTotalVolumeLifted() {
        return totalVolumeLifted;
    }

    public void setTotalVolumeLifted(int totalVolumeLifted) {
        this.totalVolumeLifted = totalVolumeLifted;
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
