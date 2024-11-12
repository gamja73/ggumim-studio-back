package pbl.project.ggumimstudioBack.auth.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbl.project.ggumimstudioBack.auth.dto.LoginRequestDto;
import pbl.project.ggumimstudioBack.auth.jwt.dto.CustomJwtPayload;
import pbl.project.ggumimstudioBack.auth.jwt.provider.JwtProvider;
import pbl.project.ggumimstudioBack.auth.service.TokenService;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;

import java.security.SignatureException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController
{

    private final JwtProvider jwtProvider;
    private final TokenService tokenService;

    @PostMapping("/login")
    public CommonApiResponse<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response)
    {
        Cookie accessToken = tokenService.login(loginRequestDto);

        response.addCookie(accessToken);

        return CommonApiResponse.OK();
    }

    @PostMapping("/refresh")
    public CommonApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie newAccessToken = tokenService.refreshToken(request);

        response.addCookie(newAccessToken);

        return CommonApiResponse.OK();
    }

    // TODO : 관리자 페이지로 이동 필요
//    @PostMapping("/force-logout")
//    public ResponseEntity<?> forceLogout(@RequestParam String userUID, HttpServletRequest request)
//    {
//        String accessToken = request.getHeader("Authorization").substring(7);
//        long expiration = jwtProvider.getExpiration(accessToken);
//
////        tokenService.addToBlacklist(accessToken, expiration);
////        tokenService.deleteRefreshToken(userUID);
//
//        return ResponseEntity.ok("User has been logged out.");
//    }
}
