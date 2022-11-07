package com.example.meetup.dao.util.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {
    ASC("asc"),
    DESC("desc");

    private final String name;

    public static SortType getByName(String value) {
        for (SortType type : SortType.values()) {
            if (type.getName().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Wrong sort value " + value);
    }
}
