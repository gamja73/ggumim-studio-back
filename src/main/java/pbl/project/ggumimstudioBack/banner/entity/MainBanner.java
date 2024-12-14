package pbl.project.ggumimstudioBack.banner.entity;

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
@Table(name = "main_banner")
public class MainBanner extends BaseEntity
{
    @Id
    @Column(name = "main_banner_uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainBannerUID;

    @Comment("배너 이미지")
    @Column(name = "main_banner_img")
    private String mainBannerImg;

    @Comment("배너 클릭시 이동 페이지")
    @Column(name = "main_banner_link")
    private String mainBannerLink;

    @Comment("노출 여부")
    @Column(name = "is_visible")
    private Boolean isVisible;
}
