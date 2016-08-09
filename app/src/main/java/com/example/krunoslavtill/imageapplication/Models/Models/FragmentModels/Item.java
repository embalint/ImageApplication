package com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels;

/**
 * Created by krunoslavtill on 09/08/16.
 */
public abstract class Item {
    public static final int TYPE_HEADER=0;
    public static final int TYPE_ITEM=1;
    private  String itemTitle;

    public abstract int getTypeItem();

    public Item(String itemTitle){
        this.itemTitle=itemTitle;
    }

    public String getItemTitle() {
        return itemTitle;
    }
}
