# Test Plan

**Author**: Team 4

Version 1.1
## 1 Testing Strategy

### 1.1 Overall Strategy
- We will test each class to verify that each unit works individually, the processes between each unit to verify that the relationship of each process is correct, the system of the design to verify that the whole system works, and the system of the design against the requirements to validate the system.

### 1.2 Test Selection
- We will use white-box techniques for the unit and integration tests. We will use both white-box and black-box techniques for system and validation testing.

### 1.3 Adequacy Criterion
- We will be assessing the white-box test cases using key values representing possible sets of inputs for expected results and boundary cases. For black-box testing, we will be testing against the design and requirements formed by the team's analysis.

### 1.4 Bug Tracking
- Bugs and enhancements will be tracked informally in a separate document in the repository.

### 1.5 Technology
- Manual testing and JUnit testing will be used.

## 2 Test Cases

|ID | Test | Description | Steps | Expected Result | Actual Result | Pass/Fail Information | Additional Information |
|----|:-----|:-----|:-----|:-----|:-----|:-----|:-----|
| A1 | Select List | Show the list | 	1. User selects a list. | List is shown | N/A | None | None |
| A2 | Create List | Show new list and notice input list name |	1. User selects option "Create New List".<br>2. User enters a name for the list.<br>	3. User clicks "Create List". | New list created and saved | N/A | None | None |
| A3 | Rename List | Notice input the new list name  | 	1. User chooses option "Rename List"<br>2. User selects a list.<br>3. User enters a name for the list.| List is renamed | N/A | None | None |
| A3.1 | Rename List | Notice input the new list name | 	1.User check the lists  to rename.<br>2.User clicks the overflow menu.<br>3.User clicks Rename.| Toast message pop up at bottom of the screen. | Toast message pop up at bottom of the screen. | Pass | None |
| A4 | Delete List | The list is removed |	1. User chooses option "Delete List"<br>2. User selects a list.| List is deleted | N/A | None | None |
| A5 | Check off item | Check off an item on a list |	1. [A1] Select List<br> 2. User checks off an item on the list.| Item checked off and saved | N/A | None | None |
| A6 | Uncheck All | Remove all the marks of the checked on the lists |	1.  Select List<br>2. User chooses option "Uncheck All".| All checks removed | N/A | None | None |
| A7 | Delete item | The item is removed |	1. [A1] Select List<br>2. User chooses "Delete item" for an item on a list.| Item is removed from the list. | N/A | None | None |
| A8 | Add Item | Add an item to a list |	1. [A1] Select List<br>2. User chooses option "Add item".<br>3. User selects an item from a list to add.<br>4. User enters a valid quantity.| Item added to the list and saved | N/A | None | None |
| A8.1 | Add Item | Add an item to a list | 1. A item(coffee) is already exist.<br>2. User clicks the overflow menu.<br>3. User clicks the Add Item.<br>4. User enter same item(coffee).| The quantity of item(coffee) add up, and item is not duplicate in list. | The quantity of item(coffee) add up, and item is not duplicate in list.| Pass | None |
| A9 | Change Quantity | Change the quantity for an item on a list |	1. [A1] Select List<br>2. User selects "Change Quantity" for an item on the list.<br>3. User enters a valid quantity.| Item quantity changed and saved | N/A | None | None |
| A10 | Search item found | Search for an item that is in the database |	1. [A1] Select List<br>2. User chooses option "Search for item".<br>3. User inputs a valid value to search.<br>4. The system presents a list of items with matching names.<br>5. User selects an item from the given list to add.<br>	6. [A8] Add Item.| Item found and added to the list | N/A | None | None |
| A11 | Search item not found | Search for an item not in the database and add a new item into the database |	1. [A1] Select List<br>2. User chooses option "Search for item".<br>3. User inputs a valid value to search.<br>User selects to add a new item to the database.<br>4. User selects an existing item type. <br>5. User enters a valid item name.<br>6. [A8] Add Item. | New Item saved to database. Item added to list. | N/A | None | New item may/may not be saved to the database if item is not added to the list |
