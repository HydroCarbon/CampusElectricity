package io.hydrocarbon.campus.electricity.param.request;

import lombok.Data;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Data
public class LoginRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
