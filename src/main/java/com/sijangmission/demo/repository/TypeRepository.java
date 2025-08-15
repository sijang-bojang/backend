package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    
    List<Type> findByNameContaining(String name);
}
