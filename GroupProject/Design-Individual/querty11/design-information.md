1.	A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list,
delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

To realize this, I added to the design the Item class with the itemName and itemQuantity attributes and the operation changeQuantity,
the ListManager class with attribute groceryList, and the GroceryList class with the attribute items as an array of the GroceryList’s Items
and the operations addItem, deleteItem, and changeItemQuantity.

2.	The application must contain a database (DB) of items and corresponding item types.

While the database is not part of the UML design, I added the ItemType class with the attribute itemType.

3.	Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal),
and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

When addItem is called, the application could display available ItemTypes from the database to choose from and then the Items in the database
with the chosen itemType. The operation changeQuantity can be used to specify the quantity.

4.	Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names
and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user
to select a type for the item and then save the new item together with its type, in its DB.

To realize this, I added the operation searchItem to the GroceryList class to search for an Item by itemName to add to the GroceryList.
For a new Item, createNewItem is called from ItemType to add a new Item with an ItemType.

5.	Lists must be saved automatically and immediately after they are modified.

Lists are saved to the database which is not part of the UML class diagram.

6.	Users must be able to check off items in a list (without deleting them).

To realize this, I added the attribute checkedOff to the Item class as a boolean.

7.	Users must also be able to clear all the check-off marks in a list at once.

To realize this, I added the operation uncheckAll to the GroceryList class to set checkedOff to false for all Items in the GroceryList.

8.	Check-off marks for a list are persistent and must also be saved immediately.

The attribute checkedOff for an Item in a GroceryList is saved into the database.

9.	The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once
(i.e., without having to go back and forth between aisles).

The application can display this list using the ItemTypes that are stored in the database and the Item class’s itemType attribute.

10.	The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”).
Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

To realize this, I changed ListManager’s groceryList attribute to groceryLists as an array of the ListManager’s GroceryLists and
added the operations createList, renameList, selectList, and deleteList.

11.	The User Interface (UI) must be intuitive and responsive.

Not considered because it does not affect the design directly.
