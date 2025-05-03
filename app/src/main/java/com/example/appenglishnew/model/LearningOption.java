package com.example.appenglishnew.model;

public class LearningOption {
    private final String title;
    private final int iconResId;

    public LearningOption(String title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public int getIconResId() {
        return iconResId;
    }
}
