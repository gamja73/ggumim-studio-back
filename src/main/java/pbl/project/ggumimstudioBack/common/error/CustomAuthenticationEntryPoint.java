package pbl.project.ggumimstudioBack.common.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint
{
    private final ObjectMapper objectMapper;
    private final CustomErrorCode errorCode = CustomErrorCode.TOKEN_EXPIRED_ERR;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException
    {
        if (request.getRequestURI().startsWith("/api") || request.getRequestURI().startsWith("/error"))
        {
            CustomErrorResponse errorResponse = CustomErrorResponse.of(errorCode.getErrorCode(), errorCode.getErrorMessage());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);

            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(CommonApiResponse.ERR(401, errorResponse.getMessage())));
            writer.flush();
        }

    }
}