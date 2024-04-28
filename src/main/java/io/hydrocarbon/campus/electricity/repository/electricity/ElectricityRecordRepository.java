package io.hydrocarbon.campus.electricity.repository.electricity;

import io.hydrocarbon.campus.electricity.entity.electricity.ElectricityRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Repository
public interface ElectricityRecordRepository extends JpaRepository<ElectricityRecordEntity, UUID>,
        JpaSpecificationExecutor<ElectricityRecordEntity> {
}
