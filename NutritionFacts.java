/**
 * NutritionFacts stores nutrition information for a food item or a daily total.
 * 
 * This class is used to represent the calories, protein, carbohydrates, and fat content
 * of a single menu item or to accumulate totals for a user's daily intake.
 * 
 * Example usage:
 * <pre>
 * NutritionFacts egg = new NutritionFacts(117, 11, 1, 7);
 * NutritionFacts toast = new NutritionFacts(80, 2, 15, 1);
 * egg.add(toast); // Now egg contains the sum of both items' nutrition.
 * </pre>
 * 
 * @author Yaseen Osman
 */
public class NutritionFacts {
    //Calories per serving or total calories 
    public int calories;
    //Protein grams per serving or total protein
    public int protein;
    //Carbohydrate grams per serving or total carbs 
    public int carbs;
    //Fat grams per serving or total fat 
    public int fat;

    /**
     * Constructs a NutritionFacts object with the specified values.
     * 
     * @param calories Calories per serving or total calories
     * @param protein  Protein grams per serving or total protein
     * @param carbs    Carbohydrate grams per serving or total carbs
     * @param fat      Fat grams per serving or total fat
     */
    public NutritionFacts(int calories, int protein, int carbs, int fat) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    /**
     * Adds the nutrition values from another NutritionFacts object to this one.
     * Useful for accumulating daily totals.
     * 
     * @param other The other NutritionFacts object whose values will be added.
     */
    public void add(NutritionFacts other) {
        this.calories += other.calories;
        this.protein += other.protein;
        this.carbs += other.carbs;
        this.fat += other.fat;
    }

    /**
     * Returns a human-readable string representation of the nutrition facts.
     * 
     * @return A formatted string showing calories, protein, carbs, and fat.
     */
    @Override
    public String toString() {
        return String.format("Calories: %d, Protein: %dg, Carbs: %dg, Fat: %dg", 
                             calories, protein, carbs, fat);
    }
}
