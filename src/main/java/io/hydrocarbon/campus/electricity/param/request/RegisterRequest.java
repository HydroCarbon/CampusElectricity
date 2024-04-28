package io.hydrocarbon.campus.electricity.param.request;

import lombok.Data;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Data
public class RegisterRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 房间
     */
    private UUID roomId;

    /**
     * 学号
     */
    private String studentNo;
}
