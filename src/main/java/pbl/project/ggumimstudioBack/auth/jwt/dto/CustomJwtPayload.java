package pbl.project.ggumimstudioBack.auth.jwt.dto;

import lombok.Data;
import lombok.ToString;
import pbl.project.ggumimstudioBack.auth.jwt.constants.UserRole;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.util.Map;

@Data
@ToString
public class CustomJwtPayload
{
    private Long uid;
    private String id;
    private String nickname;
    private String role;
    private Long iat;
    private Long exp;

    public CustomJwtPayload(User user)
    {
        this.uid = user.getUserUID();
        this.id = user.getUserId();
        this.nickname = user.getNickname();
        this.role = UserRole.ROLE_USER.name();
    }

    public CustomJwtPayload(Map map)
    {
        this.uid = Long.valueOf(map.get("uid").toString());
        this.id = map.get("id").toString();
        this.nickname = map.get("nickname").toString();
        this.role = map.get("role").toString();
    }
}