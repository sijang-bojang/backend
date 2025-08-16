package com.sijangmission.demo.repository;

import com.sijangmission.demo.domain.SpotType;
import com.sijangmission.demo.domain.SpotType.SpotTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotTypeRepository extends JpaRepository<SpotType, SpotTypeId> {
}
