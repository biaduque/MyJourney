package com.faculdade.MyJourney.controllers;

import com.faculdade.MyJourney.Services.ActivityService;
import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class HistoricoController {

    private final ActivityService activityService;

    public HistoricoController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/historico-hoje")
    public String getAdd(Model model) throws JsonProcessingException {
        model.addAttribute("dataExecucao", LocalDate.now());
        var dailyExecutedActivities = activityService.getDailyExecutedActivities(LocalDate.now());

        model.addAttribute("activities",new ArrayList<String>(dailyExecutedActivities.keySet()));
        model.addAttribute("duration",new ArrayList<Long>(dailyExecutedActivities.values()));
        log.info("Activities {}", model.getAttribute("activities"));
        log.info("duration {}", model.getAttribute("duration"));
        return "historico-diario";
    }

    @GetMapping("/historico-mes")
    public String getMes(Model model) {
        model.addAttribute("dataExecucao", LocalDate.now());

        model.addAttribute("column", new Object[]{"01/08", 10, 24, 20, 32, 18, 5, "NADA"});
        return "historico-mes";
    }

    @GetMapping("/historico-semana")
    public String getSemana(Model model) {
        model.addAttribute("dataExecucao", LocalDate.now());

        return "historico-semana";
    }
}
