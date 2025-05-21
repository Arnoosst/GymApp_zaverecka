package Model;

import java.io.*;
import java.util.ArrayList;


/**
 * The PreparedMealLoader class provides functionality for loading meals
 * from a file into a list of Meal objects.
 *
 * The file should contain one meal per line, with the following format:
 *
 * MealName;kcal;protein;fat;carbs
 *
 * For example:
 * Chicken Breast;250;40;5;0
 *
 * @author Vojtěch Malínek
 */
public class PreparedMealLoader {

    /**
     * Loads meals from the specified file and returns them as a list.
     *
     * Each line in the file is expected to contain five values separated by this = ;
     * the name of the meal, its calories, protein content, fat content, and carbohydrate content.
     *
     *
     * @param filePath the path to the file containing meal definitions
     * @return a list of Meal objects parsed from the file; returns an empty list if file cannot be read
     */
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
