package pbl.project.ggumimstudioBack.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode
{
    USER_NOT_FOUND("U-001", "회원이 존재하지 않거나 찾을 수 없습니다.")
    ;

    private final String errorCode;
    private final String errorMessage;
}
