package com.faculdade.MyJourney.Services;

import com.faculdade.MyJourney.Services.ExecutionService;
import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import com.faculdade.MyJourney.repositorys.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExecutionServiceTest {

    @Autowired
    ExecutionService executionService;

    @Autowired
    ActivityRepository activityRepository;

    // Inserindo uma execução nula
    @Test
    void testeNulo() {
        Execution execution = null;
        assertThrows(
                NullPointerException.class,
                () -> executionService.save(execution),
                "Null pointer esperado."
        );
    }

    // Inserindo uma execução com atributos nulos
    @Test
    void testeAtributosNulos() {
        Execution execution = new Execution();
        assertThrows(
                NullPointerException.class,
                () -> executionService.save(execution),
                "Null pointer esperado."
        );
    }

    // Inserindo uma execução com atributos falhos: Horário antes do atual
    @Test
    void testeAtributosBeginIncorreto() {
        Activity activity = activityRepository.save(new Activity("Teste", 1));
        var inicio = LocalTime.now().minusMinutes(30);
        var fim = inicio.plusMinutes(30);
        Execution execution = new Execution(inicio, fim, LocalDate.now(), activity);
        assertThrows(
                ResponseStatusException.class,
                () -> executionService.save(execution),
                "Não pode gravar com um inicio anterior à hora atual."
        );
    }

    // Inserindo uma execução com atributos falhos: Horário final antes do inicial
    @Test
    void testeAtributosEndIncorreto() {
        Activity activity = activityRepository.save(new Activity("Teste", 1));
        var inicio = LocalTime.now();
        var fim = inicio.minusMinutes(30);
        Execution execution = new Execution(inicio, fim, LocalDate.now(), activity);
        assertThrows(
                ResponseStatusException.class,
                () -> executionService.save(execution),
                "O horário final não pode ser anterior ao inicial."
        );
    }

    // Inserindo uma execução com atributos falhos: Data anterior ao dia atual
    @Test
    void testeAtributosDateIncorreto() {
        Activity activity = activityRepository.save(new Activity("Teste", 1));
        var inicio = LocalTime.now();
        var fim = inicio.plusMinutes(30);
        Execution execution = new Execution(inicio, fim, LocalDate.now().minusDays(1), activity);
        assertThrows(
                ResponseStatusException.class,
                () -> executionService.save(execution),
                "A data da nova atividade não pode ser anterior ao dia de hoje."
        );
    }

    // Inserindo uma execução com atributos corretos
    @Test
    void testeAtributosCorretos() {
        Activity activity = activityRepository.save(new Activity("Teste", 1));
        var inicio = LocalTime.now();
        var fim = inicio.plusMinutes(30);
        Execution execution = new Execution(inicio, fim, LocalDate.now(), activity);
        var savedExecution = executionService.save(execution);
        assertNotNull(
                savedExecution.getId(),
                "O ID não foi gerado."
        );
        assertEquals(
                "Teste",savedExecution.getActivity().getName(),
                "O nome deve ser Teste."
        );
        assertEquals(
                1,savedExecution.getActivity().getColor(),
                "A cor deve ser 1 (INT)."
        );
        assertEquals(
                inicio,savedExecution.getBegin(),
                "O horario deveria ser "+inicio+"."
        );
        assertEquals(
                fim,savedExecution.getEnd(),
                "O horario deveria ser "+fim+"."
        );
    }
}