package org.example.blog1.models;

public enum Category {
    JAVA("Java"),
    SPORT("Sport"),
    HEALTH("Health"),
    PETS("Pets"),
    STYLE("Style"),
    MUSIC("Music"),
    OTHER("Other");

    private final String title;

    Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Category fromTitle(String title) {
        for (Category category : values()) {
            if (category.title.equalsIgnoreCase(title)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + title);
    }
}
