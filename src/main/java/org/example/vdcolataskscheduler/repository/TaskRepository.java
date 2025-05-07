package org.example.vdcolataskscheduler.repository;

import org.example.vdcolataskscheduler.dto.Category;
import org.example.vdcolataskscheduler.entity.Task;
import org.example.vdcolataskscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser_Id(Long userId);

    @Query("update Task t set t.isNotified = true where t.id = :id")
    @Modifying
    void updateIsNotifiedById(Long id);

    void deleteById(Long id);

    @Query("update Task t set t.description=:description, t.category=:category, t.date =:date where t.id = :id")
    @Modifying
    void updateAllById(@Param("id") Long id,
                       @Param("description") String description,
                       @Param("category") Category category,
                       @Param("date") LocalDate date);

}