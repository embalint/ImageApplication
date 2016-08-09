package com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels;

/**
 * Created by krunoslavtill on 09/08/16.
 */
public class ChildItem extends Item{
    private String itemTitle;
    public ChildItem(String itemTitle) {
        super(itemTitle);
    }

    @Override
    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    @Override
    public int getTypeItem() {
        return TYPE_ITEM;
    }
}
