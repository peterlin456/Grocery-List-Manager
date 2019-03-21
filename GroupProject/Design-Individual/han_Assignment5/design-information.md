#### To be able to implement the app for `Grocery List Manager`, we will need to create an UML class diagram.

##### Requirements

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
> I created 2 classes, class `Grocery` with attribute ***groceryList***. Class `GroceryList` with attribute ***name*** and operations **addItem()**, **deleteItem()**, and **changeQuantity()**.

2. The application must contain a database (DB) of items and corresponding item types.
> Assume that the database for items and items' type exists.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item.
> I Added `CategoryList` with attribute ***itemType*** to specify an item's type. Next I Added another class `Item` with attribute ***name*** under `CategoryList`. I Also added association class `addToList` with attribute ***quantity***.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
> This will be handled by **add()** under `CategoryList`.

5. Lists must be saved automatically and immediately after they are modified.
> Every function will save the changes every time the list is updated.

6. Users must be able to check off items in a list (without deleting them).
> Added operation **change_check_box()** under `GroceryList` and ***checkBox*** under `addToList`.

7. Users must also be able to clear all the check-off marks in a list at once.
> Added operation **clearAll()** under `GroceryList`.

8. Check-off marks for a list are persistent and must also be saved immediately.
> Every function will save the changes every time the list is updated.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
> `CategoryList` is followed by the "Item".

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
> Added operations **selectList()**, **createList()**, and **deleteList()** under `GroceryList` and `GroceryList` is under `Grocery`.

11. The User Interface (UI) must be intuitive and responsive.
> Not considered during the design phase.
