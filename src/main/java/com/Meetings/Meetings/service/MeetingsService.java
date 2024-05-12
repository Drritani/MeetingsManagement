package com.Meetings.Meetings.service;

import com.Meetings.Meetings.dto.MeetingsDTO;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface MeetingsService {
    MeetingsDTO addMeeting(@Valid MeetingsDTO MeetingsDTO );
    MeetingsDTO findMeetingById(Integer id);
    List<MeetingsDTO> findAllMeetings(LocalDate date);
    void deleteMeeting(Integer id);

}
