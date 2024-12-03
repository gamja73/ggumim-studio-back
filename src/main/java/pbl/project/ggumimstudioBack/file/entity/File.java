package pbl.project.ggumimstudioBack.file.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;
import pbl.project.ggumimstudioBack.user.entity.User;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file")
public class File extends BaseEntity
{
    @Id
    @Column(name = "file_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileUID;

    @Comment("회원 UID")
    @JoinColumn(name = "user_uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userUID;

    @Comment("관리자 UID")
    @JoinColumn(name = "admin_uid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User adminUID;

    @Comment("원본 파일명")
    @Column(name = "file_name")
    private String fileName;

    @Comment("원본 파일 URL")
    @Column(name = "origin_url")
    private String originURL;

    @Comment("webp 변환 파일 URL")
    @Column(name = "webp_url")
    private String webpURL;
}
