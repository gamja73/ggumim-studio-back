package pbl.project.ggumimstudioBack.auth.jwt.dto;

import lombok.Data;
import pbl.project.ggumimstudioBack.auth.jwt.constants.UserRole;
import pbl.project.ggumimstudioBack.user.entity.User;

@Data
public class CustomJwtPayload
{
    private Long uid;
    private String id;
    private String nickname;
    private UserRole role;

    public CustomJwtPayload(User user)
    {
        this.uid = user.getUserUID();
        this.id = user.getUserId();
        this.nickname = user.getNickname();
        this.role = UserRole.ROLE_USER;
    }
}