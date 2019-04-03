#Test Plan

**Author**: Miao Xu, Team4

## 1 Testing Strategy

### 1.1 Overall Strategy
*Should test each class of the design. Listmanager, GroceryList, ItemType, and Item.
First, we should test each class to verify each unit works. 
Second, we should test the processes between each unit to verify the relationship of each process is correct.
Third, we should test the system of the design to verify the whole system can work.
Forth, we should test the system of the design to verify the return of each class is correct.*

### 1.2 Test Selection
*I will use white-box technique to test the design. 
Base on the design, the size of classes is not huge. It is easy to use white-box.*

### 1.3 Adequacy Criterion
*Test the operation of ListManager: create List, rename List, select List, and delete List.
Test The operation of GroceryList: add item, delete item, change item, search item, and uncheck.
Test the operation of ItemType: create new item, item type, and item name.
Test the operation of Item: change Quantity.*

### 1.4 Bug Tracking
*Test each operation of each class one by one.
If some operation does not return correct result, the error can be found.
If some attribute does not return correct result, the error can be found.*

### 1.5 Technology
*Test each item and operation depending on the black-box test.*



## 2 Test Cases
*On ListManager

Create List
show new list and notice input list name, good
else error

rename List
notice input the new list name, good
else error

select List
show the checked mark on the list, good
else error

delete List
the list is removed, good
else error

On GroceryList

add Item
show new item, good
else error

delete item
the item is removed, good
else error

change item quantity
show input the quantity of the item, good
else error

search item
show the input request on the search port, good
else error

uncheck All
remove all the marks of the checked on the lists, good
else error

On ItemType

create New Item
show the new item and request input name, good
else error

On Item

change Quantity
show the input of the request on the quantity of the item, good
else error*





