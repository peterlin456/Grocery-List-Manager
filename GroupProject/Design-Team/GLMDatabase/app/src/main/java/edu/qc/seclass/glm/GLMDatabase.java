package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class GLMDatabase extends SQLiteOpenHelper
{
    public static final String GROCERY_LIST = "GroceryListApp";
//    // Columns for Grocery List Table
//    public static final String TABLE_GROCERY_LIST = "Grocery List";
//    public static final String COLUMN_GROCERY_LIST_ID = "ID";
//    public static final String COLUMN_GROCERY_LIST_NAME = "List Name";
//
//    // Columns for GroceryListRelationToItem
//    public static final String TABLE_GROCERY_LIST_RELATION_TO_ITEM = "Grocery List Relation ToItem";
//    public static final String COLUMN_GROCERY_LIST_RELATION_TO_ITEM_GL_ID = "Grocery List ID";
//    public static final String COLUMN_GROCERY_LIST_RELATION_TO_ITEM_ITEM_ID = "Item ID"
//    public static final String COLUMN_GROCERY_LIST_RELATION_TO_ITEM_QTY = "Quantity";



    String CREATE_GROCERY_LIST_TABLE = "CREATE TABLE GROCERY_LIST ("
            + "ListID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "GroceryListName VARCHAR(60) NOT NULL)";

    String CREATE_ITEM_TYPE_TABLE = "CREATE TABLE ITEM_TYPE ("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "type VARCHAR(50) NOT NULL)";

    String CREATE_ITEM_LIST_TABLE = "CREATE TABLE ITEM_LIST ("
            + "ItemID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "itemName VARCHAR(50) NOT NULL, typeID INTEGER NOT NULL, FOREIGN KEY (typeID) REFERENCES Item_Type(typeID))";


    String CREATE_GROCERY_LIST_RELATION_TO_ITEMS = "CREATE TABLE GROCERY_LIST_RELATION_TO_ITEMS ("
            + "GroceryListID INTEGER NOT NULL,"
            + "ItemID INTEGER NOT NULL,"
            + "Qty INT NOT NULL,"
            + "ischecked INTEGER DEFAULT 0,"
            + "PRIMARY KEY(GroceryListID,ItemID)," // Composite Key
            + "FOREIGN KEY(ListID) REFERENCES GROCERY_LIST (ListID),"
            + "FOREIGN KEY(ItemID) REFERENCES Item_List(ItemID))";


    public GLMDatabase(Context context) {
        super(context, GROCERY_LIST, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TYPE_TABLE);
        db.execSQL(CREATE_ITEM_LIST_TABLE);
        db.execSQL(CREATE_GROCERY_LIST_TABLE);
        db.execSQL(CREATE_GROCERY_LIST_RELATION_TO_ITEMS);

        Log.e("Database operations ","Tables  Created ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //SQL Query to add a type
    void addType(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("type", type);
        db.insert("Item_Type", null, values);

    }

    //SQL Query to add an Item to the DB
    void addItem(String itemName, String type){
        SQLiteDatabase db = this.getWritableDatabase();

//        ContentValues values = new ContentValues();
//        values.put("itemName", itemName);
//        values.put("typeID", getItemTypeID(type));
//        db.insert("Item_Type", null, values);

    }
}
