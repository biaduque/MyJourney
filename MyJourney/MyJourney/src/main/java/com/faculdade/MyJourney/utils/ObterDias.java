package com.faculdade.MyJourney.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class ObterDias {

    public String[] execute(int quantidade) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM");
        String[] resp = new String[quantidade];
        for (int i = 0; i < quantidade; i++) {
            resp[i] = LocalDate.now().minusDays(quantidade - i - 1).format(formatter);
        }

        return resp;
    }
}
