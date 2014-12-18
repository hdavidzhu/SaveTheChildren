package com.hdavidzhu.savethechildren;

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

    public String getTitle() {
        return title;
    }

    public String getCounter() {
        return counter;
    }
}
