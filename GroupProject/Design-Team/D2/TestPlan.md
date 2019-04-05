#Test Plan

**Author**: Miao Xu, Team 4

## 1 Testing Strategy

### 1.1 Overall Strategy
- *Should test each class of the design. Listmanager, GroceryList, ItemType, and Item.*
- *First, we should test each class to verify each unit works. *
- *Second, we should test the processes between each unit to verify the relationship of each process is correct.*
- *Third, we should test the system of the design to verify the whole system can work.*
- *Fourth, we should test the system of the design to verify the return of each class is correct.*

### 1.2 Test Selection
- *I will use white-box technique to test the design.*
- *Base on the design, the size of classes is not huge. It is easy to use white-box.*

### 1.3 Adequacy Criterion
- *Test the operation of ListManager: create List, rename List, select List, and delete List.*
- *Test The operation of GroceryList: add item, delete item, change item, search item, and uncheck.*
- *Test the operation of ItemType: create new item, item type, and item name.*
- *Test the operation of Item: change Quantity.*

### 1.4 Bug Tracking
- *Test each operation of each class one by one.*
- *If some operation does not return correct result, the error can be found.*
- *If some attribute does not return correct result, the error can be found.*

### 1.5 Technology
*Test each item and operation depending on the black-box test.*

## 2 Test Cases

|ID | Test | Description | Steps | Expected Result | Actual Result | Pass/Fail Information | Additional Information |
|----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| A1 | Select List | Show the list | 	1. User selects a list. | List is shown | N/A | None | None |
| A2 | Create List | Show new list and notice input list name |	1. User selects option "Create New List".<br>2. User enters a name for the list.<br>	3. User clicks "Create List". | New list created and saved | N/A | None | None |
| A3 | Rename List | Notice input the new list name | 	1. User chooses option "Rename List"<br>2. User selects a list.<br>3. User enters a name for the list.| List is renamed | N/A | None | None |
| A4 | Delete List | The list is removed |	1. User chooses option "Delete List"<br>2. User selects a list.| List is deleted | N/A | None | None |
| A5 | Check off item | Check off an item on a list |	1. [A1] Select List<br> 2. User checks off an item on the list.| Item checked off and saved | N/A | None | None |
| A6 | Uncheck All | Remove all the marks of the checked on the lists |	1. [A1] Select List<br>2. User chooses option "Uncheck All".| All checks removed | N/A | None | None |
| A7 | Delete item | The item is removed |	1. [A1] Select List<br>2. User chooses "Delete item" for an item on a list.| Item is removed from the list. | N/A | None | None |
| A8 | Add Item | Add an item to a list |	1. [A1] Select List<br>2. User chooses option "Add item".<br>3. User selects an item from a list to add.<br>4. User enters a valid quantity.| Item added to the list and saved | N/A | None | None |
| A9 | Change Quantity | Change the quantity for an item on a list |	1. [A1] Select List<br>2. User selects "Change Quantity" for an item on the list.<br>3. User enters a valid quantity.| Item quantity changed and saved | N/A | None | None |
| A10 | Search item found | Search for an item that is in the database |	1. [A1] Select List<br>2. User chooses option "Search for item".<br>3. User inputs a valid value to search.<br>4. The system presents a list of items with matching names.<br>5. User selects an item from the given list to add.<br>	6. [A8] Add Item.| Item found and added to the list | N/A | None | None |
| A11 | Search item not found | Search for an item not in the database and add a new item into the database |	1. [A1] Select List<br>2. User chooses option "Search for item".<br>3. User inputs a valid value to search.<br>User selects to add a new item to the database.<br>4. User selects an existing item type. <br>5. User enters a valid item name.<br>6. [A8] Add Item. | New Item saved to database. Item added to list. | N/A | None | New item may/may not be saved to the database if item is not added to the list |
