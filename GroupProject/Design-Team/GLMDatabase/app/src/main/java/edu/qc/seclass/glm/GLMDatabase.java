package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class GLMDatabase extends SQLiteOpenHelper
{
    private static final String GROCERY_LIST = "GroceryListApp";
    private static final int DATABASE_VERSION = 1;

    // Columns for Grocery List Table
    private static final String TABLE_GROCERY_LIST = "Grocery List";
    private static final String COLUMN_GROCERY_LIST_ID = "List ID";
    private static final String COLUMN_GROCERY_LIST_NAME = "List Name";

    // Columns for GroceryListRelationToItem
    private static final String TABLE_GROCERY_LIST_RELATION_TO_ITEM = "Grocery List Relation ToItem";
    private static final String COLUMN_GROCERY_LIST_RELATION_TO_ITEM_GL_ID = "Grocery List ID";
    private static final String COLUMN_GROCERY_LIST_RELATION_TO_ITEM_ITEM_ID = "Item ID";
    private static final String COLUMN_GROCERY_LIST_RELATION_TO_ITEM_QTY = "Quantity";

    // Columns for Item Type Table
    private static final String COLUMN_ITEM_TYPE_ID = "Type ID";
    private static final String COLUMN_ITEM_TYPE  = "Type";

    // Columns for Item List Table
    private static final String COlUMN_ITEM_LIST_ID = "Item ID";
    private static final String COlUMN_ITEM_LIST_ITEM_NAME = "Item Name";
    private static final String COlUMN_ITEM_LIST_TYPE_ID = "Type ID";

    // Creates tables, called by onCreate method
    private String CREATE_GROCERY_LIST_TABLE = "CREATE TABLE GROCERY_LIST ("
            + COLUMN_GROCERY_LIST_ID + " PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_GROCERY_LIST_NAME + "VARCHAR(60) NOT NULL)";

    private String CREATE_ITEM_TYPE_TABLE = "CREATE TABLE ITEM_TYPE ("
            + COLUMN_ITEM_TYPE_ID + " PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ITEM_TYPE + " VARCHAR(50) NOT NULL)";

    private String CREATE_ITEM_LIST_TABLE = "CREATE TABLE ITEM_LIST ("
            + COlUMN_ITEM_LIST_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COlUMN_ITEM_LIST_ITEM_NAME + "VARCHAR(50) NOT NULL,"
            + COlUMN_ITEM_LIST_TYPE_ID + "INTEGER NOT NULL,"
            + "FOREIGN KEY('"+ COlUMN_ITEM_LIST_TYPE_ID +"') "
            + "REFERENCES Item_Type('"+ COLUMN_ITEM_TYPE_ID +"'))";


    private String CREATE_GROCERY_LIST_RELATION_TO_ITEMS = "CREATE TABLE GROCERY_LIST_RELATION_TO_ITEMS ("
            + COLUMN_GROCERY_LIST_RELATION_TO_ITEM_GL_ID + "INTEGER NOT NULL,"
            + COLUMN_GROCERY_LIST_RELATION_TO_ITEM_ITEM_ID + "INTEGER NOT NULL,"
            + COLUMN_GROCERY_LIST_RELATION_TO_ITEM_QTY + "INTEGER NOT NULL DEFAULT 0,"
            + "PRIMARY KEY('"+COLUMN_GROCERY_LIST_RELATION_TO_ITEM_GL_ID+"',"
            + "'"+COLUMN_GROCERY_LIST_RELATION_TO_ITEM_ITEM_ID +"'),"
            + "FOREIGN KEY('"+COLUMN_GROCERY_LIST_RELATION_TO_ITEM_GL_ID+"') REFERENCES GROCERY_LIST ('"+ COLUMN_GROCERY_LIST_ID +"')+,"
            + "FOREIGN KEY('"+ COLUMN_GROCERY_LIST_RELATION_TO_ITEM_ITEM_ID +"') REFERENCES ITEM_LIST('"+ COLUMN_ITEM_TYPE_ID +"'))";

    // Constructor
    public GLMDatabase(Context context) {
        super(context, GROCERY_LIST, null, DATABASE_VERSION);
    }

    // Method that creates Data base
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TYPE_TABLE);
        db.execSQL(CREATE_ITEM_LIST_TABLE);
        db.execSQL(CREATE_GROCERY_LIST_TABLE);
        db.execSQL(CREATE_GROCERY_LIST_RELATION_TO_ITEMS);

        Log.e("Database operations ","Tables  Created ");
    }

    // Drops db if exists and recreates it. For now not being used.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS ITEM_TYPE");
