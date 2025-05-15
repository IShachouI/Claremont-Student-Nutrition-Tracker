/**
 * Represents a single menu item from a dining hall with nutrition information.
 * 
 * Each MenuItem contains:
 * - The dish name
 * - The station where it is served
 * - The serving size
 * - Nutrition facts: calories, fat, carbs, and protein
 * 
 * Example usage:
 * <pre>
 * MenuItem item = new MenuItem("Scrambled Eggs", "Grill Station", "85g", 117.65, 7.55, 0, 10.63);
 * System.out.println(item);
 * </pre>
 * 
 * @author Yaseen Osman
 */
public class MenuItem {
    //Name of the dish, e.g. "Scrambled Eggs" 
    public String dish;
    //Station where dish is served, e.g. "Grill Station" 
    public String station;
    //Serving size, e.g. "85g" 
    public String servingSize;
    //Calories per serving 
    public double calories;
    //Fat (grams) per serving 
    public double fat;
    //Carbohydrates (grams) per serving 
    public double carbs;
    //Protein (grams) per serving 
    public double protein;

    /**
     * Constructs a MenuItem with the specified properties.
     * 
     * @param dish        Name of the dish
     * @param station     Station where dish is served
     * @param servingSize Serving size description
     * @param calories    Calories per serving
     * @param fat         Fat grams per serving
     * @param carbs       Carbohydrate grams per serving
     * @param protein     Protein grams per serving
     */
    public MenuItem(String dish, String station, String servingSize,
                    double calories, double fat, double carbs, double protein) {
        this.dish = dish;
        this.station = station;
        this.servingSize = servingSize;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    /**
     * Returns a human-readable String representation of this menu item,
     * including name, serving size, and nutrition facts.
     * 
     * @return A formatted string describing the menu item and its nutrition.
     */
    @Override
    public String toString() {
        return String.format("%s (%s): %.1f kcal, %.1fg fat, %.1fg carbs, %.1fg protein",
                dish, servingSize, calories, fat, carbs, protein);
    }
}
