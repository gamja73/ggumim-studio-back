package pbl.project.ggumimstudioBack.auth.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
import pbl.project.ggumimstudioBack.user.entity.User;

@Getter
@ToString
public class SignUpRequestDto
{
    private String id;
    private String password;
    private String name;
    private String callPhone;
    private String email;
    private String nickname;
    private String address;
    private String addressDetail;

    public User buildUser(PasswordEncoder passwordEncoder)
    {
        return User.builder()
                .userId(this.id)
                .password(passwordEncoder.encode(this.password))  // 비밀번호 Bcrypt 단방향 암호화 (해시)
                .userName(this.name)
                .userEmail(this.email)
                .callPhone(this.callPhone)
                .nickname(this.nickname)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .build();
    }
}
