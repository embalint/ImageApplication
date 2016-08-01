package com.example.krunoslavtill.imageapplication.Models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by krunoslavtill on 27/07/16.
 */


@Root(strict=false)
public class Homepage {

    @ElementList(name = "homeNews",inline = true)
    List<Homenews> homenewsList;

    public List<Homenews> getHomenewsList() {
        return homenewsList;
    }

    public void setHomenewsList(List<Homenews> homenewsList) {
        this.homenewsList = homenewsList;
    }
}

