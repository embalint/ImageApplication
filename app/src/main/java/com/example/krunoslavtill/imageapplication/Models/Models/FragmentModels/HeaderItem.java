package com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels;

/**
 * Created by krunoslavtill on 09/08/16.
 */
public class HeaderItem extends Item {

    public HeaderItem(String itemTitle) {
        super(itemTitle);
    }

    @Override
    public int getTypeItem() {
        return TYPE_HEADER;
    }
}
