package com.example.meetup.servisce.impl;

import com.example.meetup.dao.MeetupDao;
import com.example.meetup.dto.MeetupDto;
import com.example.meetup.entity.Meetup;
import com.example.meetup.exception.MeetupNotFoundException;
import com.example.meetup.mapper.MeetupMapper;
import com.example.meetup.service.impl.MeetupServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeetupServiceTest {

    @Mock
    MeetupDao meetupDao;

    @Mock
    MeetupMapper meetupMapper;

    @InjectMocks
    MeetupServiceImpl meetupServiceImpl;

    @Test
    void findAllMeetups() {
        Meetup meetup = new Meetup();
        List<Meetup> meetupList = List.of(meetup);

        when(meetupDao.findAll()).thenReturn(meetupList);
        when(meetupMapper.mapEntityToDto(meetup)).thenReturn(MeetupDto.builder().build());

        assertEquals(1, meetupServiceImpl.findAll(new HashMap<>()).size());
    }

    @Test
    void findMeetupById() {
        Long id = 1L;
        Meetup meetup = new Meetup();
        meetup.setId(id);
        MeetupDto meetupDto = MeetupDto
                .builder()
                .id(id)
                .build();

        when(meetupDao.findById(id)).thenReturn(Optional.of(meetup));
        when(meetupMapper.mapEntityToDto(meetup)).thenReturn(meetupDto);

        assertEquals(meetup.getId(), meetupServiceImpl.findById(id).getId());

    }

    @Test
    void findMeetupByIncorrectId() {
        Long id = 1L;

        when(meetupDao.findById((id))).thenReturn(Optional.empty());

        assertThrows(MeetupNotFoundException.class, () -> meetupServiceImpl.findById(id));
    }

    @Test
    void deleteMeetupById() {
        Long id = 1L;
        Meetup meetup = new Meetup();
        meetup.setId(id);

        when(meetupDao.findById(id)).thenReturn(Optional.of(meetup));

        meetupServiceImpl.delete(id);

        verify(meetupDao).findById(id);
        verify(meetupDao).delete(id);

    }

    @Test
    void deleteMeetupByIncorrectId() {
        Long id = 1L;

        assertThrows(MeetupNotFoundException.class, () -> meetupServiceImpl.delete(id));
    }

    @Test
    void updateMeetupById() {
        Long id = 1L;
        Meetup meetup = new Meetup();
        meetup.setId(id);
        MeetupDto meetupDto = MeetupDto
                .builder()
                .id(id)
                .build();

        when(meetupDao.findById(id)).thenReturn(Optional.of(meetup));
        when(meetupMapper.mapDtoToEntity(meetupDto)).thenReturn(meetup);

        meetupServiceImpl.update(id, meetupDto);

        verify(meetupDao).findById(id);
        verify(meetupDao).update(meetup);

    }

    @Test
    void updateMeetupByIncorrectId() {
        Long id = 1L;
        MeetupDto meetupDto = MeetupDto
                .builder()
                .build();

        assertThrows(MeetupNotFoundException.class, () -> meetupServiceImpl.update(id, meetupDto));
    }

    @Test
    void saveMeetup() {
        Long id = 1L;
        Meetup meetup = new Meetup();
        meetup.setId(id);
        MeetupDto meetupDto = MeetupDto
                .builder()
                .id(id)
                .build();

        when(meetupMapper.mapDtoToEntity(meetupDto)).thenReturn(meetup);

        meetupServiceImpl.save(meetupDto);

        verify(meetupDao).save(meetup);

    }

    @Test
    void findFilteredAndSortedMeetups() {
        Map<String, String> params = new LinkedHashMap<>();

        params.put("sort", "scheduledTime:asc,topic:desc");
        params.put("topic", "some topic");
        params.put("organizer", "some organizer");

        meetupServiceImpl.findAll(params);

        verify(meetupDao).findAll(params);
    }

}