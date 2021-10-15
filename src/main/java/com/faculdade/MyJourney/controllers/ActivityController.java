package com.faculdade.MyJourney.controllers;

import com.faculdade.MyJourney.Services.ActivityService;
import com.faculdade.MyJourney.Services.ExecutionService;
import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import com.faculdade.MyJourney.repositorys.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ActivityController {

    private final ActivityRepository activityRepository;

    private final ActivityService activityService;
    private final ExecutionService executionService;

    @GetMapping("/atividades")
    public String getAtividades(Model model) {
        var activities = activityService.listAllActivities();
        model.addAttribute("activities", activities);
        model.addAttribute("activity", new Activity());
        return "atividades";
    }

    @PostMapping("/atividades")
    public String createAtividades(@ModelAttribute @Valid Activity activity, Model model) {
        log.info("Salvando atividade: {}", activity);
        activityService.save(activity);
        return getAtividades(model);
    }

    @GetMapping("/iniciar-atividades")
    public String iniciarAtividades(Model model) {
        var activities = activityService.listAllActivities();
        model.addAttribute("activities", activities);
        model.addAttribute("execution", new Execution());
        return "iniciar-atividades";
    }

    @PostMapping("/iniciar-atividades")
    public String updateAtividades(@ModelAttribute @Valid Execution execution, Model model) {
        log.info("Salvando execucao: {}", execution);
        executionService.save(execution);
        return iniciarAtividades(model);
    }
}
