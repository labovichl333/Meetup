package com.example.meetup.service.impl;

import com.example.meetup.dao.MeetupDao;
import com.example.meetup.dto.MeetupDto;
import com.example.meetup.entity.Meetup;
import com.example.meetup.exception.MeetupNotFoundException;
import com.example.meetup.mapper.MeetupMapper;
import com.example.meetup.service.MeetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetupServiceImpl implements MeetupService {

    private final MeetupDao meetupDao;

    private final MeetupMapper meetupMapper;

    @Override
    public List<MeetupDto> findAll( Map<String, String> params) {
        List<Meetup> meetups;
        if (params == null || params.isEmpty()) {
            meetups = meetupDao.findAll();
        } else {
            meetups = meetupDao.findAll(params);
        }
        return meetups.stream()
                .map(meetupMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MeetupDto findById(Long id) {
        Meetup meetup = meetupDao.findById(id)
                .orElseThrow(() -> new MeetupNotFoundException("Meetup with id: " + id + " wasn't found"));
        return meetupMapper.mapEntityToDto(meetup);
    }

    @Override
    public MeetupDto save(MeetupDto meetupDto) {
        Meetup meetup = meetupMapper.mapDtoToEntity(meetupDto);
        Meetup resultMeetup = meetupDao.save(meetup);
        return meetupMapper.mapEntityToDto(resultMeetup);
    }

    @Override
    public void update(Long id, MeetupDto meetupDto) {
        meetupDao.findById(id).
                orElseThrow(() -> new MeetupNotFoundException("Meetup with id: " + id + " wasn't found to update"));
        Meetup meetup = meetupMapper.mapDtoToEntity(meetupDto);
        meetup.setId(id);
        meetupDao.update(meetup);
    }

    @Override
    public void delete(Long id) {
        meetupDao.findById(id).
                orElseThrow(() -> new MeetupNotFoundException("Meetup with id: " + id + " wasn't found to delete"));
        meetupDao.delete(id);
    }
}
