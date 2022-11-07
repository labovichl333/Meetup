package com.example.meetup.dao.util.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {
    TOPIC("topic"),
    ORGANIZER("organizer"),
    DATE("scheduledTime");

    private final String name;

    public static SortField getByName(String value) {
        for (SortField field : SortField.values()) {
            if (field.getName().equalsIgnoreCase(value)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Wrong sort value " + value);
    }

}
