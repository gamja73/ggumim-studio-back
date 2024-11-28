package pbl.project.ggumimstudioBack.auth.jwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pbl.project.ggumimstudioBack.common.error.CustomAuthenticationEntryPoint;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String token = getTokenFromRequest(request);

        if (token != null)
        {
            try
            {
                if (!jwtUtil.isTokenValid(token))
                {
                    authenticationEntryPoint.commence(request, response, new AuthenticationException("Invalid JWT Token") {});
                    return;
                }
            }
            catch (ExpiredJwtException e) {
                // 토큰이 만료된 경우
                authenticationEntryPoint.commence(request, response, new AuthenticationException(CustomErrorCode.TOKEN_EXPIRED_ERR.getErrorMessage()) {});
                return;
            }

            Authentication authentication = jwtUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request)
    {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer "))
        {
            return header.substring(7);  // "Bearer " 이후의 토큰 부분을 반환
        }
        return null;
    }
}
