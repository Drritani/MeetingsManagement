package com.Meetings.Meetings.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meetings")
public class Meetings {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulli")
    private String titulli;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "ora_fillimit")
    private LocalTime ora_fillimit;

    @Column(name = "ora_perfundimit")
    private LocalTime ora_perfundimit;

}
