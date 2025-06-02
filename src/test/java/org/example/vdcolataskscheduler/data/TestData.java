package org.example.vdcolataskscheduler.data;

import org.example.vdcolataskscheduler.dto.Category;
import org.example.vdcolataskscheduler.dto.TaskDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestData {

    public TaskDto getTask(){
        TaskDto task = new TaskDto();
        task.setId(1L);
        task.setUserId(101L);
        task.setCategory(Category.BEAUTY);
        task.setDescription("Событие 1");
        task.setNotified(false);
        task.setCreatedAt(LocalDateTime.now().minusDays(5));
        return task;
    }

    public List<TaskDto> getTaskList(){
        LocalDate now = LocalDate.now();

        TaskDto task1 = new TaskDto();
        task1.setId(1L);
        task1.setUserId(101L);
        task1.setCategory(Category.BEAUTY);
        task1.setDescription("Событие 1");
        task1.setDate(now.plusDays(2));
        task1.setNotified(false);
        task1.setCreatedAt(LocalDateTime.now().minusDays(5));

        TaskDto task2 = new TaskDto();
        task2.setId(2L);
        task2.setUserId(102L);
        task2.setCategory(Category.BEAUTY);
        task2.setDescription("Событие 2");
        task2.setDate(now.plusDays(1));
        task2.setNotified(false);
        task2.setCreatedAt(LocalDateTime.now().minusDays(4));

        TaskDto task3 = new TaskDto();
        task3.setId(3L);
        task3.setUserId(103L);
        task3.setCategory(Category.BEAUTY);
        task3.setDescription("Событие 3");
        task3.setDate(now);
        task3.setNotified(false);
        task3.setCreatedAt(LocalDateTime.now().minusDays(3));

        TaskDto task4 = new TaskDto();
        task4.setId(4L);
        task4.setUserId(104L);
        task4.setCategory(Category.BEAUTY);
        task4.setDescription("Событие 4");
        task4.setDate(now.plusDays(3));
        task4.setNotified(false);
        task4.setCreatedAt(LocalDateTime.now().minusDays(2));

        TaskDto task5 = new TaskDto();
        task5.setId(5L);
        task5.setUserId(105L);
        task5.setCategory(Category.BEAUTY);
        task5.setDescription("Событие 5");
        task5.setDate(now);
        task5.setNotified(true);
        task5.setCreatedAt(LocalDateTime.now().minusDays(1));

        return List.of(task1, task2, task3, task4, task5);
    }
}
