package pbl.project.ggumimstudioBack.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode
{
    // JWT
    TOKEN_EXPIRED_ERR("JWT-001", "토큰이 만료되었습니다."),
    TOKEN_MALFORMED_ERR("JWT-002", "잘못된 토큰 형식입니다."),
    TOKEN_SIGNATURE_ERR("JWT-003", "서명 오류입니다."),
    TOKEN_PARSING_ERR("JWT-004", "토큰을 파싱할 수 없습니다."),

    // 인증
    TOKEN_NOT_FOUND_ERR("AUTH-001", "토큰을 찾을 수 없습니다."),
    TOKEN_NOT_MATCH_ERR("AUTH-002", "refreshToken 이 일치하지 않습니다."),

    // 회원가입
    USERID_FORMAT_ERR("SIGNUP-001", "아이디가 형식에 맞지 않습니다."),
    CALL_PHONE_FORMAT_ERR("SIGNUP-002", "전화번호가 형식에 맞지 않습니다."),
    EMAIL_FORMAT_ERR("SIGNUP-003", "이메일이 형식에 맞지 않습니다."),
    USERID_CANNOT_DUPLICATE_ERR("SIGNUP-004", "이미 사용중인 아이디 입니다."),
    CALL_PHONE_CANNOT_DUPLICATE_ERR("SIGNUP-005", "이미 사용중인 전화번호 입니다."),
    EMAIL_CANNOT_DUPLICATE_ERR("SIGNUP-006", "이미 사용중인 이메일 주소 입니다."),

    // 회원
    USER_ID_OR_PASSWORD_ERR("USER-000", "아이디 또는 비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND("U-001", "회원이 존재하지 않거나 찾을 수 없습니다."),

    // 파일
    FILE_UPLOAD_FAIL_ERR("FILE-000", "파일 업로드에 실패하였습니다."),
    FILE_IS_NULL_ERR("FILE-001", "파일이 없습니다."),
    FILE_INVALID_FORMAT_ERR("FILE-002", "지원하지 않는 파일 형식입니다."),
    FILE_NOT_FOUND_ERR("FILE-003", "파일을 찾을 수 없습니다."),
    ;

    private final String errorCode;
    private final String errorMessage;
}
