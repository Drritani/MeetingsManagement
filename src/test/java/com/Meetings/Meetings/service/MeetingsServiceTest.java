package com.Meetings.Meetings.service;

import com.Meetings.Meetings.dto.MeetingsDTO;
import com.Meetings.Meetings.entity.Meetings;
import com.Meetings.Meetings.repository.MeetingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeetingsServiceTest {
    @Spy
    @InjectMocks
    MeetingsServiceImpl toTest;
    @Mock
    private MeetingsRepository meetingsRepository;

    @Test
    public void test_findMeetingById() {
        Meetings meetings = new Meetings();
        meetings.setId(1);
        meetings.setData(LocalDate.now());
        meetings.setOra_fillimit(LocalTime.now());
        meetings.setOra_perfundimit(LocalTime.now().plusHours(1));

        when(meetingsRepository.findById(anyInt())).thenReturn(Optional.of(meetings));

        MeetingsDTO result = toTest.findMeetingById(1);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(meetings.getId(), result.getId()),
                () -> assertEquals(meetings.getData(), result.getData()),
                () -> assertEquals(meetings.getOra_fillimit(), result.getOra_fillimit()),
                () -> assertEquals(meetings.getOra_perfundimit(), result.getOra_perfundimit())

        );
    }
    @Test
    void test_addMeeting() {

        MeetingsDTO newMeetingDTO = new MeetingsDTO();
        newMeetingDTO.setTitulli("New Meeting");
        newMeetingDTO.setData(LocalDate.now());
        newMeetingDTO.setOra_fillimit(LocalTime.of(9, 0));
        newMeetingDTO.setOra_perfundimit(LocalTime.of(10, 0));
        when(meetingsRepository.findAllByLocalDate(any(LocalDate.class))).thenReturn(new ArrayList<>());

        MeetingsDTO addedMeetingDTO = toTest.addMeeting(newMeetingDTO);

        assertNotNull(addedMeetingDTO);
        assertEquals(newMeetingDTO.getData(), addedMeetingDTO.getData());
        assertEquals(newMeetingDTO.getOra_fillimit(), addedMeetingDTO.getOra_fillimit());
        assertEquals(newMeetingDTO.getOra_perfundimit(), addedMeetingDTO.getOra_perfundimit());
    }
    @Test
    public void test_findAllMeetings() {
        LocalDate date = LocalDate.now();
        Meetings meeting1 = new Meetings();
        Meetings meeting2 = new Meetings();
        List<Meetings> mockMeetings = Arrays.asList(meeting1, meeting2);

        when(meetingsRepository.findAllByLocalDate(date)).thenReturn(mockMeetings);

        List<MeetingsDTO> result = toTest.findAllMeetings(date);

        assertEquals(2, result.size());

    }
    @Test
    public void testDeleteMeeting() {

        Integer id = 123;
        Meetings meeting = new Meetings();

        when(meetingsRepository.findById(id)).thenReturn(java.util.Optional.of(meeting));

        toTest.deleteMeeting(id);


    }
}
