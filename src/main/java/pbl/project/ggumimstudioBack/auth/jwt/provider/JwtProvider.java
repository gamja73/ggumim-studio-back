package pbl.project.ggumimstudioBack.auth.jwt.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pbl.project.ggumimstudioBack.auth.jwt.dto.CustomJwtPayload;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider
{
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenValidityInMilliseconds;

    @Value("${jwt.refreshTokenExpiration}")
    private Long refreshTokenValidityInMilliseconds;

    private Key key;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init()
    {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createAccessToken(CustomJwtPayload customPayload)
    {
        return createToken(customPayload, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken(CustomJwtPayload customPayload)
    {
        return createToken(customPayload, refreshTokenValidityInMilliseconds);
    }


    private String createToken(CustomJwtPayload customPayload, Long validityInMilliseconds)
    {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        try
        {
            String customPayloadJson = objectMapper.writeValueAsString(customPayload);
            return Jwts.builder()
                    .setSubject(customPayload.getUserUID().toString())
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .claim("customPayload", customPayloadJson)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException("Failed to serialize custom payload", e);
        }
    }

    public boolean validateToken(String token)
    {
        try
        {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException e)
        {
            return false;
        }
    }

    public CustomJwtPayload getCustomPayload(String token)
    {
        try
        {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            String customPayloadJson = claims.get("customPayload", String.class);
            return objectMapper.readValue(customPayloadJson, CustomJwtPayload.class);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to parse custom payload", e);
        }
    }

    public long getExpiration(String token)
    {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getExpiration().getTime() - System.currentTimeMillis();
    }
}
