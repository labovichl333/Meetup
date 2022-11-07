package com.example.meetup.dao.util.sort;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SortParamConverter {
    private static final String KEY_VALUE_SEPARATOR = ":";
    private static final String EXPRESSION_SEPARATOR = ",";
    private static final String EXPRESSION_KEY = "sort";

    public List<SortParam> convertToSortParam(Map<String, String> params) {

        String expression = params.get(EXPRESSION_KEY);

        if (StringUtils.isBlank(expression)) {
            return new ArrayList<>();
        }

        ArrayList<SortParam> sortParams = new ArrayList<>();
        for (String exp : expression.split(EXPRESSION_SEPARATOR)) {
            String[] keyValue = exp.split(KEY_VALUE_SEPARATOR);
            SortField sortField = SortField.getByName(keyValue[0]);
            SortType sortType = SortType.getByName(keyValue[1]);
            sortParams.add(new SortParam(sortField, sortType));
        }
        return sortParams;
    }
}
