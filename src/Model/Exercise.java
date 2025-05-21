
package Model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Represents an exercise that contains a name and an array of exercise sets.
 * Used for building workout routines with defined sets, repetitions, and weights.
 * Each exercise can have multiple sets initialized with default or custom values.
 * Implements Serializable for file storage or data persistence.
 *
 * @Author: Vojtěch Malínek
 */
public class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ExerciseSets[] sets;

    public Exercise(String name) {
        this.name = name;
        this.sets = new ExerciseSets[0];
    }

    public Exercise() {
        this.sets = new ExerciseSets[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExerciseSets[] getSets2() {
        return sets;
    }

    public ExerciseSets[] getSets() {
        if (sets == null) {
            sets = new ExerciseSets[0];
        }
        return sets;
    }

    public void setSets(ExerciseSets[] sets) {
        this.sets = sets;
    }


    /**
     * Initializes the sets array with the given number of default sets.
     * Each set starts with 1 rep and 1.0 kg.
     *
     * @param count the number of sets to initialize
     */
    public void initializeSets(int count) {
        sets = new ExerciseSets[count];
        for (int i = 0; i < count; i++) {
            sets[i] = new ExerciseSets(1,1);
        }
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", sets=" + java.util.Arrays.toString(sets) +
                '}';
    }


}