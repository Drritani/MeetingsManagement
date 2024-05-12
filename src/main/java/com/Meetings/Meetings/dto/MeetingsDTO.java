package com.Meetings.Meetings.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingsDTO {

    @NotNull
    private Integer id;
    private String titulli;
    @NotNull
    private LocalDate data;
    @NotNull
    private LocalTime ora_fillimit;
    @NotNull
    private LocalTime ora_perfundimit;
}
