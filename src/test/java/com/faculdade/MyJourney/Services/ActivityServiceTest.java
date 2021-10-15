package com.faculdade.MyJourney.Services;

import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import com.faculdade.MyJourney.repositorys.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityServiceTest {
    @Autowired
    ExecutionService executionService;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityService activityService;

    // Inserindo uma execução com atributos corretos
    @Test
    void testeAtributosCorretos() {
        Activity activity = activityRepository.save(new Activity("Teste2", 1));
        var inicio = LocalTime.now();
        var fim = inicio.plusMinutes(30);
        var dia = LocalDate.now();
        Execution execution = new Execution(inicio, fim, dia, activity);
        executionService.save(execution);
        var atividadesHoje = activityService.getDailyExecutedActivities(dia);

        assertEquals(
                30L,(Long)atividadesHoje.get("Teste2"),
                "O horário não bate com o esperado."
        );
    }

    // Executando duas vezes no mesmo dia a mesma atividade
    @Test
    void testeSomaHora() {
        Activity activity = activityRepository.save(new Activity("Teste", 1));
        var inicio = LocalTime.now();
        var fim = inicio.plusMinutes(30);
        var dia = LocalDate.now();
        Execution execution = new Execution(inicio, fim, dia, activity);
        Execution execution2 = new Execution(inicio, fim, dia, activity);
        executionService.save(execution);
        executionService.save(execution2);
        var atividadesHoje = activityService.getDailyExecutedActivities(dia);

        assertEquals(
                60L,(Long)atividadesHoje.get("Teste"),
                "O horário não bate com o esperado."
        );
    }

    // Teste de erro: Dia errado
    @Test
    void testeDiaIncorreto() {
        Activity activity = activityRepository.save(new Activity("Teste", 1));
        var inicio = LocalTime.now();
        var fim = inicio.plusMinutes(30);
        var dia = LocalDate.now();
        Execution execution = new Execution(inicio, fim, dia, activity);
        executionService.save(execution);
        var atividadesHoje = activityService.getDailyExecutedActivities(dia.plusDays(1));

        assertTrue(
                atividadesHoje.isEmpty(),
                "A lista de atividades deve ser vazia."
        );
    }
}