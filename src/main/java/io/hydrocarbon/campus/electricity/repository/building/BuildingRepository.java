package io.hydrocarbon.campus.electricity.repository.building;

import io.hydrocarbon.campus.electricity.entity.building.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
}
