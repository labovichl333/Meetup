package com.example.meetup.dao;

import com.example.meetup.entity.Meetup;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MeetupDao {

    List<Meetup> findAll();

    List<Meetup> findAll(Map<String, String> params);

    Optional<Meetup> findById(Long id);

    Meetup save(Meetup meetup);

    void update(Meetup meetup);

    void delete(Long id);
}
