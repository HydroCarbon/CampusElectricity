package io.hydrocarbon.campus.electricity.repository.building;

import io.hydrocarbon.campus.electricity.entity.building.RoomEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {
    @Query("select distinct r from RoomEntity r where r.buildingId in ?1 order by r.createdAt DESC")
    List<RoomEntity> findDistinctByBuildingIdInOrderByCreatedAtDesc(Collection<UUID> buildingIds, Sort sort);

    @Query("select distinct r from RoomEntity r where r.buildingId = ?1")
    List<RoomEntity> findDistinctByBuildingId(UUID buildingId);
}
