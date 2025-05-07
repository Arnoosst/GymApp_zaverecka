package Model;

import java.util.ArrayList;

public class Exercise {
    private String name;
    private ArrayList<ExerciseSets> sets;
    private int reps;
    private double weight;

    public Exercise(String name, int reps, double weight) {
        this.name = name;
        this.sets = new ArrayList<>();
        this.reps = reps;
        this.weight = weight;
    }
    public Exercise() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ExerciseSets> getSets() {
        return sets;
    }

    public void setSets(ArrayList<ExerciseSets> sets) {
        this.sets = sets;
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
    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", weight=" + weight +
                '}';
    }

}
