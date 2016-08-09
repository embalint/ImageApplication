package com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels;

/**
 * Created by krunoslavtill on 09/08/16.
 */
public class HeaderItem extends Item {
    String position;
    public HeaderItem(String position) {
        this.position=position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int getTypeItem() {
        return TYPE_HEADER;
    }
}
