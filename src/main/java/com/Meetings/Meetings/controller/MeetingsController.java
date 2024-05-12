package com.Meetings.Meetings.controller;

import com.Meetings.Meetings.dto.MeetingsDTO;
import com.Meetings.Meetings.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {
    @Autowired
    private MeetingsService meetingsService;

    @GetMapping("/{id}")
    public ResponseEntity<MeetingsDTO> findMeetingById(@PathVariable Integer id) {
        return ResponseEntity.ok(meetingsService.findMeetingById(id));
    }
    @GetMapping
    public ResponseEntity<List <MeetingsDTO>> findAllMeetings(LocalDate data){
        return ResponseEntity.ok(meetingsService.findAllMeetings(data));
    }

    @PostMapping
    public ResponseEntity<MeetingsDTO> addMeeting(@RequestBody MeetingsDTO meetingsDTO) {
        return ResponseEntity.ok(meetingsService.addMeeting(meetingsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable Integer id) {
        meetingsService.deleteMeeting(id);
        return ResponseEntity.ok().body("Meeting with id: " + id + " was successfully deleted");
    }
}
