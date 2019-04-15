package edu.qc.seclass.glm;

import java.util.ArrayList;

public class Grocery_list {

    private GLMDatabase db;
    private String name;
    private boolean isSelected;
    public ArrayList<Item_list> itemList;
    private int listID;
    //DatabaseStore dbs = DatabaseStore.getInstance(getApplicationContext());

    public Grocery_list(String name, boolean isSelected, int id) {
        this.name = name;
        this.isSelected = isSelected;
        this.itemList = new ArrayList<>();
        db = GLMDatabase.getInstance(MainActivity.context);
        listID = id;
    }

/*    public String[] getItemTypes() {

        return null;
    }

    public CheckListItem[] getItemsByType(String type) {
        CheckListItem[] retArray = new CheckListItem[0];
        ArrayList<CheckListItem> retList = new ArrayList<CheckListItem>();
        for (CheckListItem checkItem:checkListItemList) {
            if (checkItem.getType().equals(type)) {
                retList.add(checkItem);
            }
        }
        return retList.toArray(retArray);
    }*/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
    public int getID(){return listID;}
    /* // Copied to DBStore
    public ArrayList<Item> similarItem(String name)
    {
        ArrayList<Item> similarItemList = new ArrayList<Item>();
        ArrayList<Item> items = dbs.getItemsByName();
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getName().contains(name))
                similarItemList.add(items.get(i));
        }
        return similarItemList;
    }
    */

    public void addItem(Item_list newItem) {
        boolean itemMatched = false;
        if (itemList == null) itemList = new ArrayList<Item_list>();
        for (Item_list item : itemList) {
            if (item.getName().equals(newItem.getName()) && item.getType().equals(newItem.getType())) {
                double qty = Double.parseDouble(item.getQuant()) + Double.parseDouble(newItem.getQuant());
                item.setQuant(qty);
                itemMatched = true;
            }
        }
        if (!itemMatched) itemList.add(newItem);
    }
}