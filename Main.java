import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    // User nutrition log: Map<studentId, Map<date, NutritionFacts>>
    static class NutritionFacts {
        double calories = 0, fat = 0, carbs = 0, protein = 0;
        public void add(MenuItem item) {
            calories += item.calories;
            fat += item.fat;
            carbs += item.carbs;
            protein += item.protein;
        }
        public String toString() {
            return String.format("Calories: %.1f, at: %.1fg, Carbs: %.1fg, Protein: %.1fg",
                    calories, fat, carbs, protein);
        }
    }

    static class User {
        String id, name;
        double calorieGoal;
        Set<String> friends = new HashSet<>();
        Map<String, NutritionFacts> logs = new HashMap<>(); // date -> NutritionFacts
        public User(String id, String name, double calorieGoal) {
            this.id = id;
            this.name = name;
            this.calorieGoal = calorieGoal;
        }
    }

    public static void main(String[] args) throws Exception {
        // Load menu
        String filename = "PomonaDiningHalls.csv";
        HashMap<String, HashMap<String, List<MenuItem>>> menu = DiningHallMenuLoader.loadMenu(filename);

        // Sample users
        Map<String, User> users = new HashMap<>();
        User alice = new User("1001", "Alice", 2000);
        User bob = new User("1002", "Bob", 2200);
        alice.friends.add("1002");
        bob.friends.add("1001");
        users.put(alice.id, alice);
        users.put(bob.id, bob);

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Student Nutrition Tracker!");
        System.out.print("Enter your student ID: ");
        String studentId = sc.nextLine().trim();
        if (!users.containsKey(studentId)) {
            System.out.println("User not found. Exiting.");
            return;
        }
        User user = users.get(studentId);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Log a meal");
            System.out.println("2. View today's nutrition");
            System.out.println("3. Get meal recommendation");
            System.out.println("4. Share nutrition with friends");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                //System.out.print("Enter date (YYYY-MM-DD): ");
    
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                // List dining halls
                List<String> halls = new ArrayList<>(menu.keySet());
                Collections.sort(halls);
                for (int i = 0; i < halls.size(); i++)
                    System.out.printf("%d. %s\n", i + 1, halls.get(i));
                System.out.print("Choose dining hall number: ");
                int dhIdx = Integer.parseInt(sc.nextLine()) - 1;
                if (dhIdx < 0 || dhIdx >= halls.size()) {
                    System.out.println("Invalid selection.");
                    continue;
                }
                String hall = halls.get(dhIdx);

                // List meal periods
                List<String> meals = new ArrayList<>(menu.get(hall).keySet());
                Collections.sort(meals);
                for (int i = 0; i < meals.size(); i++)
                    System.out.printf("%d. %s\n", i + 1, meals.get(i));
                System.out.print("Choose meal period number: ");
                int mealIdx = Integer.parseInt(sc.nextLine()) - 1;
                if (mealIdx < 0 || mealIdx >= meals.size()) {
                    System.out.println("Invalid selection.");
                    continue;
                }
                String meal = meals.get(mealIdx);

                // List dishes
                List<MenuItem> items = menu.get(hall).get(meal);
                for (int i = 0; i < items.size(); i++)
                    System.out.printf("%d. %s\n", i + 1, items.get(i));
                System.out.print("Choose dish number: ");
                int dishIdx = Integer.parseInt(sc.nextLine()) - 1;
                if (dishIdx < 0 || dishIdx >= items.size()) {
                    System.out.println("Invalid selection.");
                    continue;
                }
                MenuItem item = items.get(dishIdx);

                // Log meal
                user.logs.putIfAbsent(date, new NutritionFacts());
                user.logs.get(date).add(item);
                System.out.println("Logged: " + item.dish + " for " + date);
            }
            else if (choice.equals("2")) {
               // System.out.print("Enter date (YYYY-MM-DD): ");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));;
                NutritionFacts nf = user.logs.getOrDefault(date, new NutritionFacts());
                System.out.println("Nutrition for " + date + ": " + nf);
                System.out.println("Your calorie goal: " + user.calorieGoal);
            }
            else if (choice.equals("3")) {
                //System.out.print("Enter date (YYYY-MM-DD): ");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                double remaining = user.calorieGoal - user.logs.getOrDefault(date, new NutritionFacts()).calories;
                MenuItem best = null;
                String bestHall = "", bestMeal = "";
                double minDiff = Double.MAX_VALUE;
                for (String hall : menu.keySet()) {
                    for (String meal : menu.get(hall).keySet()) {
                        for (MenuItem item : menu.get(hall).get(meal)) {
                            double diff = Math.abs(item.calories - remaining);
                            if (diff < minDiff) {
                                minDiff = diff;
                                best = item;
                                bestHall = hall;
                                bestMeal = meal;
                            }
                        }
                    }
                }
                if (best != null) {
                    System.out.printf("Recommended: %s at %s (%s) - %.1f kcal\n",
                            best.dish, bestHall, bestMeal, best.calories);
                } else {
                    System.out.println("No recommendation available.");
                }
            }
            else if (choice.equals("4")) {
                //System.out.print("Enter date (YYYY-MM-DD): ");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                NutritionFacts nf = user.logs.getOrDefault(date, new NutritionFacts());
                String summary = user.name + "'s nutrition on " + date + ": " + nf;
                for (String fid : user.friends) {
                    if (users.containsKey(fid)) {
                        System.out.printf("Shared with %s: %s\n", users.get(fid).name, summary);
                    }
                }
            }
            else if (choice.equals("5")) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid option.");
            }
        }
    }
}
