package pbl.project.ggumimstudioBack.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pbl.project.ggumimstudioBack.auth.jwt.dto.CustomJwtPayload;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.access.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;

    @Value("${jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public String generateToken(CustomJwtPayload claims, boolean isAccessToken) {
        long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;

        Map<String, Object> claimsMap = objectMapper.convertValue(claims, Map.class);

        return Jwts.builder()
                .setClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public CustomJwtPayload extractClaims(String token) {
        try
        {
            // JWT -> Claims 추출
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            // Map -> DTO 변환
            return objectMapper.convertValue(claims, CustomJwtPayload.class);
        }
        catch (Exception e)
        {
            throw new CustomException(CustomErrorCode.TOKEN_PARSING_ERR);
        }
    }

    public boolean isTokenValid(String token) {
        try
        {
            extractClaims(token);
            return true;
        }
        catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e)
        {
            return false;
        }
    }

    public Long getUidFromToken(String token)
    {
        return extractClaims(token).getUid();
    }
}
