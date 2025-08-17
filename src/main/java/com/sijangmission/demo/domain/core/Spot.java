package com.sijangmission.demo.domain.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    private Long spotId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private com.sijangmission.demo.domain.core.Image image;
    
    @Column(name = "latitude")
    private Double latitude;
    
    @Column(name = "longitude")
    private Double longitude;
    
    // Many-to-Many relationship with Course through CourseSpot
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.CourseSpot> courseSpots = new ArrayList<>();
    
    // Many-to-Many relationship with Mission through SpotMission
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<com.sijangmission.demo.domain.relation.SpotMission> spotMissions = new ArrayList<>();
    
    // 편의 메서드: 스팟의 미션들 조회
    public List<Mission> getMissions() {
        return spotMissions.stream()
                .map(spotMission -> spotMission.getMission())
                .toList();
    }
    
    // 편의 메서드: 스팟의 코스들 조회
    public List<Course> getCourses() {
        return courseSpots.stream()
                .map(courseSpot -> courseSpot.getCourse())
                .toList();
    }
    
    // 편의 메서드: 식당 카테고리인지 확인
    public boolean isRestaurant() {
        return "식당".equals(this.category);
    }
    
    // 편의 메서드: 카페 카테고리인지 확인
    public boolean isCafe() {
        return "카페".equals(this.category);
    }
    
    // 편의 메서드: 즐길거리 카테고리인지 확인
    public boolean isAttraction() {
        return "즐길거리".equals(this.category);
    }
}
