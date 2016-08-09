package com.example.krunoslavtill.imageapplication.Models.Models.Threads;

import android.content.Context;

import com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels.Homenews;

import java.util.List;

/**
 * Created by krunoslavtill on 02/08/16.
 */
public class ImageThreadParameters {
    private List<Homenews> homenewsList;
    private boolean threadFlag = false;
    private int width;
    Context appContext;



    public List<Homenews> getHomenewsList() {
        return homenewsList;
    }

    public void setHomenewsList(List<Homenews> homenewsList) {
        this.homenewsList = homenewsList;
    }

    public boolean isThreadFlag() {
        return threadFlag;
    }

    public void setThreadFlag(boolean threadFlag) {
        this.threadFlag = threadFlag;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }
}
