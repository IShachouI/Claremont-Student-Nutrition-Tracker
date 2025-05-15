import java.util.*;

/**
 * DiningHallMenu stores the menus and nutrition facts for all dining halls.
 * 
 * The data structure is:
 * <pre>
 *   menu: Map<diningHall, Map<meal, List<MenuItem>>>
 * </pre>
 * where each MenuItem contains a food item name and its NutritionFacts.
 * 
 * Example usage:
 * <pre>
 *   DiningHallMenu dhMenu = new DiningHallMenu();
 *   dhMenu.addMenuItem("Frank", "Breakfast", "Scrambled Eggs", new NutritionFacts(...));
 *   Set<String> halls = dhMenu.getDiningHalls();
 *   List<MenuItem> items = dhMenu.getMenuItems("Frank", "Breakfast");
 * </pre>
 * 
 * @author Yaseen Osman
 */
public class DiningHallMenu {
    /** 
     * Nested map: diningHall -> meal -> List of MenuItem objects.
     */
    public Map<String, Map<String, List<MenuItem>>> menu;

    /**
     * Inner class representing a menu item and its nutrition facts.
     */
    public static class MenuItem {
        /** Name of the food item */
        public String foodItem;
        /** Nutrition facts for the food item */
        public NutritionFacts facts;

        /**
         * Constructs a MenuItem.
         * @param foodItem Name of the food item.
         * @param facts NutritionFacts object for this food item.
         */
        public MenuItem(String foodItem, NutritionFacts facts) {
            this.foodItem = foodItem;
            this.facts = facts;
        }
    }

    /**
     * Constructs an empty DiningHallMenu.
     */
    public DiningHallMenu() {
        menu = new HashMap<>();
    }

    /**
     * Adds a menu item with nutrition facts to the specified dining hall and meal.
     * If the dining hall or meal does not exist, they are created.
     * 
     * @param diningHall Name of the dining hall.
     * @param meal Meal period (e.g., "Breakfast", "Lunch").
     * @param foodItem Name of the food item.
     * @param facts Nutrition facts for the food item.
     */
    public void addMenuItem(String diningHall, String meal, String foodItem, NutritionFacts facts) {
        menu.putIfAbsent(diningHall, new HashMap<>());
        menu.get(diningHall).putIfAbsent(meal, new ArrayList<>());
        menu.get(diningHall).get(meal).add(new MenuItem(foodItem, facts));
    }

    /**
     * Returns a set of all dining hall names.
     * 
     * @return Set of dining hall names.
     */
    public Set<String> getDiningHalls() {
        return menu.keySet();
    }

    /**
     * Returns a set of all meal periods for a given dining hall.
     * 
     * @param diningHall Name of the dining hall.
     * @return Set of meal periods, or an empty set if hall not found.
     */
    public Set<String> getMeals(String diningHall) {
        return menu.getOrDefault(diningHall, new HashMap<>()).keySet();
    }

    /**
     * Returns a list of menu items for a given dining hall and meal period.
     * 
     * @param diningHall Name of the dining hall.
     * @param meal Meal period.
     * @return List of MenuItem objects, or an empty list if not found.
     */
    public List<MenuItem> getMenuItems(String diningHall, String meal) {
        return menu.getOrDefault(diningHall, new HashMap<>())
                   .getOrDefault(meal, new ArrayList<>());
    }
}
