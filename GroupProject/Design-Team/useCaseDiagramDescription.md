For each use case in the use case diagram, this section should contain a description, with the following elements:*

- *Requirements: High-level description of what the use case must allow the user to do.*
- *Pre-conditions: Conditions that must be true before the use case is run.*
- *Post-conditions Conditions that must be true once the use case is run.*
- *Scenarios: Sequence of events that characterize the use case. This part may include multiple scenarios, 
for normal, alternate, and exceptional event sequences. 
These scenarios may be expressed as a list of steps in natural language or as sequence diagrams.*

## The above should be delted, it is only there for reference when creating this document.

Select List

- *Requirements: User has the choice of which type of list he wants to use.
- *Pre-conditions: n/a
- *Post-conditions: User's GUI will show the selected list.
- *Scenarios: User opens up app and has the option to choose a desired list. User has the ability to switch from Lists.

Create List

- *Requirements: User has the choice of creating a new list.
- *Pre-conditions: n/a
- *Post-conditions: User has the option of using the newly created list. The newly created list will be saved under "Select List".
- *Scenarios: User opens up app and has the option to create a new list.

Rename List

- *Requirements: User can rename a list that are stored under "Select List".
- *Pre-conditions: List must already exist and should be found under "Select List".
- *Post-conditions: List will be renamed and saved from "Select List".
- *Scenarios: List name misstyped when created, user would like to rename it.

Delete List

- *Requirements: List is no longer needed therefore user deletes it.
- *Pre-conditions: List must already and should be found under "Select List".
- *Post-conditions: List would be removed from "Select List".
- *Scenarios: List no longer needed or list created by mistake therefore discarded.

Check off item from list

- *Requirements: User checks off a item from list, item is not deleted from list.
- *Pre-conditions: List must have been selected prior.
- *Post-conditions: Item would be checked off from list.
- *Scenarios: As user shops they would check off items they added to their cart.

Clear all check offs from list

- *Requirements: All checks from a list would be cleared (unchecked).
- *Pre-conditions: List would have at least one item checked off.
- *Post-conditions: List would have no checked off items.
- *Scenarios: User would like to reuse a list that has been previously checked off.

Delete item

- *Requirements: User no longer needs an item and discards it from list.
- *Pre-conditions: List must have this item in it.
- *Post-conditions: List will no longer have this item in it and it would save its update.
- *Scenarios: User no longer needs a particular item or added it by mistake.

Add item

- *Requirements: User adds an item to the list.
- *Pre-conditions: The item added was not previously there.
- *Post-conditions: Item added to the list and it is saved for future reference.
- *Scenarios: User needs a new item and would add it to a particular list.

Specifiy Quantity

- *Requirements: User specifies the quantity of a item either by pounds or count.
- *Pre-conditions: Item must exist in the list.
- *Post-conditions: List will be updated with its quantity and saved. 
- *Scenarios: Item either existed in list or item was added and user would like to add a quantity for it.

Search for item

- *Requirements: User searches for an item to add to list.
- *Pre-conditions: Item does not exist in the current list.
- *Post-conditions: After item searched it would be added to the list as well as quantity specified then saved.
- *Scenarios: User would like to add an item and searches for it.

Similar items found

- *Requirements: User searches for item and a list of similar item names.
- *Pre-conditions: Item must not exsist in current list.
- *Post-conditions: Search returns similar items and user can either add it to the list or not. If item added the list is saved.
- *Scenarios: User searches for a specific item or wishes to find similar items to add to the list.

No match found

- *Requirements: User has searched for an item but there are no matches.
- *Pre-conditions: Item does not exisit in list and the item database.
- *Post-conditions: Item not found error would appear in GUI.
- *Scenarios: User searches for a desired item but it does not exsist in database.

Add and save new item

- *Requirements: User adds an item by type and saves it to the database.
- *Pre-conditions: User searches for item, no match found case occurs notifiying that the item doesn't exist.
- *Post-conditions: The item is added to the database as well as the list, list is saved.
- *Scenarios: User wishes to add an item, search tool is used, no match found case occurs, item is added to database by specifiyng its type.
              Item is also added to list and list is saved.