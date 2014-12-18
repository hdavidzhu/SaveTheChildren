package com.hdavidzhu.savethechildren;

/**
 * Created by pmc on 12/17/14.
 */

// This model class handles data for the custom list view for TNA
public class TNAModel {
    private String title;
    private String counter;

    public boolean isGroupHeader = false;

    public TNAModel(String title) {
        this(title,null);
        isGroupHeader = true;
    }
    public TNAModel(String title, String counter) {
        super();
        this.title = title;
        this.counter = counter;
    }

    //TODO: POssibly volley?
    public String getTitle() {
        return title;
    }

    public String getCounter() {
        return counter;
    }
}
