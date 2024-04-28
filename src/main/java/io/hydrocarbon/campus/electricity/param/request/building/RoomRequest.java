package io.hydrocarbon.campus.electricity.param.request.building;

import io.hydrocarbon.campus.electricity.entity.building.RoomEntity;
import lombok.Data;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class RoomRequest {
    private UUID buildingId;

    private String name;

    public RoomEntity toEntity() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setBuildingId(buildingId);
        roomEntity.setName(name);
        return roomEntity;
    }
}
