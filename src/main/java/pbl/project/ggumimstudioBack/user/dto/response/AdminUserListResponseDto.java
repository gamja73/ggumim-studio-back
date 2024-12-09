package pbl.project.ggumimstudioBack.user.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.user.entity.User;

@Data
public class AdminUserListResponseDto
{
    private Long userUID;
    private String userId;
    private String userName;
    private String nickname;
    private String createdAt;
    private String updatedAt;
    private String lastLoginAt;

    public AdminUserListResponseDto(User user)
    {
        this.userUID = user.getUserUID();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.nickname = user.getNickname();
        this.createdAt = user.getCreatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDD(user.getCreatedAt()) : "-";
        this.updatedAt = user.getUpdatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDD(user.getUpdatedAt()) : "-";
        this.lastLoginAt = user.getLastLoginAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDD(user.getLastLoginAt()) : "-";
    }
}
