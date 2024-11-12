package pbl.project.ggumimstudioBack.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.auth.dto.LoginRequestDto;
import pbl.project.ggumimstudioBack.auth.jwt.dto.CustomJwtPayload;
import pbl.project.ggumimstudioBack.auth.jwt.provider.JwtProvider;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenService
{

    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    public Cookie login(LoginRequestDto loginRequestDto)
    {
        User findUser = userRepository.findByUserID(loginRequestDto.getId());

        if (findUser == null)
        {
            throw new CustomException(CustomErrorCode.USER_ID_OR_PASSWORD_ERR);
        }

        // TODO : bcrypt 인코딩 후 비밀번호 확인 로직 추가

        CustomJwtPayload customPayload = new CustomJwtPayload(findUser.getUserUID(), findUser.getUserId());

        String accessToken = jwtProvider.createAccessToken(customPayload);
        String refreshToken = jwtProvider.createRefreshToken(customPayload);

        saveRefreshToken(1L, refreshToken, jwtProvider.getExpiration(refreshToken));

        // ACCESS TOKEN 쿠키 생성
        Cookie accessTokenCookie = new Cookie("ACCESS_TOKEN", accessToken);
        accessTokenCookie.setPath("/");

        return accessTokenCookie;
    }

    public Cookie refreshToken(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND);
        }

        Optional<Cookie> accessTokenCookie = java.util.Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("ACCESS_TOKEN"))
                .findFirst();

        if (accessTokenCookie.isEmpty())
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND);
        }

        // ACCESS_TOKEN 에서 회원 UID 가져오기
        CustomJwtPayload customPayload = jwtProvider.getCustomPayload(accessTokenCookie.get().getValue());
        Long userUID = customPayload.getUserUID();

        String storedRefreshToken = getRefreshToken(userUID);

        if (storedRefreshToken == null)
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND);
        }

        // 새로운 ACCESS TOKEN 쿠키 생성
        Cookie newAccessTokenCookie = new Cookie("ACCESS_TOKEN", jwtProvider.createAccessToken(customPayload));
        newAccessTokenCookie.setPath("/");

        return newAccessTokenCookie;
    }

    private void saveRefreshToken(Long userUID, String refreshToken, long expiration)
    {
        redisTemplate.opsForValue().set("refreshToken:" + userUID, refreshToken, expiration, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(Long userUID)
    {
        return redisTemplate.opsForValue().get("refreshToken:" + userUID);
    }

    public void deleteRefreshToken(Long userUID)
    {
        redisTemplate.delete("refreshToken:" + userUID);
    }

    public void addToBlacklist(String token, long expiration)
    {
        redisTemplate.opsForValue().set("blacklist:" + token, "log_out", expiration, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenBlacklisted(String token)
    {
        return redisTemplate.hasKey("blacklist:" + token);
    }
}
