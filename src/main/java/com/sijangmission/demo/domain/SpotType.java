package com.sijangmission.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "spot_types")
public class SpotType {
    @EmbeddedId
    private SpotTypeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("typeId")
    @JoinColumn(name = "type_id")
    private Type type;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class SpotTypeId implements Serializable {
        @Column(name = "course_id")
        private Long courseId;

        @Column(name = "type_id")
        private Long typeId;
    }
}
