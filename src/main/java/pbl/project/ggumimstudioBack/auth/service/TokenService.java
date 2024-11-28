package pbl.project.ggumimstudioBack.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.auth.dto.LoginRequestDto;
import pbl.project.ggumimstudioBack.auth.dto.TokenResponseDto;
import pbl.project.ggumimstudioBack.auth.jwt.dto.CustomJwtPayload;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenService
{
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private static final String REFRESH_TOKEN_PREFIX = "refresh:";
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDto userLogin(LoginRequestDto loginRequestDto)
    {
        User findUser = userRepository.findByUserID(loginRequestDto.getId());

        if (findUser == null)
        {
            throw new CustomException(CustomErrorCode.USER_ID_OR_PASSWORD_ERR);
        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), findUser.getPassword()))
        {
            throw new CustomException(CustomErrorCode.USER_ID_OR_PASSWORD_ERR);
        }

        CustomJwtPayload customPayload = new CustomJwtPayload(findUser);

        String accessToken = jwtUtil.generateToken(customPayload, true);
        String refreshToken = jwtUtil.generateToken(customPayload, false);

        storeRefreshToken(customPayload.getUid().toString(), refreshToken, 1000 * 60 * 60 * 24 * 7);

        return new TokenResponseDto(accessToken, refreshToken);
    }

    public TokenResponseDto refreshToken(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR);
        }

        Cookie refreshTokenCookie = java.util.Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR));

        // REFRESH_TOKEN 에서 회원 UID 가져오기
        CustomJwtPayload payload = jwtUtil.extractClaims(refreshTokenCookie.getValue());
        Long userUID = payload.getUid();

        String storedRefreshToken = getRefreshToken(userUID);

        if (storedRefreshToken == null)
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR);
        }

        if (!refreshTokenCookie.getValue().equals(storedRefreshToken))
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_MATCH_ERR);
        }

        String accessToken = jwtUtil.generateToken(payload, true);
        String refreshToken = jwtUtil.generateToken(payload, false);

        storeRefreshToken(userUID.toString(), refreshToken, 1000 * 60 * 60 * 24 * 7);

        return new TokenResponseDto(accessToken, refreshToken);
    }

    public boolean userLogout(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR);
        }

        Cookie refreshTokenCookie = java.util.Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR));

        // REFRESH_TOKEN 에서 회원 UID 가져오기
        CustomJwtPayload payload = jwtUtil.extractClaims(refreshTokenCookie.getValue());
        Long userUID = payload.getUid();

        String storedRefreshToken = getRefreshToken(userUID);

        if (storedRefreshToken != null)
        {
            deleteRefreshToken(userUID);
        }

        return true;
    }

    private void storeRefreshToken(String userId, String refreshToken, long duration)
    {
        redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX + userId, refreshToken, duration, TimeUnit.MILLISECONDS);
    }

    private String getRefreshToken(Long userId)
    {
        return redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + userId);
    }

    private void deleteRefreshToken(Long userId)
    {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + userId);
    }
}
