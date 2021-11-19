package com.faculdade.MyJourney.Services;

import com.faculdade.MyJourney.models.Execution;
import com.faculdade.MyJourney.repositorys.ActivityRepository;
import com.faculdade.MyJourney.repositorys.ExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ExecutionRepository executionRepository;
    private final ActivityRepository activityRepository;

    @Transactional
    public Execution save(Execution execution) {
        execution.setActivity(activityRepository.findById(execution
                        .getActivity()
                        .getId())
                .orElseThrow(NullPointerException::new));
        execution.setDate(LocalDate.now());
        return executionRepository.save(execution);
    }
}
