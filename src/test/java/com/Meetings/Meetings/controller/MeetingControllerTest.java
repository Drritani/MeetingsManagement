package com.Meetings.Meetings.controller;

import com.Meetings.Meetings.dto.MeetingsDTO;
import com.Meetings.Meetings.BaseTest;
import com.Meetings.Meetings.service.MeetingsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MeetingControllerTest extends BaseTest {

    @MockBean
    private MeetingsService meetingsService;

    @Test
    public void test_addMeeting() throws Exception {
        MeetingsDTO bookingDTO= MeetingsDTO.builder()

                .data(LocalDate.now())
                .ora_fillimit(LocalTime.now())
                .ora_perfundimit(LocalTime.now().plusHours(2))
                .build();
        doReturn(bookingDTO).when(meetingsService).addMeeting(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/meetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new MeetingsDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_findMeetingById() throws Exception {

        MeetingsDTO bookingDTO = new MeetingsDTO();
        doReturn(bookingDTO).when(meetingsService).findMeetingById(any());
        mockMvc.perform(MockMvcRequestBuilders.get("/meetings/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_findAllMeetings() throws Exception {
        List<MeetingsDTO> meetingsDTOList = new ArrayList<>();
        doReturn(meetingsDTOList).when(meetingsService).findAllMeetings(any());
        mockMvc.perform(MockMvcRequestBuilders.get("/meetings"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deleteMeeting() throws Exception {
        doNothing().when(meetingsService).deleteMeeting(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.delete("/meetings/1"))
                .andExpect(status().isOk());
    }

}
