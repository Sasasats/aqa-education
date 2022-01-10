package utils;

import models.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortUtils {
    public static List<String> sortListReverseOrder(List<String> list) {
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public static List<Project> sortListForTimeReverseOrder(List<Project> projects) {
        List<String> times = new ArrayList<>();
        List<Project> sortedProjects = new ArrayList<>();

        for (Project project : projects) {
            times.add(project.getStartTime());
        }

        List<String> sortedTimes = sortListReverseOrder(times);

        for (String time : sortedTimes) {
            for (int i = 0; i < sortedTimes.size(); i++) {
                if (time == projects.get(i).getStartTime()) {
                    sortedProjects.add(projects.get(i));
                }
            }
        }
        
        return sortedProjects;
    }
}