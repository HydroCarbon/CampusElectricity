package io.hydrocarbon.campus.electricity.param.response.building;

import io.hydrocarbon.campus.electricity.entity.building.RoomEntity;
import lombok.Data;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class RoomResponse {

    private UUID id;

    private UUID buildingId;

    private String name;

    public static RoomResponse fromEntity(RoomEntity roomEntity) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(roomEntity.getId());
        roomResponse.setBuildingId(roomEntity.getBuildingId());
        roomResponse.setName(roomEntity.getName());
        return roomResponse;
    }
}
