package com.faculdade.MyJourney.Services;

import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import com.faculdade.MyJourney.repositorys.ActivityRepository;
import com.faculdade.MyJourney.repositorys.ExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ExecutionRepository executionRepository;
    private final ActivityRepository activityRepository;

    public Map<String, Long> getDailyExecutedActivities(LocalDate date) {
        return executionRepository.findAllByDate(date)
                .stream()
                .collect(Collectors.groupingBy(execution -> execution.getActivity().getName(),
                        Collectors.summingLong(Execution::durationInMinutes)));
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> listAllActivities() {
        return activityRepository.findAll();
    }

    public List<Execution> mockData() {
        Execution mocked = new Execution();
        mocked.setDate(LocalDate.now());
        mocked.setBegin(LocalTime.MIDNIGHT.minusHours(5));
        mocked.setEnd(LocalTime.MIDNIGHT.minusHours(2));

        var mockedActivity = new Activity();
        mockedActivity.setColor(1);
        mockedActivity.setName("Trabalho");
        mocked.setActivity(mockedActivity);

        Execution mocked2 = new Execution();
        mocked2.setDate(LocalDate.now());
        mocked2.setBegin(LocalTime.MIDNIGHT.minusHours(8));
        mocked2.setEnd(LocalTime.MIDNIGHT.minusHours(2));

        var mockedActivity2 = new Activity();
        mockedActivity2.setColor(1);
        mockedActivity2.setName("Programar");
        mocked2.setActivity(mockedActivity2);
        return Arrays.asList(mocked, mocked2);
    }

    public void saveMockData() {
        executionRepository.saveAll(mockData());
    }
}
