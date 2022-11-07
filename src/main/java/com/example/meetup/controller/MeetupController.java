package com.example.meetup.controller;

import com.example.meetup.dto.MeetupDto;
import com.example.meetup.service.MeetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/meetups")
@RequiredArgsConstructor
public class MeetupController {

    private final MeetupService meetupService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public final List<MeetupDto> findAll(Map<String, String> params) {
        return meetupService.findAll(params);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetupDto getById(@PathVariable("id") Long id) {
        return meetupService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MeetupDto save(@RequestBody @Valid MeetupDto dto) {
        return meetupService.save(dto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody @Valid MeetupDto dto) {
        meetupService.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        meetupService.delete(id);
    }
}
