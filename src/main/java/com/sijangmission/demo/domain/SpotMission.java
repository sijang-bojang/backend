package com.sijangmission.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "spot_mission")
public class SpotMission {
    @EmbeddedId
    private SpotMissionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("spotId")
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("missionId")
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class SpotMissionId implements Serializable {
        @Column(name = "spot_id")
        private Long spotId;

        @Column(name = "mission_id")
        private Long missionId;
    }
}
