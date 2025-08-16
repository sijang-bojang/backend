package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
