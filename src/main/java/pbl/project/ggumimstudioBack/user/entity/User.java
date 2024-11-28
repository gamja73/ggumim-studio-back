package pbl.project.ggumimstudioBack.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity
{
    @Id
    @Column(name = "user_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userUID;

    @Comment("회원 ID")
    @Column(name = "user_id")
    private String userId;

    @Comment("회원 비밀번호")
    @Column(name = "password")
    private String password;

    @Comment("회원명")
    @Column(name = "user_name")
    private String userName;

    @Comment("이메일 주소")
    @Column(name = "user_email")
    private String userEmail;

    @Comment("전화번호")
    @Column(name = "call_phone")
    private String callPhone;

    @Comment("주소")
    @Column(name = "address")
    private String address;

    @Comment("상세 주소")
    @Column(name = "address_detail")
    private String addressDetail;

    @Comment("회원 별명")
    @Column(name = "nickname")
    private String nickname;
}
