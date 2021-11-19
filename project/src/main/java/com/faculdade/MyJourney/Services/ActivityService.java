package com.faculdade.MyJourney.Services;

import com.faculdade.MyJourney.models.Activity;
import com.faculdade.MyJourney.models.Execution;
import com.faculdade.MyJourney.models.Student;
import com.faculdade.MyJourney.repositorys.ActivityRepository;
import com.faculdade.MyJourney.repositorys.ExecutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ExecutionRepository executionRepository;
    private final ActivityRepository activityRepository;
    private final StudentService studentService;

    public Map<String, Long> getDailyExecutedActivities(LocalDate date) {
        List<Activity> activities = studentService.getStudentFromLogin().getListActivity();

        if (activities == null) {
            activities = Collections.emptyList();
        }

        return executionRepository.findAllByDateAndActivityIn(date, activities)
                .stream()
                .collect(Collectors.groupingBy(execution -> execution.getActivity().getName(),
                        Collectors.summingLong(Execution::durationInMinutes)));
    }

    public Map<String, List<Long>> getActivitiesOnLastDays(int days) {
        HashMap<String, List<Long>> response = new HashMap<>();

        List<Activity> activities = studentService.getStudentFromLogin().getListActivity();

        activities.forEach(activity -> response.put(activity.getName(), getExecutions(days, activity)));

        return response;
    }

    private List<Long> getExecutions(int days, Activity activity) {
        ArrayList<Long> executions = new ArrayList<>();

        for (int i = days - 1; i >= 0; i--) {
            executions.add(executionRepository.findAllByDateAndActivityIn(
                            LocalDate.now().minusDays(i), Collections.singletonList(activity))
                    .stream()
                    .mapToLong(Execution::durationInMinutes)
                    .sum());
        }

        return executions;

    }

    public Activity save(Activity activity) {
        activity.setStudent(studentService.getStudentFromLogin());
        return activityRepository.save(activity);
    }

    public List<Execution> mockData() {
        Execution mocked = new Execution();
        mocked.setDate(LocalDate.now());
        mocked.setBegin(LocalTime.MIDNIGHT.minusHours(5));
        mocked.setEnd(LocalTime.MIDNIGHT.minusHours(2));

        var student = new Student();
        student.setName("user1");

        var mockedActivity = new Activity();
        mockedActivity.setColor(1);
        mockedActivity.setName("Trabalho");
        mockedActivity.setStudent(student);
        mocked.setActivity(mockedActivity);

        Execution mocked2 = new Execution();
        mocked2.setDate(LocalDate.now());
        mocked2.setBegin(LocalTime.MIDNIGHT.minusHours(8));
        mocked2.setEnd(LocalTime.MIDNIGHT.minusHours(2));

        Execution mocked3 = new Execution();
        mocked3.setDate(LocalDate.now().minusDays(3));
        mocked3.setBegin(LocalTime.MIDNIGHT.minusHours(7));
        mocked3.setEnd(LocalTime.MIDNIGHT.minusHours(2));

        var mockedActivity2 = new Activity();
        mockedActivity2.setColor(1);
        mockedActivity2.setName("Programar");
        mockedActivity2.setStudent(student);
        mocked2.setActivity(mockedActivity2);
        mocked3.setActivity(mockedActivity2);
        return Arrays.asList(mocked, mocked2, mocked3);
    }

    public void saveMockData() {
        executionRepository.saveAll(mockData());
    }
}
