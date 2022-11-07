package com.example.meetup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class MeetupDto {

    private Long id;

    @NotBlank(message = "topic can not be empty")
    @Size(max = 100, message = "topic should not exceed 100 characters")
    private String topic;

    @NotBlank(message = "description can not be empty")
    @Size(max = 500, message = "description should not exceed 500 characters")
    private String description;

    @NotBlank(message = "organizer can not be empty")
    @Size(max = 255, message = "organizer should not exceed 255 characters")
    private String organizer;

    @NotNull(message = "scheduledTime can not be empty")
    private LocalDateTime scheduledTime;

    @NotBlank(message = "location can not be empty")
    @Size(max = 255, message = "location should not exceed 255 characters")
    private String location;

}
