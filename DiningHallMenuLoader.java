import java.io.*;
import java.util.*;
/**
 * DiningHallMenuLoader is a utility class for loading dining hall menu data
 * from a CSV file into a nested HashMap structure.
 *
 * The resulting data structure is:
 *   HashMap<diningHall, HashMap<mealPeriod, List<MenuItem>>>
 * where each MenuItem contains nutrition facts and other info.
 *
 * Example usage:
 *   HashMap<String, HashMap<String, List<MenuItem>>> menu = DiningHallMenuLoader.loadMenu("PomonaDiningHalls.csv");
 *
 * @author Yaseen Osman
 */

public class DiningHallMenuLoader {
    public static HashMap<String, HashMap<String, List<MenuItem>>> loadMenu(String filename) throws IOException {
        HashMap<String, HashMap<String, List<MenuItem>>> menu = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String header = br.readLine(); //skip header
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = parseCSVLine(line);

            //Defensive: skip if not enough columns
            if (tokens.length < 17) continue;

            String diningHall = tokens[1].trim();
            String mealPeriod = tokens[2].trim();
            String station = tokens[3].trim();
            String dish = tokens[4].trim();
            String servingSize = tokens[5].trim();

            double calories = parseDouble(tokens[6]);
            double fat = parseDouble(tokens[7]);
            double carbs = parseDouble(tokens[12]);
            double protein = parseDouble(tokens[16]);

            MenuItem item = new MenuItem(dish, station, servingSize, calories, fat, carbs, protein);

            menu.putIfAbsent(diningHall, new HashMap<>());
            menu.get(diningHall).putIfAbsent(mealPeriod, new ArrayList<>());
            menu.get(diningHall).get(mealPeriod).add(item);
        }
        br.close();
        return menu;
    }

    //Helper to handle empty or "NA" values
    private static double parseDouble(String s) {
        try {
            if (s == null || s.trim().isEmpty() || s.trim().equalsIgnoreCase("NA")) return 0.0;
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    //Simple CSV parser (handles quoted commas)
    private static String[] parseCSVLine(String line) {
    List<String> tokens = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean inQuotes = false;
    for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (c == '"') {
            inQuotes = !inQuotes;
        } else if (c == ',' && !inQuotes) {
            tokens.add(sb.toString());
            sb.setLength(0);
        } else {
            sb.append(c);
        }
    }
    tokens.add(sb.toString()); //Add the last token
    return tokens.toArray(new String[0]); //This always returns an array
}

}