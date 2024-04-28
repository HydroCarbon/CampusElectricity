package io.hydrocarbon.campus.electricity.service.building;

import io.hydrocarbon.campus.electricity.entity.building.BuildingEntity;
import io.hydrocarbon.campus.electricity.entity.building.RoomEntity;
import io.hydrocarbon.campus.electricity.param.request.building.RoomRequest;
import io.hydrocarbon.campus.electricity.param.response.building.BuildingResponse;
import io.hydrocarbon.campus.electricity.param.response.building.RoomResponse;
import io.hydrocarbon.campus.electricity.param.response.user.UserResponse;
import io.hydrocarbon.campus.electricity.repository.building.BuildingRepository;
import io.hydrocarbon.campus.electricity.repository.building.RoomRepository;
import io.hydrocarbon.campus.electricity.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<BuildingResponse> listBuilding() {
        List<BuildingEntity> allBuildingList = buildingRepository.findAll(Sort.by(Sort.Order.desc("createdAt")));

        Map<BuildingEntity, List<RoomEntity>> roomMap = roomRepository.findAll(Sort.by(Sort.Order
                        .desc("createdAt")))
                .stream()
                .collect(Collectors.groupingBy(RoomEntity::getBuilding));

        return allBuildingList.stream()
                .map(building -> {
                    BuildingResponse buildingResponse = BuildingResponse.fromEntity(building);
                    buildingResponse.setRooms(roomMap.getOrDefault(building, List.of())
                            .stream()
                            .map(RoomResponse::fromEntity)
                            .toList());
                    return buildingResponse;
                })
                .toList();
    }

    public List<RoomResponse> listRoom(UUID buildingId) {
        List<RoomEntity> roomList = roomRepository.findDistinctByBuildingId(buildingId);
        return roomList.stream()
                .map(RoomResponse::fromEntity)
                .toList();
    }

    public UUID addRoom(RoomRequest roomRequest) {
        RoomEntity room = roomRepository.save(roomRequest.toEntity());
        return room.getId();
    }

    public UUID addBuilding(String name) {
        BuildingEntity building = new BuildingEntity();
        building.setName(name);
        buildingRepository.save(building);
        return building.getId();
    }

    public List<UserResponse> listUser(UUID buildingId, UUID roomId) {
        if (Objects.nonNull(roomId)) {
            return userRepository.findDistinctByRoomId(roomId)
                    .stream()
                    .map(UserResponse::fromEntity)
                    .toList();
        }

        if (Objects.nonNull(buildingId)) {
            List<RoomEntity> roomList = roomRepository.findDistinctByBuildingId(buildingId);
            List<UUID> roomIdList = roomList.stream()
                    .map(RoomEntity::getId)
                    .toList();

            return userRepository.findDistinctByRoomIdIn(roomIdList)
                    .stream()
                    .map(UserResponse::fromEntity)
                    .toList();
        }

        return List.of();
    }
}
