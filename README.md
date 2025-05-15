# Claremont-Student-Nutrition-Tracker

## Overview

**Student Nutrition Tracker** is a Java console application that helps Pomona College students easily and accurately track their nutrition and calorie intake when eating at campus dining halls.  
The app allows users to:
- Automatically log meals from the official Pomona dining hall menu.
- Track daily macros and calories.
- Get personalized meal recommendations based on nutrition goals.
- Share their nutrition log with friends for accountability and motivation.

---

## How to Run

1. **Clone the repository:**
   ```
   git clone https://github.com/IShachouI/Claremont-Student-Nutrition-Tracker
   cd Claremont-Student-Nutrition-Tracker
   ```

2. **Place the dataset**  
   Ensure `PomonaDiningHalls.csv` is in the root directory.

3. **Compile the code:**
   ```
   javac Main.java MenuItem.java DiningHallMenuLoader.java
   ```

4. **Run the app:**
   ```
   java Main
   ```

---

## External Libraries

This project uses Java core libraries including java.time.

---

## Public API and Usage Examples

### MenuItem

Represents a menu item with nutrition facts.

**Constructor:**  
```
MenuItem(String dish, String station, String servingSize,
         double calories, double fat, double carbs, double protein)
```
**Example:**
```
MenuItem item = new MenuItem("Scrambled Eggs", "Grill Station", "85g", 117.65, 7.55, 0, 10.63);
```

**Fields:**  
- `dish` (String): Name of the dish  
- `station` (String): Dining hall station  
- `servingSize` (String): Serving size  
- `calories` (double): Calories per serving  
- `fat` (double): Fat grams  
- `carbs` (double): Carbohydrate grams  
- `protein` (double): Protein grams  

---

### DiningHallMenuLoader

**Method:**  
```
static HashMap>> loadMenu(String filename)
```
- **Input:** CSV filename
- **Output:** Nested HashMap: `diningHall` → `mealPeriod` → List of `MenuItem`
- **Description:** Loads the menu from CSV.

**Example:**
```
HashMap>> menu = DiningHallMenuLoader.loadMenu("PomonaDiningHalls.csv");
```

---

### Main (User-facing features)

- **Log a meal:**  
  User selects dining hall, meal period, and dish; nutrition is logged for the selected date.

- **View today's nutrition:**  
  Shows total calories, fat, carbs, and protein for the date.

- **Get meal recommendation:**  
  Suggests a dish from the menu closest to your remaining calorie goal.

- **Share nutrition with friends:**  
  Prints a summary of your daily log to your friends.

---

## Example Usage
I already have two registered students. First one is Alice with student id: 1001, the other is Bob with student id: 1002. If you want to add extra users, go to the Main.java and look for the comment called Sample users and add as many users as you like. 

```     
User bob = new User("1002", "Bob", 2200);
bob.friends.add("1001");
users.put(bob.id, bob);
```

**Log a meal:**
```
Enter your student ID: 1001
1. Log a meal
Choose dining hall number: 1
Choose meal period number: 2
Choose dish number: 3
Logged: Scrambled Eggs for 2025-05-14
```

**View today's nutrition:**
```
2. View today's nutrition
Nutrition for 2025-05-14: Calories: 117.7, Fat: 7.6g, Carbs: 0.0g, Protein: 10.6g
Your calorie goal: 2000.0
```

**Get meal recommendation:**
```
3. Get meal recommendation
Recommended: Grilled Halal Chicken Breast at Frank (Breakfast) - 217.0 kcal
```

**Share nutrition with friends:**
```
4. Share nutrition with friends
Shared with Bob: Alice's nutrition on 2025-05-14: Calories: 117.7, Fat: 7.6g, Carbs: 0.0g, Protein: 10.6g
```

---

## Dataset

- **File:** `PomonaDiningHalls.csv`
- **Format:** CSV, one row per menu item, with nutrition facts.


---

## Contributors

- Yaseen Osman

---

## Repository Structure

```
student-nutrition-tracker/
├── Main.java
├── MenuItem.java
├── DiningHallMenuLoader.java
├── PomonaDiningHalls.csv
├── README.md
```

---

## License

Apachi 2.0 License

---

