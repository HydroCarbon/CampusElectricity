package io.hydrocarbon.campus.electricity.param.request.user;

import lombok.Data;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Data
public class UserInfoRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 房间 ID
     */
    private UUID roomId;

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

    /**
     * 角色
     */
    private String role;
}
