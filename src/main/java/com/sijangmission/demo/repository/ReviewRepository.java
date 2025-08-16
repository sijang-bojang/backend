package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
