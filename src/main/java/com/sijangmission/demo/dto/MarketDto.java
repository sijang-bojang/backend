package com.sijangmission.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketDto {
    private Long marketId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Integer courseCount;
    private Integer spotCount;
}
