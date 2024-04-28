package io.hydrocarbon.campus.electricity.util;

import io.hydrocarbon.campus.electricity.entity.user.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

/**
 * @author HydroCarbon
 * @since 2024-04-27
 */
public final class UserUtil {

    private UserUtil() {
    }

    public static UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserEntity user) {
            return user.getId();
        }
        return null;
    }

    public static UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserEntity user) {
            return user;
        }
        return null;
    }
}
