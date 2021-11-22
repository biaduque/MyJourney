package com.faculdade.MyJourney.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final ActivityService activityService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Iniciando banco de dados");
        activityService.saveMockData();

    }
}