//        db.execSQL("DROP TABLE IF EXISTS GROCERY_LIST" );
//        db.execSQL("DROP TABLE IF EXISTS ITEM_TYPE" );
//        db.execSQL("DROP TABLE IF EXISTS ITEM_LIST" );
//        onCreate(db);
    }



//    // Add a type to Item type table
    public void addType(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_TYPE, type);
        db.insert("ITEM_TYPE", null, cv);
        db.close();
    }

    // Add item GROCERY LIST RELATION TO ITEMS table
    public void addItem(String itemName, String itemType, String listName)
    {

    }

    // Set the quantity of an item after adding it, by default it would be zero.
    public void setQuantity(int n)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        if (n <= 0)
            cv.put(COLUMN_GROCERY_LIST_RELATION_TO_ITEM_QTY, 0);
        else
            cv.put(COLUMN_GROCERY_LIST_RELATION_TO_ITEM_QTY, n);

        db.insert("ITEM_TYPE", null, cv);
        db.close();

    }


    // Add a new list to Grocery List table
    public void addList(String newListName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GROCERY_LIST_NAME, newListName);
        db.insert("GROCERY_LIST", null, cv);
    }

    // Delete List  from Grocery List;
    public void deleteList(String listName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("GROCERY_LIST","List Name = ?", new String[] {listName});
    }

    // Change the name of a current list
    void editListName(String newListName, String listName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GROCERY_LIST_NAME, newListName);
        db.update("GROCERY_LIST", cv, "List Name = ?", new String []{listName});

    }

    // Returns the List id by using List Name
    public int getListID(String listName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT '"+ COLUMN_GROCERY_LIST_ID +"' FROM GROCERY_LIST"
                +"WHERE '"+ COLUMN_GROCERY_LIST_NAME +"'='" +listName+ "'", null);

        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;

    }
    // Returns list name from Grocery List table, using list id as reference
    public String getListName(int listID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT '"+ COLUMN_GROCERY_LIST_NAME +"' FROM ITEM_LIST"
                + "WHERE '"+ COLUMN_GROCERY_LIST_ID +"'=?'" + listID + "'",null);

        cursor.moveToFirst();
        String result = cursor.getString(cursor.getColumnIndex(COLUMN_GROCERY_LIST_NAME));
        cursor.close();
        return result;
    }

    // Returns type id from Item List Table, by referring to it by item name.
    public int getItemTypeIdFromILT(String itemName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT '" +  COlUMN_ITEM_LIST_TYPE_ID +"' FROM ITEM_LIST"
                +"WHERE '"+COlUMN_ITEM_LIST_ITEM_NAME +"'='"+ itemName +"'", null);

        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;
    }

    // Returns type id from Item Type List
    public int getItemTypeId(String type)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT '" + COlUMN_ITEM_LIST_ITEM_NAME +"'FROM ITEM_TYPE"
        + "WHERE '"+ COLUMN_ITEM_TYPE +"'='"+ type +"'",null);

        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        return result;
    }

    // Method that removes an Item from List, from GROCERY_LIST_RELATION_TO_ITEMS table.
    public void removeIteFromList(String listName, String itemName)
    {
        int itemID = getItemTypeId(itemName);
        int listNameId = getListID(listName);
        SQLiteDatabase db = this.getWritableDatabase();


        db.delete("GROCERY_LIST_RELATION_TO_ITEMS'",
        " '"+ COLUMN_GROCERY_LIST_RELATION_TO_ITEM_GL_ID +"' =? '"+ listNameId +"'  AND  '"
                +COLUMN_GROCERY_LIST_RELATION_TO_ITEM_ITEM_ID + "' =? '"+ itemID + "'   ",null);
        db.close();

    }

    // This method should be called if the user wants to set the quantity back to default, zero.
    // A scenario for this  query could be, if we want to set quantity to zero after un checking all items in a list.
    public  void setQuantityToDefault ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GROCERY_LIST_RELATION_TO_ITEM_QTY, 0);
        db.update("GROCERY_LIST_RELATION_TO_ITEMS", cv, null, null);
    }

    // Method should be use to check if there are similar items, it has to be used
    // in conjunction with other two methods. If true either use method to return
    // similar matches or add item and type.

    public boolean isThereMathces(String itemName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT '" + COlUMN_ITEM_LIST_ITEM_NAME +"'FROM ITEM_LIST"
                + "WHERE '"+ COlUMN_ITEM_LIST_ITEM_NAME  +"' =? '"+ itemName + "'", new String []{"%" + itemName + "%"});


        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // If isThereMatches true call this method
    public ArrayList<String> getSimilarMatches (String itemName)
    {
        ArrayList<String> similar = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT '" + COlUMN_ITEM_LIST_ITEM_NAME +"'FROM ITEM_LIST"
                + "WHERE '"+ COlUMN_ITEM_LIST_ITEM_NAME  +"' =? '"+ itemName + "'", new String []{"%" + itemName + "%"});



        while(cursor.moveToNext())
        {
            similar.add(cursor.getString(cursor.getColumnIndex(COlUMN_ITEM_LIST_ITEM_NAME)));
        }

        return similar;
    }

    // Method returns a list of Item Types, should be used if isThereMatches is false, select type from this list
    // before adding the item. Then call addToItemList method.
    public ArrayList<String> getTypes()
    {
        ArrayList<String> types = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT '"+ COLUMN_ITEM_TYPE +"' FROM ITEM_TYPE", null);

        if (cursor.moveToFirst())
        {
            do {
                types.add(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_TYPE )));
            } while (cursor.moveToNext());
        }

        return types;
    }

    public ArrayList<String> getItems()
    {
        ArrayList<String> items = new ArrayList<>();

        return items;
    }

    // Add an Item to the Item List table
    void addToItemList(String itemName, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COlUMN_ITEM_LIST_ITEM_NAME , itemName);
        cv.put(COlUMN_ITEM_LIST_TYPE_ID, getItemTypeId(type));
        db.insert("ITEM_LIST", null, cv);
        db.close();
    }



    // Sub static Class that holds data, in order to keep GLM cleaner.
    public static class DatabaseHelper
    {

        public String []itemType = new String []{"Fresh Produce", "Meat", "Dairy Products",
        "Spices & Seasoning", "Canned Food & pasta", "Cleaning Supplies", "Bakery", "Toilet Trees","Frozen Food" +
                "Health Beauty", "Beverages"};

        public String []itemNameFP = new String [] {"Bananas", "Blueberry", "Grape", "Kiwi",
        "Blackberry", "Raspberry", "Strawberry", "Pineapple", "Apple","Cucumber", "Lettuce",
        "Lemon", "Lime","Orange", "Spinach", "Better", "Broccoli", "Cabbage", "Carrot",
        "Celery", "Corn", "Eggplant", "Ginger", "Onion", "Pepper", "Potato",};

        public String []itemNameMeat = new String [] {"Bacon", "Ham", "Beef", "Lamb", "Chicken", "Turkey",
        "Duck"};

        public String [] spicesSeasoning = new String [] {"Cajun Season", "Basil", "Garlic Her & Seasoing",
        "Steak Seasoning", "Salt", "Oregano", "Onion Powder", "Mixed Spices", "Pepper", "Cayenne Pepper",
        "Ground Cinnamon", "Cinnamon Sticks", "Cloves", "Cummin", "Curry", "Nutmeg"};

        public String [] dairyProducts = new String [] {"Milk", "Yogurt", "Almond Milk", "Coconut Milk",
        "Sour Cream", "Greek Yogurt", "American Cheese", "Cheddar Cheese","Blue Cheese", "Whipped Butter", "Butter Sticks",
        "Monterey Jack Cheese"};

        public String [] CannedFood = new String [] {"Tuna"};
    }



}


