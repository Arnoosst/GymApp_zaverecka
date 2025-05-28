package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Represents a workout with name, date, duration, level, and exercises.
 *
 * @author Vojtěch Malínek
 */
public class Workout implements Serializable {
    private String name;
    private int duration;
    private LocalDate date;
    private WorkoutLevel workoutLevel;
    private ArrayList<Exercise> exercises;


    /**
     * Creates a workout with given name, duration, date and level.
     *
     * @param name         Name of the workout
     * @param duration     Duration in minutes
     * @param date         Date of the workout
     * @param workoutLevel Difficulty level of the workout
     */
    public Workout(String name, int duration, LocalDate date, WorkoutLevel workoutLevel) {
        this.name = name;
        this.duration = duration;
        this.date = date;
        this.workoutLevel = workoutLevel;
        this.exercises = new ArrayList<>();
    }

    public Workout() {
        this.exercises = new ArrayList<>();
    }


    /**
     * Adds an exercise to the workout.
     *
     * @param exercise The exercise to add
     * @return true if added successfully, false if exercise is null
     */
    public boolean addExercise(Exercise exercise){
        if(exercise==null){
            return false;
        }
        if (exercises == null) {
            exercises = new ArrayList<>();
        }
        exercises.add(exercise);
        return true;
    }


    /**
     * Calculates the total volume lifted in the workout.
     * Volume = sum of (reps * weight) for all sets.
     *
     * @return Total volume lifted in kilograms
     */
    public double calculateTotalVolumeLifted() {
        double totalVolume = 0;
        for (Exercise exercise : exercises) {
            for (ExerciseSets set : exercise.getSets()) {
                totalVolume += set.getReps() * set.getWeight();
            }
        }
        return totalVolume;
    }


    /**
     * Estimates calories burned during the workout.
     * Uses formula: 0.1 * reps * weight for each set.
     *
     * @return Estimated calories burned
     */

    public int calculateKcalBurned() {
        int kcal = 0;
        for (Exercise exercise : exercises) {
            for (ExerciseSets set : exercise.getSets()) {
                kcal += (int)(set.getReps() * set.getWeight());
            }
        }
        return kcal;
    }


    /**
     * Estimates the duration of the workout based on all reps.
     * Adds 2 seconds per rep.
     *
     * @return Estimated time in seconds
     */
    public int calculateTimeDuration() {
        int totalDuration = 0;
        for (Exercise exercise : exercises) {
            for (ExerciseSets set : exercise.getSets()) {
                totalDuration += set.getReps() * 2;
            }
        }
        return totalDuration;
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
