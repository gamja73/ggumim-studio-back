package pbl.project.ggumimstudioBack.common.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import pbl.project.ggumimstudioBack.common.dto.response.CommonApiResponse;

@RestControllerAdvice
public class GlobalApiExceptionHandler
{
    // 404 Not Found 예외 처리
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonApiResponse<CustomErrorResponse> handleNotFoundException()
    {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.of("404", "Resource not found");

        return CommonApiResponse.ERR(customErrorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public CommonApiResponse<CustomErrorResponse> handleCustomErrorResponseException(CustomException ex)
    {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.of("400", ex.getMessage());

        return CommonApiResponse.ERR(customErrorResponse);
    }

    // 모든 기타 API 예외 처리
    @ExceptionHandler(Exception.class)
    public CommonApiResponse<CustomErrorResponse> handleGlobalApiException(Exception ex)
    {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.of("500", "aaa");

        return CommonApiResponse.ERR(customErrorResponse);
    }
}
