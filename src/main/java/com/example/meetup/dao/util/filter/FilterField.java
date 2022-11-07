package com.example.meetup.dao.util.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterField {
    TOPIC("topic"),
    ORGANIZER("organizer"),
    START_TIME("startTime"),
    END_TIME("endTime");

    private final String name;

    public static FilterField getByName(String value) {
        for (FilterField field : FilterField.values()) {
            if (field.getName().equalsIgnoreCase(value)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Wrong filter value " + value);
    }

}
