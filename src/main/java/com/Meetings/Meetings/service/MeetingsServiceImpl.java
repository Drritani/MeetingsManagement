package com.Meetings.Meetings.service;

import com.Meetings.Meetings.entity.Meetings;
import com.Meetings.Meetings.dto.MeetingsDTO;
import com.Meetings.Meetings.exceptions.CustomException;
import com.Meetings.Meetings.repository.MeetingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.Meetings.Meetings.mapper.MeetingsMapper.*;

@Service
public class MeetingsServiceImpl implements MeetingsService {
    @Autowired
    private MeetingsRepository meetingsRepository;

    @Override
    public MeetingsDTO addMeeting(MeetingsDTO meetingsDTO) {
        boolean canRegist = false;
        List<Meetings> existingMeetings=meetingsRepository.findAllByLocalDate(meetingsDTO.getData());
        for(Meetings meeting: existingMeetings){
            if (!((meetingsDTO.getOra_fillimit().isBefore(meeting.getOra_fillimit()) && meetingsDTO.getOra_perfundimit().isBefore(meeting.getOra_perfundimit()))
                    || (meetingsDTO.getOra_fillimit().isAfter(meeting.getOra_fillimit()) && meetingsDTO.getOra_perfundimit().isAfter(meeting.getOra_perfundimit())))) {
                canRegist = true;
            }
        }
            if (canRegist==false){
            Meetings newMeeting = Meetings.builder()
                    .titulli(meetingsDTO.getTitulli())
                    .data(meetingsDTO.getData())
                    .ora_fillimit(meetingsDTO.getOra_fillimit())
                    .ora_perfundimit(meetingsDTO.getOra_perfundimit())
                    .build();

            meetingsRepository.save(newMeeting);
            return MEETING_MAPPER.toDto(newMeeting);
            }
            else throw new CustomException("You can not add a meeting during this time period");

    }

    @Override
    public MeetingsDTO findMeetingById(Integer id) {
        Meetings meeting = meetingsRepository.findById(id).orElseThrow(()->new CustomException("No meetings with id: " + id + " found"));
        return MEETING_MAPPER.toDto(meeting);
    }

    @Override
    public List<MeetingsDTO> findAllMeetings(LocalDate data) {
        List<Meetings> meetingEntities= meetingsRepository.findAllByLocalDate(data);
        return meetingEntities.stream().map(MEETING_MAPPER::toDto).collect(Collectors.toList()) ;
    }

    @Override
    public void deleteMeeting(Integer id) {
        Meetings meeting = meetingsRepository.findById(id).orElseThrow(()->new CustomException("No meetings with id: " + id + " found"));
        meetingsRepository.delete(meeting);
    }
}
