package com.example.meetup.dao.util.sort;

import com.example.meetup.entity.Meetup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MeetupSortOrderProvider {
    private final CriteriaBuilder criteriaBuilder;
    private final Root<Meetup> root;

    public Order[] getOrders(List<SortParam> sortParams) {
        List<Order> orders = new ArrayList<>();
        sortParams.forEach(sortParam ->
                orders.add(getOrder(sortParam.getSortField(), sortParam.getSortType())));
        return orders.toArray(new Order[0]);
    }

    private Order getOrder(SortField field, SortType sortType) {
        if (SortType.ASC.equals(sortType)) {
            return criteriaBuilder.asc(root.get(field.getName()));
        }
        return criteriaBuilder.desc(root.get(field.getName()));
    }

}
