package Model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PreparedWorkoutLoader {

    public static ArrayList<Workout> loadPreparedWorkouts(String path) {
        ArrayList<Workout> workouts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            Workout currentWorkout = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Workout:")) {
                    String[] parts = line.substring(8).split(";");
                    String name = parts[0];
                    int kcal = Integer.parseInt(parts[1]);
                    int duration = Integer.parseInt(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);
                    int volume = Integer.parseInt(parts[4]);
                    WorkoutLevel level = WorkoutLevel.valueOf(parts[5].toUpperCase());

                    currentWorkout = new Workout(name, kcal, duration, date, volume, level  );
                }

                else if (line.startsWith("Exercise:") && currentWorkout != null) {
                    String[] parts = line.substring(9).split(";");
                    String exName = parts[0];
                    int sets = Integer.parseInt(parts[1]);
                    int reps = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);

                    Exercise exercise = new Exercise(exName, sets, reps, weight);
                    currentWorkout.getExercises().add(exercise);
                }

                else if (line.equals("---") && currentWorkout != null) {
                    workouts.add(currentWorkout);
                    currentWorkout = null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return workouts;

        // TODO Predelat ten loading lepe (subString pryc)
    }
}