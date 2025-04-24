package org.example.vdcolataskscheduler.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.vdcolataskscheduler.dto.Category;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@ToString(exclude = "user")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;
    private LocalDate date;

    @Column(nullable = false)
    private boolean isNotified;
}
