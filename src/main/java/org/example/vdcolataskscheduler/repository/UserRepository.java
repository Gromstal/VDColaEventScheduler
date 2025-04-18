package org.example.vdcolataskscheduler.repository;

import org.example.vdcolataskscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    boolean existsByLogin(String login);
}
