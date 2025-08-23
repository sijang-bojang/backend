package com.sijangmission.demo.mapper;

import com.sijangmission.demo.domain.core.Image;
import com.sijangmission.demo.dto.ImageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {
    
    public ImageDto toDto(Image entity) {
        if (entity == null) {
            return null;
        }
        
        return ImageDto.builder()
                .imageId(entity.getImageId())
                .imageUrl(entity.getImageUrl())
                .build();
    }
    
    public List<ImageDto> toDtoList(List<Image> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public Image toEntity(ImageDto dto) {
        if (dto == null) {
            return null;
        }
        
        Image entity = new Image();
        entity.setImageId(dto.getImageId());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
}
