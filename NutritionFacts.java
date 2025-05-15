
    /**
 * NutritionFacts stores nutrition info for a food item.
 */
public class NutritionFacts {
    public int calories;
    public int protein;
    public int carbs;
    public int fat;

    public NutritionFacts(int calories, int protein, int carbs, int fat) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public void add(NutritionFacts other) {
        this.calories += other.calories;
        this.protein += other.protein;
        this.carbs += other.carbs;
        this.fat += other.fat;
    }

    @Override
    public String toString() {
        return String.format("Calories: %d, Protein: %dg, Carbs: %dg, Fat: %dg", calories, protein, carbs, fat);
    }
}


