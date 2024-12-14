package pbl.project.ggumimstudioBack.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import pbl.project.ggumimstudioBack.common.error.CustomErrorResponse;

@Data
@AllArgsConstructor
public class CommonApiResponse<T>
{
    private final Boolean success;
    private final Integer statusCode;
    private final T data;

    // 성공 응답 (데이터 없음)
    public static <T> CommonApiResponse<T> OK()
    {
        return new CommonApiResponse<>(true, 200, null);
    }

    // 성공 응답 (데이터 있음)
    public static <T> CommonApiResponse<T> OK(T data)
    {
        return new CommonApiResponse<>(true, 200, data);
    }

    // 오류 응답
    public static <T> CommonApiResponse<T> ERR(CustomErrorResponse error)
    {
        return new CommonApiResponse<>(false, Integer.parseInt(error.getCode()), (T) error.getMessage());
    }

    // 오류 응답
    public static CommonApiResponse<String> ERR(Integer errorCode, String errorMessage)
    {
        return new CommonApiResponse<>(false, errorCode, errorMessage);
    }
}
