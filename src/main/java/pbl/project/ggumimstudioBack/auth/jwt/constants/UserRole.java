package pbl.project.ggumimstudioBack.auth.jwt.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole
{
    ROLE_ADMIN("최고관리자"),
    ROLE_SHOP_ADMIN("쇼핑몰관리자"),
    ROLE_USER("일반회원");

    private final String roleName;
}
