package com.sijangmission.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotDto {
    private Long spotId;
    private Long marketId;
    private String marketName;
    private String name;
    private String category;
    private String description;
    private String imageUrl;
    private Double latitude;
    private Double longitude;
    private Integer missionCount;
    private List<String> courseNames;
}
