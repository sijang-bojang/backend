package com.sijangmission.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_course_progress")
public class UserCourseProgress {
    @EmbeddedId
    private UserCourseProgressId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Integer currentStep;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserCourseProgressId implements Serializable {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "course_id")
        private Long courseId;
    }
}
