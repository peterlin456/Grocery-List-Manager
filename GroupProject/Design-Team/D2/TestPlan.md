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
On ListManager

| Test | Description | Expected Result |
|----|:-----|:-----|
| Create List | Show new list and notice input list name | New list created |
| Rename List | Notice input the new list name | Renamed list |
| Select List | Show the list | List is shown |
| Delete List | The list is removed | List is deleted |

On GroceryList

| Test | Description | Expected Result |
|----|:-----|:-----|
| Add Item | Show new item | Item added |
| Delete item | The item is removed | Item removed |
| Change item quantity | show input the quantity of the item | Item quantity changed |
| Search item | show the input request on the search port | Item found or not found |
| Uncheck All | Remove all the marks of the checked on the lists | All checks removed |

On ItemType

| Test | Description | Expected Result |
|----|:-----|:-----|
| Create New Item | Show the new item and request input name | New Item Saved |

On Item

| Test | Description | Expected Result |
|----|:-----|:-----|
| Change Quantity | Show the input of the request on the quantity of the item | Item Quantity Changed |





