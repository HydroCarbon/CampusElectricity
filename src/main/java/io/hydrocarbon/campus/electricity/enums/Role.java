package io.hydrocarbon.campus.electricity.enums;

import io.hydrocarbon.campus.electricity.constants.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
public enum Role {

    /**
     * 管理员
     */
    ADMIN,

    /**
     * 用户
     */
    USER;

    public static List<Role> fromRole(String role) {
        return Arrays.stream(StringUtils.split(role, Constants.String.COMMA))
                .map(Role::valueOf)
                .toList();
    }

    public static boolean isAdmin(String role) {
        return fromRole(role).contains(ADMIN);
    }
}
