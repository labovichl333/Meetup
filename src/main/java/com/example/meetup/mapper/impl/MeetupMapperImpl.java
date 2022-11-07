package com.example.meetup.mapper.impl;

import com.example.meetup.dto.MeetupDto;
import com.example.meetup.entity.Meetup;
import com.example.meetup.mapper.MeetupMapper;
import org.springframework.stereotype.Component;

@Component
public class MeetupMapperImpl implements MeetupMapper {

    @Override
    public MeetupDto mapEntityToDto(Meetup meetup) {
        return MeetupDto.builder()
                .id(meetup.getId())
                .topic(meetup.getTopic())
                .description(meetup.getDescription())
                .organizer(meetup.getOrganizer())
                .scheduledTime(meetup.getScheduledTime())
                .location(meetup.getLocation())
                .build();
    }

    @Override
    public Meetup mapDtoToEntity(MeetupDto meetupDto) {
        return Meetup.builder()
                .id(meetupDto.getId())
                .topic(meetupDto.getTopic())
                .description(meetupDto.getDescription())
                .organizer(meetupDto.getOrganizer())
                .scheduledTime(meetupDto.getScheduledTime())
                .location(meetupDto.getLocation())
                .build();
    }
}
