package Model;

import java.io.Serializable;
import java.util.Objects;


/**
 * Represents a meal with its nutritional values including calories,
 * protein, fat, and carbohydrates.
 *
 * This class is serializable and is typically used for tracking or managing
 * meals in a calorie tracking or fitness application.
 *
 *
 * @author Vojtěch Malínek
 */
public class Meal implements Serializable {
    private String name;
    private int kcal;
    private int protein, fat, carbs;

    public Meal(String name, int kcal, int protein, int fat, int carbs) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public Meal() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Meal: " + name + "\n" +
                "Kcal: " + kcal + " kcal\n" +
                "Protein: " + protein + " g\n" +
                "Fat: " + fat + " g\n" +
                "Carbs: " + carbs + " g\n";
    }

}
