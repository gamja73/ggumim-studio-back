package pbl.project.ggumimstudioBack.user.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.user.entity.User;

@Data
public class UserMyPageResponseData
{
    private Long userUID;
    private String userName;

    public UserMyPageResponseData(User user)
    {
        this.userUID = user.getUserUID();
        this.userName = user.getUserName();
    }
}
