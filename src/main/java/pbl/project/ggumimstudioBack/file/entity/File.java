package pbl.project.ggumimstudioBack.file.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import pbl.project.ggumimstudioBack.common.entity.BaseEntity;

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
    @Column(name = "user_uid")
    private Long userUID;

    @Comment("관리자 UID")
    @Column(name = "admin_uid")
    private Long adminUID;

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
