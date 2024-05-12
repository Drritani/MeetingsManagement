package com.Meetings.Meetings.repository;

import com.Meetings.Meetings.entity.Meetings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MeetingsRepository extends JpaRepository <Meetings, Integer> {

    @Query("SELECT m FROM Meetings m where m.data=:data ")
    List<Meetings> findAllByLocalDate(LocalDate data);
}
