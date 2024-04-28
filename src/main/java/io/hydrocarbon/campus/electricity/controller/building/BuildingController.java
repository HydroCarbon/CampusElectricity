package io.hydrocarbon.campus.electricity.controller.building;

import io.hydrocarbon.campus.electricity.param.request.building.RoomRequest;
import io.hydrocarbon.campus.electricity.param.response.Response;
import io.hydrocarbon.campus.electricity.param.response.building.BuildingResponse;
import io.hydrocarbon.campus.electricity.param.response.building.RoomResponse;
import io.hydrocarbon.campus.electricity.param.response.user.UserResponse;
import io.hydrocarbon.campus.electricity.service.building.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    /**
     * 添加楼栋
     *
     * @param name 楼栋名称
     * @return 楼栋 ID
     */
    @PutMapping
    public Response<UUID> addBuilding(@RequestParam String name) {
        UUID buildingId = buildingService.addBuilding(name);
        return Response.success(buildingId);
    }

    /**
     * 添加房间
     *
     * @param roomRequest 房间信息
     * @return 房间 ID
     */
    @PutMapping("/room")
    public Response<UUID> addRoom(@RequestBody RoomRequest roomRequest) {
        UUID roomId = buildingService.addRoom(roomRequest);
        return Response.success(roomId);
    }

    /**
     * 获取全部楼栋信息
     *
     * @return 全部楼栋信息
     */
    @GetMapping("/list")
    public Response<List<BuildingResponse>> listBuilding() {
        return Response.success(buildingService.listBuilding());
    }

    /**
     * 获取楼栋下的全部房间信息
     *
     * @param buildingId 楼栋 ID
     * @return 楼栋下的全部房间信息
     */
    @GetMapping("/{buildingId}/rooms")
    public Response<List<RoomResponse>> listRoom(@PathVariable UUID buildingId) {
        return Response.success(buildingService.listRoom(buildingId));
    }

    /**
     * 获取楼栋下的全部用户信息
     *
     * @param buildingId 楼栋 ID
     * @return 楼栋下的全部用户信息
     */
    @GetMapping("/users")
    public Response<List<UserResponse>> listUser(@RequestParam(required = false)
                                                 UUID buildingId,
                                                 @RequestParam(required = false)
                                                 UUID roomId) {
        return Response.success(buildingService.listUser(buildingId, roomId));
    }
}
