package com.example.meetup.dao.util.filter;

import com.example.meetup.entity.Meetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class MeetupFilterPredicateProvider {

    private static final String SCHEDULED_TIME_COLUMN_NAME = "scheduledTime";

    private static final String TOPIC_COLUMN_NAME = "topic";

    private static final String ORGANIZER_COLUMN_NAME = "organizer";

    private final Root<Meetup> root;

    private final CriteriaBuilder criteriaBuilder;

    private final ObjectMapper objectMapper;

    public Predicate getFilterPredicate(Map<String, String> params) {
        List<Predicate> predicates = new ArrayList<>();
        addDate(params, predicates);
        addOrganizer(params, predicates);
        addTopic(params, predicates);
        return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }


    private void addDate(Map<String, String> params, List<Predicate> predicates) {
        Optional<LocalDateTime> start = getDateFromParams(params, FilterField.START_TIME);
        Optional<LocalDateTime> end = getDateFromParams(params, FilterField.END_TIME);

        if (start.isPresent() && end.isPresent()) {
            predicates.add(criteriaBuilder.between(root.get(SCHEDULED_TIME_COLUMN_NAME), start.get(), end.get()));
        } else {
            start.ifPresent(localDateTime ->
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(SCHEDULED_TIME_COLUMN_NAME), localDateTime)));

            end.ifPresent(localDateTime ->
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(SCHEDULED_TIME_COLUMN_NAME), localDateTime)));
        }
    }

    private Optional<LocalDateTime> getDateFromParams(Map<String, String> params, FilterField key) {
        String stringDateTime = params.get(key.getName());
        if (!StringUtils.isBlank(stringDateTime)) {
            try {
                return Optional.of(objectMapper.convertValue(stringDateTime, LocalDateTime.class));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Wrong time format " + stringDateTime);
            }
        }
        return Optional.empty();
    }

    private void addTopic(Map<String, String> params, List<Predicate> predicates) {
        String topic = params.get(FilterField.TOPIC.getName());
        if (!StringUtils.isBlank(topic)) {
            predicates.add(toPredicate(TOPIC_COLUMN_NAME, topic));
        }
    }

    private void addOrganizer(Map<String, String> params, List<Predicate> predicates) {
        String organizer = params.get(FilterField.ORGANIZER.getName());
        if (!StringUtils.isBlank(organizer)) {
            predicates.add(toPredicate(ORGANIZER_COLUMN_NAME, organizer));
        }
    }

    private Predicate toPredicate(String key, String value) {
        return criteriaBuilder.like(criteriaBuilder.upper(root.get(key)), ("%" + value.toUpperCase() + "%"));
    }

}
