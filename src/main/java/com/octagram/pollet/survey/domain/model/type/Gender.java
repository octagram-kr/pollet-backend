package com.octagram.pollet.survey.domain.model.type;

public enum Gender {
    MALE("남자"),
    FEMALE("여자"),
    ANY("무관");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
