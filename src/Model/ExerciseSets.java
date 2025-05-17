package Model;

import java.io.Serializable;

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
