package pbl.project.ggumimstudioBack.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pbl.project.ggumimstudioBack.auth.jwt.dto.CustomJwtPayload;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtil
{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.access.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public String generateToken(CustomJwtPayload claims, boolean isAccessToken)
    {
        long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;

        Map<String, Object> claimsMap = objectMapper.convertValue(claims, Map.class);

        return Jwts.builder()
                .setClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public CustomJwtPayload extractClaims(String token)
    {
        // JWT -> Claims 추출
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Map claimMap = objectMapper.convertValue(claims, Map.class);

        // Map -> DTO 변환
        return new CustomJwtPayload(claimMap);
    }

    public boolean isTokenValid(String token)
    {
        if (token != null && !token.isBlank())
        {
            extractClaims(token);
            return true;
        }

        return true;
    }

    public Long getUidFromToken(HttpServletRequest request)
    {
        return extractClaims(getAccessTokenCookie(request)).getUid();
    }

    private String getAccessTokenCookie(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
        {
            throw new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR);
        }

        Cookie accessTokenCookie = java.util.Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("accessToken"))
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomErrorCode.TOKEN_NOT_FOUND_ERR));

        return accessTokenCookie.getValue();
    }

    public Authentication getAuthentication(String token)
    {
        CustomJwtPayload payload = extractClaims(token);

        // UserDetails 객체를 가져와서 Authentication 생성
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(payload.getRole()));
        UserDetails userDetails = new User(payload.getNickname(), "", authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }
}
