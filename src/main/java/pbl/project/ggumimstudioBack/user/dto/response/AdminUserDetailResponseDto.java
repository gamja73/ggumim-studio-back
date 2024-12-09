package pbl.project.ggumimstudioBack.user.dto.response;

import lombok.Data;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.user.entity.User;

@Data
public class AdminUserDetailResponseDto
{
    private Long userUID;
    private String userId;
    private String userName;
    private String userEmail;
    private String callPhone;
    private String address;
    private String addressDetail;
    private String nickname;
    private String lastLoginAt;
    private String createdAt;
    private String updatedAt;

    public AdminUserDetailResponseDto(User user)
    {
        this.userUID = user.getUserUID();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.callPhone = user.getCallPhone();
        this.address = user.getAddress();
        this.addressDetail = user.getAddressDetail();
        this.nickname = user.getNickname();
        this.lastLoginAt = user.getLastLoginAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDDHHMMSS(user.getLastLoginAt()) : "-";
        this.createdAt = user.getCreatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDDHHMMSS(user.getCreatedAt()) : "-";
        this.updatedAt = user.getUpdatedAt() != null ? CommonUtil.localDateTimeFormatToYYYYMMDDHHMMSS(user.getUpdatedAt()) : "-";
    }
}
