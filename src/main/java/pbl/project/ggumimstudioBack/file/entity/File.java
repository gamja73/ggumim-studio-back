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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileUID;

    @Comment("회원 UID")
    private Long userUID;

    @Comment("관리자 UID")
    private Long adminUID;

    @Comment("원본 파일명")
    private String fileName;

    @Comment("원본 파일 URL")
    private String originURL;

    @Comment("webp 변환 파일 URL")
    private String webpURL;
}
