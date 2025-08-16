package com.sijangmission.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "markets")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;

    @OneToMany(mappedBy = "market")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "market")
    private List<Spot> spots = new ArrayList<>();
}
