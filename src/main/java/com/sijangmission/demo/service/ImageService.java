package com.sijangmission.demo.service;

import com.sijangmission.demo.domain.core.Image;
import com.sijangmission.demo.dto.ImageDto;
import com.sijangmission.demo.mapper.ImageMapper;
import com.sijangmission.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    
    public List<ImageDto> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return imageMapper.toDtoList(images);
    }
    
    public Optional<ImageDto> getImageById(Long imageId) {
        Optional<Image> image = imageRepository.findById(imageId);
        return image.map(imageMapper::toDto);
    }
    

    
    public ImageDto saveImage(Image image) {
        Image savedImage = imageRepository.save(image);
        return imageMapper.toDto(savedImage);
    }
    
    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
