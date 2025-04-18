package org.example.vdcolataskscheduler.repository;

import org.example.vdcolataskscheduler.entity.Task;
import org.example.vdcolataskscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);
}
