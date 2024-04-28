package io.hydrocarbon.campus.electricity.controller.user;

import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import io.hydrocarbon.campus.electricity.param.request.user.UserInfoRequest;
import io.hydrocarbon.campus.electricity.param.response.Response;
import io.hydrocarbon.campus.electricity.param.response.user.UserResponse;
import io.hydrocarbon.campus.electricity.param.user.UserQueryParam;
import io.hydrocarbon.campus.electricity.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 查询用户列表
     *
     * @param name      姓名
     * @param studentNo 学号
     * @param roomId    房间 ID
     * @param pageNo    页码
     * @param pageSize  每页大小
     * @return 用户列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public Response<Page<UserResponse>> list(@RequestParam(required = false)
                                             String name,
                                             @RequestParam(required = false)
                                             String studentNo,
                                             @RequestParam(required = false)
                                             UUID roomId,
                                             @RequestParam(defaultValue = "0")
                                             Integer pageNo,
                                             @RequestParam(defaultValue = "10")
                                             Integer pageSize) {
        var userQueryParam = UserQueryParam.builder()
                .name(name)
                .studentNo(studentNo)
                .roomId(roomId)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();
        Page<UserEntity> pageResult = userService.page(userQueryParam);
        return Response.success(pageResult.map(UserResponse::fromEntity));
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户 ID
     * @return 用户信息
     */
    @GetMapping("/{userId}/info")
    @PostAuthorize("hasRole('ADMIN') or returnObject.data.id == authentication.principal.id")
    public Response<UserResponse> info(@PathVariable String userId) {
        return Response.success(UserResponse.fromEntity(userService.info(userId)));
    }

    /**
     * 编辑用户信息
     *
     * @param userId   用户 ID
     * @param userInfo 用户信息
     * @return 是否成功
     */
    @PostMapping("/{userId}/info")
    public Response<Boolean> editInfo(@PathVariable String userId,
                                      @RequestBody UserInfoRequest userInfo) {

        return Response.success(userService.editInfo(userId, userInfo));
    }

    @PostMapping("/{userId}/change-password")
    public Response<Boolean> changePassword(@PathVariable String userId,
                                            @RequestParam String password) {
        return Response.success(userService.changePassword(userId, password));
    }
}
