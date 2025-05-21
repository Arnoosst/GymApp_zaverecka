package Model;

import java.io.Serializable;


/**
 * Represents a single set of an exercise, including the number of repetitions
 * and the weight used.
 * This class is serializable and is used in workout tracking or planning applications.
 *
 * @author Vojtěch Malínek
 */
public class ExerciseSets implements Serializable {
    private int reps;
    private double weight;

    public ExerciseSets(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public ExerciseSets() {
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
