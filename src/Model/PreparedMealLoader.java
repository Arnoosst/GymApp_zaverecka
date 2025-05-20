package Model;

import java.io.*;
import java.util.ArrayList;

public class PreparedMealLoader {
    public static ArrayList<Meal> loadMealsFromFile(String filePath) {
        ArrayList<Meal> meals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                String name = parts[0].trim();
                int kcal = Integer.parseInt(parts[1].trim());
                int protein = Integer.parseInt(parts[2].trim());
                int fat = Integer.parseInt(parts[3].trim());
                int carbs = Integer.parseInt(parts[4].trim());

                Meal meal = new Meal(name, kcal, protein, fat, carbs);
                meals.add(meal);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error loading meals");
        } catch (IOException e) {
            System.out.println("Error loading meals");
        }

        return meals;
    }
}
