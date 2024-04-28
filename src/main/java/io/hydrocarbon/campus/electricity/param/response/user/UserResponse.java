package io.hydrocarbon.campus.electricity.param.response.user;

import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import lombok.Data;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Data
public class UserResponse {

    /**
     * 用户 ID
     */
    private UUID id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 楼栋
     */
    private String building;

    /**
     * 房间号
     */
    private String room;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学号
     */
    private String studentNo;

    public static UserResponse fromEntity(UserEntity userEntity) {
        var userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setName(userEntity.getName());
       // userResponse.setBuilding(userEntity.getRoom().getBuilding().getName());
        //userResponse.setRoom(userEntity.getRoom().getName());
        userResponse.setPhone(userEntity.getPhone());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setStudentNo(userEntity.getStudentNo());
        return userResponse;
    }
}
