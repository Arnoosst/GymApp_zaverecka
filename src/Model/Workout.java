package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Workout implements Serializable {
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

    @Override
    public String toString() {
        String result = "";
        result += "Workout Detail\n\n";
        result += "Name: " + name + "\n";
        result += "Date: " + (date != null ? date.toString() : "N/A") + "\n";
        result += "Duration: " + getDuration() + " min\n";
        result += "Calories Burned: " + calculateKcalBurned() + " kcal\n";
        result += "Total Volume Lifted: " + calculateTotalVolumeLifted() + " kg\n";
        result += "Level: " + (workoutLevel != null ? workoutLevel.toString() : "N/A") + "\n";
        result += "\nExercises:\n";

        if (exercises != null && !exercises.isEmpty()) {
            for (Exercise e : exercises) {
                result += "• " + e.getName() + "\n";
                ExerciseSets[] sets = e.getSets();
                if (sets != null && sets.length > 0) {
                    for (int i = 0; i < sets.length; i++) {
                        result += "   Set " + (i + 1) + ": " + sets[i].getReps() + " reps @ " + sets[i].getWeight() + " kg\n";
                    }
                } else {
                    result += "   No sets recorded.\n";
                }
                result += "\n";
            }
        } else {
            result += "No exercises recorded.\n";
        }

        return result;
    }
}
