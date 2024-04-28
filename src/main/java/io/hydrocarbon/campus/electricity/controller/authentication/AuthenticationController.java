package io.hydrocarbon.campus.electricity.controller.authentication;

import io.hydrocarbon.campus.electricity.param.request.LoginRequest;
import io.hydrocarbon.campus.electricity.param.request.RegisterRequest;
import io.hydrocarbon.campus.electricity.param.response.Response;
import io.hydrocarbon.campus.electricity.param.response.authentication.AuthenticationResponse;
import io.hydrocarbon.campus.electricity.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HydroCarbon
 * @since 2024-04-23
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * 注册
     *
     * @param request 注册请求参数
     * @return 注册结果
     */
    @PostMapping("/register")
    public Response<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return Response.success(authenticationService.register(request));
    }

    /**
     * 登录
     *
     * @param request 登录请求参数
     * @return 登录结果
     */
    @PostMapping("/login")
    public Response<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return Response.success(authenticationService.login(request));
    }
}
