package com.example.meetup.mapper;

import com.example.meetup.dto.MeetupDto;
import com.example.meetup.entity.Meetup;

public interface MeetupMapper {

    MeetupDto mapEntityToDto(Meetup meetup);

    Meetup mapDtoToEntity(MeetupDto meetupDto);

}
