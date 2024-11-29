package pbl.project.ggumimstudioBack.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbl.project.ggumimstudioBack.auth.dto.LoginRequestDto;
import pbl.project.ggumimstudioBack.auth.dto.SignUpRequestDto;
import pbl.project.ggumimstudioBack.auth.dto.TokenResponseDto;
import pbl.project.ggumimstudioBack.auth.service.AuthService;
import pbl.project.ggumimstudioBack.auth.service.TokenService;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApiController
{
    private final TokenService tokenService;
    private final AuthService authService;

    @PostMapping("/login")
    public CommonApiResponse<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto)
    {
        TokenResponseDto responseDto = tokenService.userLogin(loginRequestDto);

        return CommonApiResponse.OK(responseDto);
    }

    @PostMapping("/refresh")
    public CommonApiResponse<?> refreshToken(HttpServletRequest request)
    {
        TokenResponseDto responseDto = tokenService.refreshToken(request);

        return CommonApiResponse.OK(responseDto);
    }

    @PostMapping("/logout")
    public CommonApiResponse<?> logout(HttpServletRequest request)
    {
        return CommonApiResponse.OK(tokenService.userLogout(request));
    }

    @PostMapping("/signup")
    public CommonApiResponse<?> signUp(@RequestBody @Valid SignUpRequestDto requestDto)
    {
        return CommonApiResponse.OK(authService.signUpUser(requestDto));
    }
}
