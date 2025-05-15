import java.util.*;

/**
 * User stores user profile, nutrition log, and friends.
 */
public class User {
    public String studentId;
    public String name;
    public Map<String, Integer> goals; // calories, protein, carbs, fat
    public Map<String, NutritionFacts> nutritionLog; // date -> NutritionFacts
    public Set<String> friends;

    public User(String studentId, String name, Map<String, Integer> goals) {
        this.studentId = studentId;
        this.name = name;
        this.goals = goals;
        this.nutritionLog = new HashMap<>();
        this.friends = new HashSet<>();
    }

    public void logMeal(String date, NutritionFacts nf) {
        nutritionLog.putIfAbsent(date, new NutritionFacts(0, 0, 0, 0));
        nutritionLog.get(date).add(nf);
    }

    public NutritionFacts getDailyNutrition(String date) {
        return nutritionLog.getOrDefault(date, new NutritionFacts(0, 0, 0, 0));
    }

    public String shareNutrition(String date) {
        NutritionFacts nf = getDailyNutrition(date);
        return String.format("%s's nutrition on %s: %s", name, date, nf);
    }
}
