###Use Case Model

**Author**: Team 4

## 1 Use Case Diagram

![Use Case Diagram image](UseCaseDiagram.png)

## 2 Use Case Description

Select List

- *Requirements: User has the choice of which type of list he wants to use.
- *Pre-conditions: n/a
- *Post-conditions: User's GUI will show the selected list.
- *Scenarios: User choose option "List" and picks one.

Create List

- *Requirements: User has the choice of creating a new list.
- *Pre-conditions: n/a
- *Post-conditions: User has the option of using the newly created list. The newly created list will be saved under "Select List".
- *Scenarios: User selects option "Creates List".

Rename List

- *Requirements: User can rename a list that are stored under "Select List".
- *Pre-conditions: List must already exist and should be found under "Select List".
- *Post-conditions: List will be renamed and saved from "Select List".
- *Scenarios: User  chooses option "Rename List" and renames a list.

Delete List

- *Requirements: List is no longer needed therefore user deletes it.
- *Pre-conditions: List must already and should be found under "Select List".
- *Post-conditions: List would be removed from "Select List".
- *Scenarios: User chooses option "Delete List" and deletes a list.

Check off item from list

- *Requirements: User checks off a item from list, item is not deleted from list.
- *Pre-conditions: List must have been selected prior.
- *Post-conditions: Item would be checked off from list.
- *Scenarios: User has choosen a list and checks off an item from the list.

Clear all check offs from list

- *Requirements: All checks from a list would be cleared (unchecked).
- *Pre-conditions: List would have at least one item checked off.
- *Post-conditions: List would have no checked off items.
- *Scenarios: User has choosen a list and has one or more item checked off.
	User choose option "Clear all check offs from list", list is set back to default state.

Delete item

- *Requirements: User no longer needs an item and discards it from list.
- *Pre-conditions: List must have this item in it.
- *Post-conditions: List will no longer have this item in it and it would save its update.
- *Scenarios: User has choosen a list, chooses "Delete item" option and item is removed from list.

Add item

- *Requirements: User adds an item to the list.
- *Pre-conditions: The item added was not previously there.
- *Post-conditions: Item added to the list and it is saved for future reference.
- *Scenarios: User has choosen a list, and chooses option "Add item" a new item is added to the list and saved.

Specifiy Quantity

- *Requirements: User specifies the quantity of a item either by pounds or count.
- *Pre-conditions: Item must exist in the list.
- *Post-conditions: List will be updated with its quantity and saved. 
- *Scenarios: Item either existed in list or item was added and user would like to add a quantity for it.

Search for item

- *Requirements: User searches for an item to add to list.
- *Pre-conditions: Item does not exist in the current list.
- *Post-conditions: After item searched it would be added to the list as well as quantity specified then saved.
- *Scenarios: User has choosen a list and chooses option "Search for item".

Similar items found

- *Requirements: User searches for item and a list of similar item names.
- *Pre-conditions: Item must not exsist in current list.
- *Post-conditions: Search returns similar items and user can either add it to the list or not. If item added the list is saved.
- *Scenarios: User has choosen a list and has searched for an item, searched has returned similar items and has the option to add it to list.

No match found

- *Requirements: User has searched for an item but there are no matches.
- *Pre-conditions: Item does not exisit in list and the item database.
- *Post-conditions: Item not found error would appear in GUI.
- *Scenarios: User has choosen a list and has searched for an item, searched has returned an error stating no items found.

Add and save new item

- *Requirements: User adds an item by type and saves it to the database.
- *Pre-conditions: User searches for item, no match found case occurs notifiying that the item doesn't exist.
- *Post-conditions: The item is added to the database as well as the list, list is saved.
- *Scenarios: User has choosen a list and has searched for an item, searched has returned an error stating no items found.
			  Item is now added to the database by type and to the list. 
