package com.example.meetup.dao.impl;

import com.example.meetup.dao.MeetupDao;
import com.example.meetup.dao.util.filter.MeetupFilterPredicateProvider;
import com.example.meetup.dao.util.sort.MeetupSortOrderProvider;
import com.example.meetup.dao.util.sort.SortParamConverter;
import com.example.meetup.entity.Meetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MeetupDaoImpl implements MeetupDao {

    private final SessionFactory sessionFactory;

    private final ObjectMapper objectMapper;

    @Override
    public List<Meetup> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("SELECT m FROM Meetup m", Meetup.class).getResultList();
    }

    @Override
    public List<Meetup> findAll(Map<String, String> params) {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<Meetup> criteriaQuery = criteriaBuilder.createQuery(Meetup.class);
        Root<Meetup> root = criteriaQuery.from(Meetup.class);

        MeetupFilterPredicateProvider meetupFilterPredicateProvider =
                new MeetupFilterPredicateProvider(root, criteriaBuilder, objectMapper);
        criteriaQuery.where(meetupFilterPredicateProvider.getFilterPredicate(params));

        MeetupSortOrderProvider meetupSortOrderProvider = new MeetupSortOrderProvider(criteriaBuilder, root);
        SortParamConverter sortParamConverter = new SortParamConverter();
        criteriaQuery.orderBy(meetupSortOrderProvider.getOrders(sortParamConverter.convertToSortParam(params)));
        return currentSession.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<Meetup> findById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Optional<Meetup> meetup = currentSession.byId(Meetup.class).loadOptional(id);
        return meetup;
    }


    @Override
    public Meetup save(Meetup meetup) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(meetup);
        return meetup;
    }

    @Override
    public void update(Meetup meetup) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(meetup);
    }

    @Override
    public void delete(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Meetup meetupToDelete = findById(id).get();
        currentSession.delete(meetupToDelete);
    }
}
