package io.hydrocarbon.campus.electricity.param.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    /**
     * JWT token
     */
    private String token;
}
