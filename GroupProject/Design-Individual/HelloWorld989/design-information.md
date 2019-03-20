# Assignment 5

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
* To realize this requirement, I added a class Grocery List with attributes List ID, List Name, List Status and Check-off. The operations are create, name/rename, select, and delete.

2. The application must contain a database (DB) of items and corresponding item types .
* To realize this requirement, I added a Database (DB) Item with attributes Item ID, Item name, Item type and Item group. The operations are add, delete, change, save, and search.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
* To realize this requirement, I added one class First Level of Item type and another class Second Level of Item Actual name. The class First Level of Item type contain First Level of Item type. The class Second Level of Item Actual name contain Item Actual name and Item Acutal Name ID. The Item Actal Name ID equal to the Item ID of Item Database. Users can create, delete, select ,save, and name/rename.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
* To realize this requirement, I added to the design a search Operation in Item Database.

5. Lists must be saved automatically and immediately after they are modified.
* To realize this requirement, users can edit lists in the list class. When users finished edit, the list class will be saved.

6. Users must be able to check off items in a list (without deleting them).
* To realize this requirement, I added the check-off attribute in List class.

7. Users must also be able to clear all the check-off marks in a list at once.
* To realize this requirement, I added the clear attribute in List class. 

8. Check-off marks for a list are persistent and must also be saved immediately.
* To realize this requirement, users can do this operation in List, and the list will auto-saved immediately.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
* To realize this requirement, I added Item type and Item group in Item Database. The database would be auto-saved after users finished their operations.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
* To realize this requirement, I added create operation in List class. It allowed users to create multiple lists at a time and name/rename, select, and delete lists.

11. The User Interface (UI) must be intuitive and responsive.
* Not considered because it does not affect the design directly.
