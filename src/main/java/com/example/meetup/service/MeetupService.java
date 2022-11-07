package com.example.meetup.service;

import com.example.meetup.dto.MeetupDto;

import java.util.List;
import java.util.Map;

public interface MeetupService {

    List<MeetupDto> findAll(Map<String, String> params);


    MeetupDto findById(Long id);

    MeetupDto save(MeetupDto meetupDto);

    void update(Long id, MeetupDto meetupDto);

    void delete(Long id);
}
