package com.faculdade.MyJourney.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Activity {

    public Activity(String name, int color) {
        this.name = name;
        this.color = color;
    }

    @Id
    @GeneratedValue
    Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    private String name;

    @NotNull(message = "Cor não pode ser vazia")
    @Min(value = 0, message = "Valor da cor não pode ser zero")
    private int color;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Execution> executions;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", nullable = true, insertable = true, updatable = true)
    private Student student;
}
