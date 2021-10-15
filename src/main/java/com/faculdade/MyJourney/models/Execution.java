package com.faculdade.MyJourney.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Execution {

    public Execution(LocalTime begin, LocalTime end, LocalDate date, Activity activity) {
        this.begin = begin;
        this.end = end;
        this.date = date;
        this.activity = activity;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalTime begin;
    @NotNull
    private LocalTime end;
    private LocalDate date;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Activity activity;

    public Long durationInMinutes() {
        return Duration.between(begin, end).toMinutes();
    }
}
