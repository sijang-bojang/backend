package com.sijangmission.demo.controller;

import com.sijangmission.demo.domain.core.Image;
import com.sijangmission.demo.dto.ImageDto;
import com.sijangmission.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    
    private final ImageService imageService;
    
    @GetMapping
    public ResponseEntity<List<ImageDto>> getAllImages() {
        List<ImageDto> images = imageService.getAllImages();
        return ResponseEntity.ok(images);
    }
    
    @GetMapping("/{imageId}")
    public ResponseEntity<ImageDto> getImageById(@PathVariable Long imageId) {
        Optional<ImageDto> image = imageService.getImageById(imageId);
        return image.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    
    @PostMapping
    public ResponseEntity<ImageDto> createImage(@RequestBody Image image) {
        ImageDto savedImage = imageService.saveImage(image);
        return ResponseEntity.ok(savedImage);
    }
    
    @PutMapping("/{imageId}")
    public ResponseEntity<ImageDto> updateImage(@PathVariable Long imageId, @RequestBody Image image) {
        Optional<ImageDto> existingImage = imageService.getImageById(imageId);
        if (existingImage.isPresent()) {
            image.setImageId(imageId);
            ImageDto updatedImage = imageService.saveImage(image);
            return ResponseEntity.ok(updatedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        Optional<ImageDto> image = imageService.getImageById(imageId);
        if (image.isPresent()) {
            imageService.deleteImage(imageId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
