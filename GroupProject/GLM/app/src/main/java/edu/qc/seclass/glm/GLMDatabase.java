package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GLMDatabase extends SQLiteOpenHelper
{
    private static GLMDatabase glmdb;
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

    public static synchronized GLMDatabase getInstance(Context context){
        if(glmdb == null){
            glmdb = new GLMDatabase(context.getApplicationContext());
        }
        return glmdb;
    }

    private GLMDatabase(Context context) {
        super(context, GROCERY_LIST, null, 1);
    }

    String[] itemTypes = new String[]{"Beverages", "Bread/Bakery", "Canned/Jarred Goods", "Dairy", "Dry/Baking Goods", "Frozen Foods", "Meat", "Produce", "Cleaners", "Paper Goods", "Personal Care", "Other"};
    String[] Beverages = new String[]{"coffee", "tea", "soda"};
    String[] Bread_Bakery = new String[]{"sandwich loaves", "dinner rools", "tortillas", "bagels"};
    String[] Canned_Jarred_Goods = new String[]{"vegetables", "spaghetti sauce", "ketchup"};
    String[] Dairy = new String[]{"cheeses", "eggs", "milk", "yogurt", "butter"};
    String[] Dry_Baking_Goods = new String[]{"cereals", "flour", "sugar", "pasta", "mixes"};
    String[] Frozen_Foods = new String[]{"waffles", "vegetables", "individual meals", "ice cream"};
    String[] Meat = new String[]{"lunch meat", "poultry", "beef", "pork"};
    String[] Produce = new String[]{"fruits", "vegetables"};
    String[] Cleaners = new String[]{"all- purpose", "laundry detergent", "dishwashing liquid", "detergent"};
    String[] Paper_Goods = new String[]{"paper towels", "toilet paper", "aluminum foil", "sandwich bags"};
    String[] Personal_Care = new String[]{"shampoo", "soap", "hand soap", "shaving cream"};
    String[] Other = new String[]{"baby items", "pet items", "batteries", "greeting cards"};
    String[][] items = new String[][]{Beverages, Bread_Bakery, Canned_Jarred_Goods, Dairy, Dry_Baking_Goods, Frozen_Foods, Meat, Produce, Cleaners, Paper_Goods, Personal_Care, Other};


    String CREATE_GROCERY_LIST_TABLE = "CREATE TABLE GROCERY_LIST ("
            + "ListID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "GroceryListName VARCHAR(60) NOT NULL)";

    String CREATE_ITEM_TYPE_TABLE = "CREATE TABLE ITEM_TYPE ("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "type VARCHAR(50) NOT NULL)";

    String CREATE_ITEM_LIST_TABLE = "CREATE TABLE ITEM_LIST ("
            + "ItemID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "itemName VARCHAR(50) NOT NULL, typeID INTEGER NOT NULL, FOREIGN KEY (typeID) REFERENCES Item_Type(typeID) ON DELETE CASCADE)";


    String CREATE_GROCERY_LIST_RELATION_TO_ITEMS = "CREATE TABLE GROCERY_LIST_RELATION_TO_ITEMS ("
            + "ListID INTEGER NOT NULL,"
            + "ItemID INTEGER NOT NULL,"
            + "Qty DOUBLE NOT NULL DEFAULT 0.0,"
            + "isSelected INTEGER DEFAULT 0,"
            + "PRIMARY KEY(ListID,ItemID)," // Composite Key
            + "FOREIGN KEY(ListID) REFERENCES GROCERY_LIST (ListID) ON DELETE CASCADE,"
            + "FOREIGN KEY(ItemID) REFERENCES Item_List(ItemID) ON DELETE CASCADE)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TYPE_TABLE);
        db.execSQL(CREATE_ITEM_LIST_TABLE);
        db.execSQL(CREATE_GROCERY_LIST_TABLE);
        db.execSQL(CREATE_GROCERY_LIST_RELATION_TO_ITEMS);
        insertTypes(db);
        insertItems(db);
        Log.e("Database operations ","Tables  Created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertTypes(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        for(String i:itemTypes){
            values.put("type", i);
            db.insert("ITEM_TYPE", null, values);
        }
    }

    public String getListName(int listID){
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT * FROM GROCERY_LIST WHERE ListID = " + listID + ";";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            name = cursor.getString(1);
        cursor.close();
        return name;
    }

    private void insertItems(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        int tid = -1;
        for(int i=0;i<itemTypes.length;++i){
            for(String j:items[i]){
                tid = getTypeID(itemTypes[i], db);
                values.put("itemName", j);
                values.put("typeID", tid);
                db.insert("ITEM_LIST", null, values);
            }
        }
    }

    public void insertItem(Item_list item){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("itemName", item.getName());
        values.put("typeID", getTypeID(item.getType(), db));
        db.insert("ITEM_LIST", null, values);
    }

    public ArrayList<Grocery_list> getList(){
        ArrayList<Grocery_list> glist = new ArrayList<>();
        String q = "SELECT * FROM GROCERY_LIST";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()){
            do{
                Grocery_list gl = new Grocery_list(cursor.getString(1), false, cursor.getInt(0));
                glist.add(gl);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return glist;
    }

    int addList(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("GroceryListName", name);
        return (int)db.insert("GROCERY_LIST", null, values);
    }

    void renameList(long glID, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("GroceryListName", newName);
        db.update("GROCERY_LIST", values, "ListID ="+glID, null);
    }

    void removeList(long glID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("GROCERY_LIST_RELATION_TO_ITEMS", "ListID = " + glID, null);
        db.delete("GROCERY_LIST", "ListID = '" + glID + "'", null);
    }

    ArrayList<String> getItemList(){
        ArrayList<String> s = new ArrayList<>();
        String i = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM GROCERY_LIST_RELATION_TO_ITEMS";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()) {
            do {
                i = cursor.getInt(0) + ", " + cursor.getInt(1) + ", " + cursor.getDouble(2) + ", " + cursor.getInt(3);
                s.add(i);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return s;
    }

    int getListID(String name, SQLiteDatabase db){
        int id = -1;
        String q = "SELECT * FROM GROCERY_LIST WHERE GroceryListName = '" + name + "';";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    int getListID(String name){
        int id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM GROCERY_LIST WHERE GroceryListName = '" + name + "';";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    int getItemID(String name){
        int id = -1;
        String q = "SELECT * FROM ITEM_LIST WHERE itemName = '" + name + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    int getItemID(String name, SQLiteDatabase db){
        int id;
        String q = "SELECT * FROM ITEM_LIST WHERE itemName = '" + name + "';";
        Cursor cursor = db.rawQuery(q, null);
        cursor.moveToFirst();
        id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    void setSelected(int listID, Item_list item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(item.isSelected()){
            values.put("isSelected", 1);
        }else{
            values.put("isSelected", 0);
        }
        db.update("GROCERY_LIST_RELATION_TO_ITEMS", values, "ListID = " + listID + " AND ItemID = " +  getItemID(item.getName(), db), null);
    }

    public ArrayList<String> getTypes(){
        return new ArrayList<>(Arrays.asList(itemTypes));
    }

    public int getTypeID(String t){
        int id = -1;
        String q = "SELECT * FROM ITEM_TYPE WHERE type = '" + t + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    public int getTypeID(String t, SQLiteDatabase db){
        int id = -1;
        String q = "SELECT * FROM ITEM_TYPE WHERE type = '" + t + "';";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    ArrayList<String> getItemsByType(String type){
        ArrayList<String> itemL = new ArrayList<>();
        String item;
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM ITEM_LIST WHERE typeID = " + getTypeID(type, db);
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()){
            do{
                item = cursor.getString(1);
                itemL.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return itemL;
    }

    void addItem(Item_list item, int listID){
        double qua;
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM GROCERY_LIST_RELATION_TO_ITEMS WHERE ListID = " + listID + " AND ItemID = " + getItemID(item.getName(), db) + ";";
        Cursor cursor = db.rawQuery(q, null);
        ContentValues values = new ContentValues();
        if(cursor.moveToFirst()){
            qua = cursor.getDouble(2) + Double.parseDouble(item.getQuant());
            values.put("Qty", qua);
            db.update("GROCERY_LIST_RELATION_TO_ITEMS", values, "ListID = " + listID + " AND ItemID = " +  getItemID(item.getName(), db), null);
        }else {
            values.put("ListID", listID);
            values.put("ItemID", getItemID(item.getName(), db));
            values.put("Qty", item.getQuant());
            db.insert("GROCERY_LIST_RELATION_TO_ITEMS", null, values);
        }
        cursor.close();
    }

    public ArrayList<Item_list> getItemList(int listID){
        ArrayList<Item_list> itemL = new ArrayList<>();
        Item_list item;
        SQLiteDatabase db = this.getWritableDatabase();
        int n;
        String name;
        double quan;
        int s;
        String q = "SELECT * FROM GROCERY_LIST_RELATION_TO_ITEMS WHERE ListID = " + listID;
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()){
            do{
                n = cursor.getInt(1);
                quan = cursor.getDouble(2);
                s = cursor.getInt(3);
                name = getItemName(n, db);
                item = s <= 0 ? new Item_list(name, getItemType(n, db), quan, false):new Item_list(name, getItemType(n, db), quan, true);
                itemL.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return itemL;
    }

    String getItemName(int itemID, SQLiteDatabase db){
        String name = "";
        String q = "SELECT * FROM ITEM_LIST WHERE ItemID = " + itemID ;
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            name = cursor.getString(1);
        cursor.close();
        return name;
    }

    String getItemType(int itemID){
        SQLiteDatabase db = this.getWritableDatabase();
        String type = "";
        String q = "SELECT ITEM_TYPE.*, ITEM_LIST.* FROM ITEM_TYPE, ITEM_LIST WHERE ITEM_LIST.ItemID = " + itemID + " AND ITEM_LIST.typeID = " + "ITEM_TYPE.ID";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            type = cursor.getString(1);
        cursor.close();
        return type;
    }

    String getItemType(int itemID, SQLiteDatabase db){
        String type = "";
        String q = "SELECT ITEM_TYPE.*, ITEM_LIST.* FROM ITEM_TYPE, ITEM_LIST WHERE ITEM_LIST.ItemID = " + itemID + " AND ITEM_LIST.typeID = " + "ITEM_TYPE.ID";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
            type = cursor.getString(1);
        cursor.close();
        return type;
    }

    ArrayList<String> getSimilarItem(String text){
        ArrayList<String> iList = new ArrayList<>();
        String name;
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM ITEM_LIST WHERE itemName LIKE '%%" + text + "%%';";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()){
            do{
                name = cursor.getString(1);
                iList.add(name);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return iList;
    }

    ArrayList<String> getItemType(String item){
        String type = "";
        ArrayList<String> types = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT ITEM_TYPE.*, ITEM_LIST.* FROM ITEM_TYPE, ITEM_LIST WHERE itemName = '" + item + "' AND typeID = ID";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()) {
            do{
                type = cursor.getString(1);
                types.add(type);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return types;
    }

    void deleteItem(Item_list item, int glID){
        int itemID = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM ITEM_LIST WHERE itemName = '" + item.getName() + "' AND typeID =" + getTypeID(item.getType()) + ";";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()){
            itemID = cursor.getInt(0);
        }
        cursor.close();
        db.delete("GROCERY_LIST_RELATION_TO_ITEMS", "ListID = " + glID + " AND ItemID = " + itemID, null);
    }

    void editListItem(Item_list item, int glID){
        int itemID = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String q = "SELECT * FROM ITEM_LIST WHERE itemName = '" + item.getName() + "' AND typeID =" + getTypeID(item.getType()) + ";";
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst()){
            itemID = cursor.getInt(0);
        }
        cursor.close();
        values.put("Qty", Double.parseDouble(item.getQuant()));
        db.update("GROCERY_LIST_RELATION_TO_ITEMS", values,"ListID = " + glID + " AND ItemID = " + itemID, null);
    }
}