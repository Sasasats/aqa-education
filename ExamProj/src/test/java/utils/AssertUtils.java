package utils;

import models.Project;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AssertUtils {

    public static boolean assertEquals(List<Project> actual, List<Project> expected) {
        for (int i = 0; i < actual.size(); i++) {
            replaceEmptyLine(actual.get(i));
            replaceEmptyLine(expected.get(i));

            if (!actual.get(i).getName().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getName().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getMethod().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getMethod().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getStatus().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getStatus().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getStartTime().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getStartTime().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getEndTime().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getEndTime().toLowerCase(Locale.ROOT))) {
                return false;
            }
            if (!actual.get(i).getDuration().toLowerCase(Locale.ROOT).equals(
                    expected.get(i).getDuration().toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

    private static void replaceEmptyLine(Project project) {
        if (Objects.equals(project.getName(), "")) {
            project.setName("null");
        } else if (Objects.equals(project.getName(), null)) {
            project.setName("null");
        }

        if (Objects.equals(project.getMethod(), "")) {
            project.setMethod("null");
        } else if (Objects.equals(project.getMethod(), null)) {
            project.setMethod("null");
        }

        if (Objects.equals(project.getStatus(), "")) {
            project.setStatus("null");
        } else if (Objects.equals(project.getStatus(), null)) {
            project.setStatus("null");
        }

        if (Objects.equals(project.getStartTime(), "")) {
            project.setStartTime("null");
        } else if (Objects.equals(project.getStartTime(), null)) {
            project.setStartTime("null");
        }

        if (Objects.equals(project.getEndTime(), "")) {
            project.setEndTime("null");
        } else if (Objects.equals(project.getEndTime(), null)) {
            project.setEndTime("null");
        }

        if (Objects.equals(project.getDuration(), "")) {
            project.setDuration("null");
        } else if (Objects.equals(project.getDuration(), null)) {
            project.setDuration("null");
        }
    }
}