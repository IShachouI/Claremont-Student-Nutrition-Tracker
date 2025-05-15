import java.util.*;

/**
 * Represents a user profile in the Student Nutrition Tracker.
 * 
 * Each User object stores:
 * - The user's student ID and name
 * - Their nutrition goals (calories, protein, carbs, fat)
 * - A daily log of nutrition facts (date → NutritionFacts)
 * - A set of friends (by student ID) for social sharing
 * 
 * Example usage:
 * <pre>
 * Map<String, Integer> goals = new HashMap<>();
 * goals.put("calories", 2000);
 * goals.put("protein", 100);
 * goals.put("carbs", 250);
 * goals.put("fat", 70);
 * User alice = new User("1001", "Alice", goals);
 * </pre>
 * 
 * @author Yaseen Osman
 */
public class User {
    //Unique student ID for the user
    public String studentId;
    //User's name
    public String name;
    //Nutrition goals: map of goal type ("calories", "protein", etc.) to value 
    public Map<String, Integer> goals; // calories, protein, carbs, fat
    //Daily nutrition log: date (YYYY-MM-DD) → NutritionFacts
    public Map<String, NutritionFacts> nutritionLog;
    //Set of friend student IDs for social features
    public Set<String> friends;

    /**
     * Constructs a User with the given ID, name, and nutrition goals.
     * 
     * @param studentId The user's unique student ID
     * @param name      The user's name
     * @param goals     Nutrition goals (map: "calories", "protein", "carbs", "fat")
     */
    public User(String studentId, String name, Map<String, Integer> goals) {
        this.studentId = studentId;
        this.name = name;
        this.goals = goals;
        this.nutritionLog = new HashMap<>();
        this.friends = new HashSet<>();
    }

    /**
     * Logs a meal for the given date by adding the provided NutritionFacts to that day's log.
     * If there is no entry for the date, a new one is created.
     * 
     * @param date Date in "YYYY-MM-DD" format
     * @param nf   NutritionFacts to add for this meal
     */
    public void logMeal(String date, NutritionFacts nf) {
        nutritionLog.putIfAbsent(date, new NutritionFacts(0, 0, 0, 0));
        nutritionLog.get(date).add(nf);
    }

    /**
     * Gets the accumulated NutritionFacts for the given date.
     * If no meals were logged for that date, returns an empty NutritionFacts object.
     * 
     * @param date Date in "YYYY-MM-DD" format
     * @return NutritionFacts for the date (or empty if none logged)
     */
    public NutritionFacts getDailyNutrition(String date) {
        return nutritionLog.getOrDefault(date, new NutritionFacts(0, 0, 0, 0));
    }

    /**
     * Returns a string summary of the user's nutrition for a given date.
     * 
     * @param date Date in "YYYY-MM-DD" format
     * @return String summary for sharing
     */
    public String shareNutrition(String date) {
        NutritionFacts nf = getDailyNutrition(date);
        return String.format("%s's nutrition on %s: %s", name, date, nf);
    }
}
