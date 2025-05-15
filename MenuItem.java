public class MenuItem {
    public String dish;
    public String station;
    public String servingSize;
    public double calories;
    public double fat;
    public double carbs;
    public double protein;

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

    @Override
    public String toString() {
        return String.format("%s (%s): %.1f kcal, %.1fg fat, %.1fg carbs, %.1fg protein",
                dish, servingSize, calories, fat, carbs, protein);
    }
}
