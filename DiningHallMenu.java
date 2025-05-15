import java.util.*;

/**
 * DiningHallMenu stores menus and nutrition facts.
 */
public class DiningHallMenu {
    // diningHall -> meal -> List of (foodItem, NutritionFacts)
    public Map<String, Map<String, List<MenuItem>>> menu;

    public static class MenuItem {
        public String foodItem;
        public NutritionFacts facts;

        public MenuItem(String foodItem, NutritionFacts facts) {
            this.foodItem = foodItem;
            this.facts = facts;
        }
    }

    public DiningHallMenu() {
        menu = new HashMap<>();
    }

    public void addMenuItem(String diningHall, String meal, String foodItem, NutritionFacts facts) {
        menu.putIfAbsent(diningHall, new HashMap<>());
        menu.get(diningHall).putIfAbsent(meal, new ArrayList<>());
        menu.get(diningHall).get(meal).add(new MenuItem(foodItem, facts));
    }

    public Set<String> getDiningHalls() {
        return menu.keySet();
    }

    public Set<String> getMeals(String diningHall) {
        return menu.getOrDefault(diningHall, new HashMap<>()).keySet();
    }

    public List<MenuItem> getMenuItems(String diningHall, String meal) {
        return menu.getOrDefault(diningHall, new HashMap<>()).getOrDefault(meal, new ArrayList<>());
    }
}
