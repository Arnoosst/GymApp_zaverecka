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
    private ArrayList<Exercise> exercises;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(name, workout.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
