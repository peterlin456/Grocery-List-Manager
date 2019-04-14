package edu.qc.seclass.glm;

public class Item_list implements Comparable<Item_list> {

    private String name;
    private String type;
    private double quant;
    private boolean isSelected;

    public Item_list(String n, String t, double q, boolean s)
    {
        name = n;
        type = t;
        quant = q;
        isSelected = s;
    }

    public Item_list(int i, String n, String t, double q, boolean s)
    {
        name = n;
        type = t;
        quant = q;
        isSelected = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuant(){
        return Double.toString(quant);
    }

    public void setQuant(double quant){
        this.quant = quant;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected){
        this.isSelected = selected;
    }

    @Override
    public int compareTo(Item_list anotherItem) {
        return this.getType().compareTo(anotherItem.getType());
    }
}
