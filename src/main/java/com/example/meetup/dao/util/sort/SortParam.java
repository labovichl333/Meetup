package com.example.meetup.dao.util.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SortParam {
    private final SortField sortField;
    private final SortType sortType;

}
