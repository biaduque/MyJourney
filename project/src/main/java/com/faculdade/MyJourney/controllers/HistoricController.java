package com.faculdade.MyJourney.controllers;

import com.faculdade.MyJourney.Services.ActivityService;
import com.faculdade.MyJourney.utils.ObterDias;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@Slf4j
public class HistoricController {

    private final ActivityService activityService;

    public HistoricController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/historico-hoje")
    public String getAdd(Model model) {
        model.addAttribute("dataExecucao", LocalDate.now());
        var dailyExecutedActivities = activityService.getDailyExecutedActivities(LocalDate.now());

        model.addAttribute("activities", new ArrayList<>(dailyExecutedActivities.keySet()));
        model.addAttribute("duration", new ArrayList<>(dailyExecutedActivities.values()));
        log.info("Activities {}", model.getAttribute("activities"));
        log.info("duration {}", model.getAttribute("duration"));
        return "historico-diario";
    }

    @GetMapping("/historico-mes")
    public String getMes(Model model) {
        populateLastActivities(model, 30);

        return "historico-mes";
    }


    @GetMapping("/historico-semana")
    public String getSemana(Model model) {
        populateLastActivities(model, 7);

        return "historico-semana";
    }


    private void populateLastActivities(Model model, int days) {
        model.addAttribute("dataExecucao", LocalDate.now());
        var dailyExecutedActivities = activityService.getActivitiesOnLastDays(days);

        model.addAttribute("dias", ObterDias.execute(days));
        model.addAttribute("activities", new ArrayList<>(dailyExecutedActivities.keySet()));
        model.addAttribute("durations", new ArrayList<>(dailyExecutedActivities.values()));

        log.info("Activities {}", model.getAttribute("activities"));
        log.info("duration {}", model.getAttribute("durations"));
    }
}
