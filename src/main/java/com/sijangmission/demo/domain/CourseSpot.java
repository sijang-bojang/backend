package com.sijangmission.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_spots")
public class CourseSpot {
    @EmbeddedId
    private CourseSpotId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("spotId")
    @JoinColumn(name = "spot_id")
    private Spot spot;

    private Integer stepNumber;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class CourseSpotId implements Serializable {
        @Column(name = "course_id")
        private Long courseId;

        @Column(name = "spot_id")
        private Long spotId;
    }
}
