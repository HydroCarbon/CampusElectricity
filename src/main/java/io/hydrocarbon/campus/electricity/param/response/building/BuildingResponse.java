package io.hydrocarbon.campus.electricity.param.response.building;

import io.hydrocarbon.campus.electricity.entity.building.BuildingEntity;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Data
public class BuildingResponse {

    private UUID id;

    private String name;

    private List<RoomResponse> rooms;

    public static BuildingResponse fromEntity(BuildingEntity buildingEntity) {
        BuildingResponse buildingResponse = new BuildingResponse();
        buildingResponse.setId(buildingEntity.getId());
        buildingResponse.setName(buildingEntity.getName());
        return buildingResponse;
    }
}
